package com.lianjia.sh.se.config.regaltang.external.service.application;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.util.DigestUtils;

import com.lianjia.sh.se.config.regaltang.dto.ApplicationDescriptor;
import com.lianjia.sh.se.config.regaltang.dto.ModuleDescriptor;
import com.lianjia.sh.se.config.regaltang.dto.ModuleOptionDescriptor;
import com.lianjia.sh.se.config.regaltang.model.Application;
import com.lianjia.sh.se.config.regaltang.model.ApplicationModule;
import com.lianjia.sh.se.config.regaltang.model.ModuleOption;
import com.lianjia.sh.se.config.regaltang.model.ModuleOptionPredefinedValue;
import com.lianjia.sh.se.config.regaltang.model.ModuleOutputJavaBean;
import com.lianjia.sh.se.config.regaltang.model.ModuleOutputPredefinedValue;
import com.lianjia.sh.se.config.regaltang.model.ModuleRuleItem;

public final class AppDescriptorValidator {
  /**
   * 验证当前应用的数据是否符合要求
   * @exception IllegalArgumentException 校验未通过
   */
   static final void validate(ApplicationDescriptor appDescriptor) {
    Application app=appDescriptor.getApp();
    if (app == null) {
      throw new IllegalArgumentException("app为空");
    }
    if (isNullOrTrimedEmpty(app.getAppKey()) || isNullOrTrimedEmpty(app.getName())) {
      throw new IllegalArgumentException("app key(spring.application.name) 或 name为空");
    }

    if (appDescriptor.getModules() == null || appDescriptor.getModules().isEmpty()) {
      throw new IllegalArgumentException(String.format("app:%s(name=%s)，未指定模块信息", app.getAppKey(),app.getName()));
    }
    Set<String> moduleIdentitys = new HashSet<>();
    // ruleItem的name
    for (ModuleDescriptor module : appDescriptor.getModules()) {
      ApplicationModule appModule = module.getModule();
      if (appModule == null || isNullOrTrimedEmpty(appModule.getModuleKey()) || isNullOrTrimedEmpty(appModule.getName())) {
        throw new IllegalArgumentException(String.format("app:%s(name=%s),module的moduleKey或name为空", app.getAppKey(), app.getName()));
      }
      if (moduleIdentitys.contains(appModule.getModuleKey())) {
        throw new IllegalArgumentException(String.format("app:%s(name=%s),module:%s(name=%s)重复", app.getAppKey(),app.getName(),
            appModule.getModuleKey(), appModule.getName()));
      }
      moduleIdentitys.add(appModule.getModuleKey());
      
      // 可配置项
      final List<ModuleRuleItem> configItems = module.getItems();
      if (configItems == null || configItems.isEmpty()) {
        throw new IllegalArgumentException(String.format("app:%s(name=%s)，module:%s(name=%s)，未指定配置项",app.getAppKey(),
            app.getName(), appModule.getModuleKey(), appModule.getName()));
      }

      Set<String> itemIdentitys = new HashSet<>();
      for (ModuleRuleItem item : configItems) {
        if (isNullOrTrimedEmpty(item.getItemKey()) || isNullOrTrimedEmpty(item.getName())) {
          throw new IllegalArgumentException(String.format("app:%s(name=%s)，module:%s(name=%s)，配置项RuleItem的itemKey或name为空",
              app.getAppKey(), app.getName(), appModule.getModuleKey(), appModule.getName()));
        }
        if (itemIdentitys.contains(item.getItemKey())) {
          throw new IllegalArgumentException(String.format("app:%s(name=%s)，module:%s(name=%s)，ruleItem:%s(name=%s)重复",
             app.getAppKey(), app.getName(), appModule.getModuleKey(), appModule.getName(), item.getItemKey(), item.getName()));
        }
        itemIdentitys.add(item.getItemKey());
      }

      // 条件选项
      final List<ModuleOptionDescriptor> options = module.getOptions();
      if (options == null || options.isEmpty()) {
        throw new IllegalArgumentException(String.format("app:%s(name=%s)，module:%s(name=%s)，未指定条件项", app.getAppKey(),
            app.getName(), appModule.getModuleKey(), appModule.getName()));
      }

      // 条件选项
      Set<String> optionIdentitys = new HashSet<>();
      for (ModuleOptionDescriptor option : options) {
        ModuleOption mo = option.getOption();
        if (mo == null || isNullOrTrimedEmpty(mo.getOptionKey())) {
          throw new IllegalArgumentException(String.format("app:%s(name=%s)，module:%s(name=%s)，条件项optionKey为空", app.getAppKey(),
              app.getName(), appModule.getModuleKey(), appModule.getName()));
        }

        if (isNullOrTrimedEmpty(mo.getClassName())) {
          throw new IllegalArgumentException(String.format("app:%s(name=%s)，module:%s(name=%s)，条件项：%s(name=%s) className为空",
              app.getAppKey(), app.getName(), appModule.getModuleKey(), appModule.getName(), mo.getOptionKey(), mo.getName()));
        }
        if (optionIdentitys.contains(mo.getOptionKey())) {
          throw new IllegalArgumentException(String.format("app:%s(name=%s)，module:%s(name=%s)，条件项：%s(name=%s) 重复", app.getAppKey(),
              app.getName(), appModule.getModuleKey(), appModule.getName(), mo.getOptionKey(), mo.getName()));
        }

        // 条件选项预定义的值，必须包含name和value
        if (option.getPredefinedValues() != null && !option.getPredefinedValues().isEmpty()) {
          for (ModuleOptionPredefinedValue val : option.getPredefinedValues()) {
            if (isNullOrTrimedEmpty(val.getName()) || isNullOrTrimedEmpty(val.getPredefinedValue())) {
              throw new IllegalArgumentException(
                  String.format("app:%s(name=%s)，module:%s(name=%s)，条件项：%s(name=%s)包含预定义的值，但预定义的值的name或value或者className为空", app.getAppKey(),
                      app.getName(), appModule.getModuleKey(), appModule.getName(), mo.getOptionKey(), mo.getName()));
            }
          }
        }
        optionIdentitys.add(mo.getOptionKey());
      }

      // 模块输出没有配置
      if (module.getOutput() == null || module.getOutput().getOutput() == null) {
        throw new IllegalArgumentException(String.format("app:%s(name=%s)，module:%s(name=%s)，输出（output)没有配置", app.getAppKey(),
            app.getName(), appModule.getModuleKey(), appModule.getName()));
      }
      // 如果为javabean输出，必须指定字段名和类型
      if (module.getOutput().getJavaBeanFields() != null && !module.getOutput().getJavaBeanFields().isEmpty()) {
        for (ModuleOutputJavaBean bean : module.getOutput().getJavaBeanFields()) {
          if (isNullOrTrimedEmpty(bean.getFieldClassName()) || isNullOrTrimedEmpty(bean.getFieldName())) {
            throw new IllegalArgumentException(
                String.format("app:%s(name=%s)，module:%s(name=%s)，输出（output)类型为JavaBean，但是field的name或className为空",app.getAppKey(),
                    app.getName(), appModule.getModuleKey(), appModule.getName()));
          }
        }
      }
      // 如果为预定义的输出，必须指定name和value
      if (module.getOutput().getPredefinedValues() != null && !module.getOutput().getPredefinedValues().isEmpty()) {
        for (ModuleOutputPredefinedValue val : module.getOutput().getPredefinedValues()) {
          if (isNullOrTrimedEmpty(val.getName()) || isNullOrTrimedEmpty(val.getValue())) {
            throw new IllegalArgumentException(String.format("app:%s(name=%s)，module:%s(name=%s)，输出（output)类型为预定义的值，但是name或value为空",
                app.getAppKey(), app.getName(), appModule.getModuleKey(), appModule.getName()));
          }
        }
      }
    }
  }
   
   /**
    * 计算摘要信息
   * @param input
   */
  public static String md5Digest(String input){
     return DigestUtils.md5DigestAsHex(input.getBytes());
   }

  /**
   * 如果source为null或者source.trime.isEmpty，返回true
   * 
   * @param source
   */
  private static boolean isNullOrTrimedEmpty(String source) {
    return source == null || source.trim().isEmpty();
  }
}
