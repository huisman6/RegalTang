package com.lianjia.sh.se.config.regaltang.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lianjia.sh.se.config.regaltang.dto.ApplicationDescriptor;
import com.lianjia.sh.se.config.regaltang.dto.ModuleDescriptor;
import com.lianjia.sh.se.config.regaltang.dto.ModuleOptionDescriptor;
import com.lianjia.sh.se.config.regaltang.dto.ModuleOutputDescriptor;
import com.lianjia.sh.se.config.regaltang.model.Application;
import com.lianjia.sh.se.config.regaltang.model.ApplicationModule;
import com.lianjia.sh.se.config.regaltang.model.ModuleOption;
import com.lianjia.sh.se.config.regaltang.model.ModuleOptionNamedValue;
import com.lianjia.sh.se.config.regaltang.model.ModuleOutput;
import com.lianjia.sh.se.config.regaltang.model.ModuleOutputJavaBean;
import com.lianjia.sh.se.config.regaltang.model.ModuleOutputPredefinedValue;
import com.lianjia.sh.se.config.regaltang.model.ModuleRuleItem;
import com.lianjia.sh.se.config.regaltang.rule.item.RuleItem;
import com.lianjia.sh.se.config.regaltang.rule.option.CriteriaOption;
import com.lianjia.sh.se.config.regaltang.rule.option.EnumerableValueOption;
import com.lianjia.sh.se.config.regaltang.rule.output.RuleOutput;
import com.lianjia.sh.se.config.regaltang.rule.value.EnumerableValues;
import com.lianjia.sh.se.config.regaltang.rule.value.MultiKeyBasedValues;

