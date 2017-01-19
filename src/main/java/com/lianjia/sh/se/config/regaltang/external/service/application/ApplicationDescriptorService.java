package com.lianjia.sh.se.config.regaltang.external.service.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lianjia.sh.se.config.regaltang.dto.ApplicationDescriptor;
import com.lianjia.sh.se.config.regaltang.dto.ModuleDescriptor;
import com.lianjia.sh.se.config.regaltang.dto.ModuleOptionDescriptor;
import com.lianjia.sh.se.config.regaltang.dto.ModuleOutputDescriptor;
import com.lianjia.sh.se.config.regaltang.external.service.item.ModuleRuleItemService;
import com.lianjia.sh.se.config.regaltang.external.service.module.ApplicationModuleService;
import com.lianjia.sh.se.config.regaltang.external.service.output.ModuleOutputService;
import com.lianjia.sh.se.config.regaltang.model.Application;
import com.lianjia.sh.se.config.regaltang.model.ApplicationModule;
import com.lianjia.sh.se.config.regaltang.model.ModuleOption;
import com.lianjia.sh.se.config.regaltang.model.ModuleOutput;
import com.lianjia.sh.se.config.regaltang.model.ModuleOutputJavaBean;
import com.lianjia.sh.se.config.regaltang.model.ModuleOutputPredefinedValue;
import com.lianjia.sh.se.config.regaltang.model.ModuleRuleItem;

@Service
public class ApplicationDescriptorService {
  @Autowired
  private RuleApplicationService ruleApplicationService;
  @Autowired
  private ApplicationModuleService applicationModuleService;
  @Autowired
  private ModuleRuleItemService moduleRuleItemService;
  @Autowired
  private ModuleOutputService moduleOutputService;

  /**
   * 将appDescriptor保存到数据库（修改或者新增）
   * 
   * @param appDescriptor 应用程序的描述信息
   */
  public boolean configApp(ApplicationDescriptor appDescriptor, String appDigest) {
    // 首先校验数据是否完整
    AppDescriptorValidator.validate(appDescriptor);

    // 开始保存整个数据
    Application app = appDescriptor.getApp();
    // 已经存在了的应用信息
    Application existedApp = this.ruleApplicationService.findByAppKey(app.getAppKey());
    if (existedApp == null) {
      // 首次初始化，我们新增整个层级的数据
      addApp(appDescriptor, appDigest);
    } else {
      // 负责要比对摘要信息，以确定数据有变化
      existedApp.setDigest(appDigest);
      updateApp(appDescriptor, existedApp);
    }
    return true;
  }

  /**
   * 只处理新增应用的逻辑，不涉及更新的问题
   * 
   * @param appDesc
   */
  private void updateApp(ApplicationDescriptor appDesc, Application existedApplication) {
    //更新应用信息
    Application app = appDesc.getApp();
    int appId=existedApplication.getId();
    existedApplication.setName(app.getName());
    // 摘要信息外部设置好了
    if (!this.ruleApplicationService.updateApp(appId, existedApplication.getName(), existedApplication.getDigest())) {
      throw new IllegalStateException(String.format("应用:%s(%s)更新失败", appDesc.getApp().getAppKey(), appDesc.getApp().getName()));
    }
    
    // 根据模块标识分组，方便下文批量组装数据并执行
    int moduleCount = appDesc.getModules().size();
    List<ApplicationModule> modules = new ArrayList<>(moduleCount);
    Map<String, List<ModuleOptionDescriptor>> moduleOptionMap = new java.util.HashMap<>(moduleCount);
    Map<String, ModuleOutputDescriptor> moduleOutputMap = new HashMap<>(moduleCount);
    Map<String, List<ModuleRuleItem>> moduleItemMap = new HashMap<>(moduleCount);

    for (ModuleDescriptor md : appDesc.getModules()) {
      String moduleKey = md.getModule().getModuleKey();
      // module
      modules.add(new ApplicationModule(appId, moduleKey, md.getModule().getName()));
      // item
      moduleItemMap.put(moduleKey, md.getItems());
      // option
      moduleOptionMap.put(moduleKey, md.getOptions());
      // output
      moduleOutputMap.put(moduleKey, md.getOutput());
    }

    // 首先，批量新增所有模块，自动生成主键Id
    this.applicationModuleService.batchAddMoudules(modules);
    // 再查一次数据库，因为我们需要moduleId
    Map<String, ApplicationModule> moduleMap = this.applicationModuleService.findByAppId(appId).stream()
        .collect(Collectors.toMap(ApplicationModule::getModuleKey, Function.identity()));

    // 其次，保存所有模块可配置项
    List<ModuleRuleItem> items = new ArrayList<>(120);
    moduleItemMap.forEach((String moduleKey, List<ModuleRuleItem> ruleItems) -> {
      items.addAll(ruleItems.stream().map((item) -> {
        item.setModuleId(moduleMap.get(moduleKey).getId());
        return item;
      }).collect(Collectors.toList()));
    });
    this.moduleRuleItemService.batchAddItems(items);

    // 再次，保存所有模块条件选项信息
    List<ModuleOption> moduleOptions = new ArrayList<>(120);
    moduleOptionMap.forEach((String moduleKey, List<ModuleOptionDescriptor> options) -> {});

    // 最后，批量保存所有模块的输出信息
    saveModelOutput(moduleOutputMap, (String moduleKey) -> moduleMap.get(moduleKey).getId());
  }

