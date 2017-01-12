package com.lianjia.sh.se.config.regaltang.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lianjia.sh.se.config.regaltang.descriptor.RuleItem;
import com.lianjia.sh.se.config.regaltang.rule.option.CriteriaOption;
import com.lianjia.sh.se.config.regaltang.rule.output.RuleOutput;

/**
 * 手动配置当前应用程序的基于规则的业务模块
 * 
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public final class ManualRuleConfigurer implements RuleConfigurer<ManualRuleConfigurer> {
  private Logger logger = LoggerFactory.getLogger(this.getClass());
  /**
   * spring.application.name对外显示的名称
   */
  private String appName;

  private List<DefaultModuleConfigurer> moduleConfigurers = new ArrayList<>();

  private ManualRuleConfigurer() {
  }

  /**
   * 手动配置业务模块相关信息
   */
  public static RuleConfigurer<ManualRuleConfigurer> create() {
    return new ManualRuleConfigurer();
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
   * 开始配置当前应用的业务配置模块；
   */
  public ModuleConfigurer<ManualRuleConfigurer> withModule() {
    DefaultModuleConfigurer moduleConfigurer = new DefaultModuleConfigurer(this);
    moduleConfigurers.add(moduleConfigurer);
    return moduleConfigurer;
  }

  /**
   * 校验已配置好的模块
   */
  void validate() {
    if (this.appName == null || this.appName.trim().isEmpty()) {
      throw new IllegalArgumentException("请指定appName");
    }
    Set<String> moduleIdentitys = new HashSet<>();
    // ruleItem的name
    for (DefaultModuleConfigurer module : moduleConfigurers) {
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
  public void print() {
    logger.info("\n");
    logger.info("****************** {} 启用的业务模块 ******************", this.appName);
    for (DefaultModuleConfigurer moduleConfigurer : moduleConfigurers) {
      logger.info("****************** 模块 name={}，identity={} ******************", moduleConfigurer.name, moduleConfigurer.identity);
      moduleConfigurer.configItems.forEach((item) -> logger.info("=====>  配置项：{}", item));
      moduleConfigurer.criteriaOptions.forEach((item) -> logger.info("=====>  条件选项：{}", item));
      logger.info("=====>  输出结果：{}", moduleConfigurer.ruleOutput);
    }
    logger.info("\n");
  }

  static class DefaultModuleConfigurer implements ModuleConfigurer<ManualRuleConfigurer> {
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

    DefaultModuleConfigurer(ManualRuleConfigurer ruleConfigurer) {
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