/**
 * 手动配置当前应用程序的基于规则的业务模块
 * 
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public final class ManualRuleConfigurer implements RuleConfigurer<ManualRuleConfigurer> {
  private Logger logger = LoggerFactory.getLogger(this.getClass());
  /**
   * 单个应用程序就一个
   */
  private static final ManualRuleConfigurer SIGLETON_CONFIGURER = new ManualRuleConfigurer();
  /**
   * spring.application.name对外显示的名称
   */
  private String appName;
  /**
   * spring.applicaton.name环境变量，由系统自动解析，无需手动输入
   */
  private String springApplicationName;

  /**
   * 配置是否完毕
   */
  private boolean configDone = false;

  private List<ManualModuleConfigurer> moduleConfigurers = new ArrayList<>();

  private ManualRuleConfigurer() {}

  /**
   * 手动配置业务模块相关信息，单例，整个项目就一个实例
   */
  public static ManualRuleConfigurer singleton() {
    return SIGLETON_CONFIGURER;
  }

  /**
   * 设置spring.application.name在配置界面对外显示的名称
   * 
   * @param appName spring.application.name对外显示的名称
   */
  public ManualRuleConfigurer appName(String appName) {
    this.appName = appName;
    return this;
  }

      /**
       * ${spring.application.name}，由项目自动解析，无需用户指定
       */
   /* non-public */ public ManualRuleConfigurer springApplicationName(String springApplicationName) {
    this.springApplicationName = springApplicationName;
    return this;
  }


  /**
   * 给应用新增业务配置模块；
   */
  public ManualRuleConfigurer addModule(ManualModuleConfigurer module) {
    moduleConfigurers.add(module);
    return this;
  }

  /**
   * 开始配置当前应用的业务配置模块；
   */
  public ModuleConfigurer<ManualRuleConfigurer> withModule() {
    ManualModuleConfigurer moduleConfigurer = ManualModuleConfigurer.module();
    moduleConfigurers.add(moduleConfigurer);
    return moduleConfigurer;
  }

  /**
   * 等应用启动完毕之后，自动提交应用的规则配置；<br>
   * 在提交之前，必须自动解析springApplicationName以及自动发现配置的模块;<br>
   * 此方法只能调用一次
   */
 /*non-public*/public synchronized ApplicationDescriptor done() {
    // 是否需要标识应用程序配置完毕
    if (this.configDone) {
      throw new IllegalStateException("应用程序的规则配置已提交，重复调用done()无效。");
    }
    // 开始校验模块信息
    validate();
    
    //打印配置信息
    this.print();
    
    logger.info("=====> 开始构造完整的ApplicationDescriptor");
    // 构造dto之后，开始销毁Configurer引用的数据
    ApplicationDescriptor appDescriptor = buildFullAppDescriptor();
    logger.info("=====> ApplicationDescriptor构造完毕");
    this.destroy();
    // 保存解析出来的信息
    this.configDone = true;
    return appDescriptor;
  }

  /**
   * 生成完整的规则描述元数据信息，准备提交给服务端
   */
  private ApplicationDescriptor buildFullAppDescriptor() {
    ApplicationDescriptor appDescriptor = new ApplicationDescriptor();
    // 应用的信息
    appDescriptor.setApp(new Application(this.springApplicationName, this.appName));
    // module
    //@formatter:off
    for (ManualModuleConfigurer module : moduleConfigurers) {
      ModuleDescriptor moduleDescriptor=new ModuleDescriptor();
      //模块元数据
      moduleDescriptor.setModule(new ApplicationModule(module.identity, module.name));
      
      //配置项元数据
      moduleDescriptor.setItems(
        module.configItems.stream()
        .map((item) -> new ModuleRuleItem(item.identity(),item.name()))
          .collect(Collectors.toList()));
      
      //条件选项
      moduleDescriptor.setOptions(
        module.criteriaOptions.stream().map( 
          (option) ->{
            ModuleOptionDescriptor mod= new ModuleOptionDescriptor(new ModuleOption(option.key(), option.type().getName(),option.name()));
             //如果有多个预定义的选项值
             if (option instanceof EnumerableValueOption) {
               mod.setPredefinedValues(
                 ((EnumerableValueOption)option).elements()
                 .stream().map((value) -> new ModuleOptionNamedValue(value.name(),
                     value.value().toString())).collect(Collectors.toList()));
             }
             return mod;
          }).collect(Collectors.toList()));
      
      
      //条件输出的元数据
      ModuleOutputDescriptor outputDescriptor=
          new ModuleOutputDescriptor(new ModuleOutput(module.ruleOutput.name(),
            module.ruleOutput.type().getName()));
      
      if (module.ruleOutput instanceof EnumerableValues) {
        //预定义的输出，只给用户选
        outputDescriptor.setPredefinedValues(
          ((EnumerableValues)module.ruleOutput).elements().stream()
          .map((item) -> new ModuleOutputPredefinedValue(item.name(),
            item.value().toString())).collect(Collectors.toList()));
      }else if(module.ruleOutput instanceof MultiKeyBasedValues){
        //有多个选项配置
        outputDescriptor.setJavaBeanFields(
          ((MultiKeyBasedValues)module.ruleOutput).elements().stream()
          .map((item) -> new ModuleOutputJavaBean(item.key(),
            item.type().getName())).collect(Collectors.toList()));
      }
      moduleDescriptor.setOutput(outputDescriptor);
      
      //添加当前模块
      appDescriptor.addModule(moduleDescriptor);
    }
    
    //@formatter:on
    return appDescriptor;
  }

  /*non-public*/  synchronized void destroy() {
    logger.info("======> 销毁Configurer引用的模块配置信息");
    this.moduleConfigurers = null;
  }

  /**
   * 校验已配置好的模块
   */
  /*non-public*/ synchronized void validate() {
    if (this.springApplicationName == null || this.springApplicationName.trim().isEmpty()) {
      throw new IllegalArgumentException("请指定springApplicationName");
    }

    if (this.appName == null || this.appName.trim().isEmpty()) {
      logger.warn("=========> 没有手动指定当前应用的显示名称(appName)，系统自动默认为：{}", this.springApplicationName);
      this.appName = springApplicationName;
    }

    Set<String> moduleIdentitys = new HashSet<>();
    // ruleItem的name
    for (ManualModuleConfigurer module : moduleConfigurers) {
      if (module.identity == null || module.identity.trim().isEmpty()) {
        throw new IllegalArgumentException(String.format("appName:%s,module identity is null,module name:%s", this.appName, module.name));
      }
      if (moduleIdentitys.contains(module.identity)) {
        throw new IllegalArgumentException(String.format("appName:%s,module identity:%s,module identity重复", this.appName, module.identity));
      }
      moduleIdentitys.add(module.identity);

      Set<String> itemIdentitys = new HashSet<>();
      for (RuleItem item : module.configItems) {
        if (item.identity() == null || item.identity().trim().isEmpty()) {
          throw new IllegalArgumentException(String.format("module:%s(name=%s),配置项identity为空", module.identity, module.name));
        }
        if (itemIdentitys.contains(item.identity())) {
          throw new IllegalArgumentException(
              String.format("module:%s(name=%s),item identity:%s，配置项identity重复", module.identity, module.name, item.identity()));
        }
        itemIdentitys.add(item.identity());
      }

      Set<String> optionIdentitys = new HashSet<>();
      for (CriteriaOption option : module.criteriaOptions) {
        if (option.key() == null || option.key().trim().isEmpty()) {
          throw new IllegalArgumentException(String.format("module:%s(name=%s),条件选项key为空", module.identity, module.name));
        }
        if (optionIdentitys.contains(option.key())) {
          throw new IllegalArgumentException(
              String.format("module:%s(name=%s),option key:%s，条件选项key重复", module.identity, module.name, option.key()));
        }
        optionIdentitys.add(option.key());
      }
      if (module.ruleOutput == null) {
        throw new IllegalArgumentException(String.format("module:%s(name=%s),ruleOutput为空", module.identity, module.name));
      }
    }

  }

  /**
   * 打印当前配置好的模块信息
   */
  /*non-public*/ void print() {
    logger.info("\n");
    logger.info("****************** {} 启用的业务模块 ******************", this.appName);
    for (ManualModuleConfigurer moduleConfigurer : moduleConfigurers) {
      logger.info("****************** 模块 name={}，identity={} ******************", moduleConfigurer.name, moduleConfigurer.identity);
      moduleConfigurer.configItems.forEach((item) -> logger.info("=====>  配置项：{}", item));
      moduleConfigurer.criteriaOptions.forEach((item) -> logger.info("=====>  条件选项：{}", item));
      logger.info("=====>  输出结果：{}", moduleConfigurer.ruleOutput);
    }
    logger.info("\n");
  }

  public static class ManualModuleConfigurer implements ModuleConfigurer<ManualRuleConfigurer> {
    /**
     * 规则配置
     */
    private final ManualRuleConfigurer ruleConfigurer;
    /**
     * 可配置项
     */
    private List<RuleItem> configItems = new ArrayList<>();
    /**
     * 业务条件选项
     */
    private List<CriteriaOption> criteriaOptions = new ArrayList<>();
    /**
     * 模块输出
     */
    private RuleOutput ruleOutput;

    /**
     * 模块名称
     */
    private String name;
    /**
     * 模块的标识
     */
    private String identity;

    /**
     * 给{@code ManualRuleConfigurer}新增个模块
     * 
     * @param ruleConfigurer
     */
    public static ManualModuleConfigurer module() {
      return new ManualModuleConfigurer(ManualRuleConfigurer.singleton());
    }

    private ManualModuleConfigurer(ManualRuleConfigurer ruleConfigurer) {
      this.ruleConfigurer = ruleConfigurer;
    }

    @Override
    public ManualRuleConfigurer and() {
      return this.ruleConfigurer;
    }

    @Override
    public ModuleConfigurer<ManualRuleConfigurer> ruleItems(List<RuleItem> ruleItems) {
      if (ruleItems == null || ruleItems.isEmpty()) {
        throw new IllegalArgumentException("RuleItem 不能为空");
      }
      this.configItems.addAll(ruleItems);
      return this;
    }

    @Override
    public ModuleConfigurer<ManualRuleConfigurer> availableOptions(List<CriteriaOption> options) {
      if (options == null || options.isEmpty()) {
        throw new IllegalArgumentException("CriteriaOptions 不能为空");
      }
      this.criteriaOptions.addAll(options);
      return this;
    }

    @Override
    public ModuleConfigurer<ManualRuleConfigurer> output(RuleOutput output) {
      if (output == null) {
        throw new IllegalArgumentException("RuleOutput不能为空");
      }
      this.ruleOutput = output;
      return this;
    }

    /*
     * @see
     * com.lianjia.sh.se.config.regaltang.config.ModuleConfigurer#ruleItems(com.lianjia.sh.se.config
     * .regaltang.descriptor.RuleItem[])
     */
    @Override
    public ModuleConfigurer<ManualRuleConfigurer> ruleItems(RuleItem... ruleItems) {
      if (ruleItems == null || ruleItems.length == 0) {
        throw new IllegalArgumentException("RuleItem 不能为空");
      }
      this.configItems.addAll(Arrays.asList(ruleItems));
      return this;
    }

    /*
     * @see
     * com.lianjia.sh.se.config.regaltang.config.ModuleConfigurer#availableOptions(com.lianjia.sh.se
     * .config.regaltang.rule.option.CriteriaOption[])
     */
    @Override
    public ModuleConfigurer<ManualRuleConfigurer> availableOptions(CriteriaOption... options) {
      if (options == null || options.length == 0) {
        throw new IllegalArgumentException("options 不能为空");
      }
      this.criteriaOptions.addAll(Arrays.asList(options));
      return this;
    }

    /*
     * @see com.lianjia.sh.se.config.regaltang.config.ModuleConfigurer#identity()
     */
    @Override
    public ModuleConfigurer<ManualRuleConfigurer> identity(String identity) {
      this.identity = identity;
      return this;
    }

    /*
     * @see com.lianjia.sh.se.config.regaltang.config.ModuleConfigurer#name()
     */
    @Override
    public ModuleConfigurer<ManualRuleConfigurer> name(String name) {
      this.name = name;
      return this;
    }
  }
}