  /**
   * 只处理新增应用的逻辑，不涉及更新的问题
   * 
   * @param appDesc
   */
  private void addApp(ApplicationDescriptor appDesc, String appDigest) {
    // first ，我们新增app
    int appId = this.ruleApplicationService.addApp(appDesc.getApp().getAppKey(), appDesc.getApp().getName(), appDigest);
    if (appId < 0) {
      throw new IllegalStateException(String.format("应用:%s(%s)新增失败", appDesc.getApp().getAppKey(), appDesc.getApp().getName()));
    }

    // 根据模块标识分组，方便下文批量组装数据并执行
    int moduleCount = appDesc.getModules().size();
    List<ApplicationModule> modules = new ArrayList<>(moduleCount);
    Map<String, List<ModuleOptionDescriptor>> moduleOptionMap = new java.util.HashMap<>(moduleCount);
    Map<String, ModuleOutputDescriptor> moduleOutputMap = new HashMap<>(moduleCount);
    Map<String, List<ModuleRuleItem>> moduleItemMap = new HashMap<>(moduleCount);

    for (ModuleDescriptor md : appDesc.getModules()) {
      String moduleKey = md.getModule().getModuleKey();
      // module
      modules.add(new ApplicationModule(appId, moduleKey, md.getModule().getName()));
      // item
      moduleItemMap.put(moduleKey, md.getItems());
      // option
      moduleOptionMap.put(moduleKey, md.getOptions());
      // output
      moduleOutputMap.put(moduleKey, md.getOutput());
    }

    // 首先，批量新增所有模块，自动生成主键Id
    this.applicationModuleService.batchAddMoudules(modules);
    // 再查一次数据库，因为我们需要moduleId
    Map<String, ApplicationModule> moduleMap = this.applicationModuleService.findByAppId(appId).stream()
        .collect(Collectors.toMap(ApplicationModule::getModuleKey, Function.identity()));

    // 其次，保存所有模块可配置项
    List<ModuleRuleItem> items = new ArrayList<>(120);
    moduleItemMap.forEach((String moduleKey, List<ModuleRuleItem> ruleItems) -> {
      items.addAll(ruleItems.stream().map((item) -> {
        item.setModuleId(moduleMap.get(moduleKey).getId());
        return item;
      }).collect(Collectors.toList()));
    });
    this.moduleRuleItemService.batchAddItems(items);

    // 再次，保存所有模块条件选项信息
    List<ModuleOption> moduleOptions = new ArrayList<>(120);
    moduleOptionMap.forEach((String moduleKey, List<ModuleOptionDescriptor> options) -> {});

    // 最后，批量保存所有模块的输出信息
    saveModelOutput(moduleOutputMap, (String moduleKey) -> moduleMap.get(moduleKey).getId());
  }

  /**
   * 批量保存整个应用的模块输出信息
   * 
   * @param moduleOutputMap
   * @param moduleIdProvider
   */
  private void saveModelOutput(Map<String, ModuleOutputDescriptor> moduleOutputMap, Function<String, Integer> moduleIdProvider) {
    // 模块输出 digest
    List<ModuleOutput> outputs = new ArrayList<>(30);
    List<ModuleOutputPredefinedValue> outputPredefinedValues = new ArrayList<>(100);
    List<ModuleOutputJavaBean> outputJavaBeans = new ArrayList<>(120);
    moduleOutputMap.forEach((String moduleKey, ModuleOutputDescriptor out) -> {
      ModuleOutput modelOut = out.getOutput();
      int moduleId = moduleIdProvider.apply(moduleKey);
      modelOut.setModuleId(moduleId);
      // 输出结果为java bean
      if (out.getJavaBeanFields() != null && out.getJavaBeanFields().isEmpty()) {
        modelOut.setOutputType(3);
        // 保存字段数据
        outputJavaBeans.addAll(out.getJavaBeanFields().stream().map((field) -> {
          field.setModuleId(moduleId);
          return field;
        }).collect(Collectors.toList()));

      } else if (out.getPredefinedValues() != null && !out.getPredefinedValues().isEmpty()) {
        modelOut.setOutputType(2);
        // 保存预定义的输出
        outputPredefinedValues.addAll(out.getPredefinedValues().stream().map((val) -> {
          val.setModuleId(moduleId);
          return val;
        }).collect(Collectors.toList()));

      } else {
        // 默认为需要用户手动输入
        modelOut.setOutputType(1);
      }
      outputs.add(modelOut);
    });
    this.moduleOutputService.batchAddOutputs(outputs, outputPredefinedValues, outputJavaBeans);
  }
}
