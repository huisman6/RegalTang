package com.lianjia.sh.se.config.regaltang.dto;

import java.util.List;

import com.lianjia.sh.se.config.regaltang.model.ApplicationModule;
import com.lianjia.sh.se.config.regaltang.model.ModuleRuleItem;

/**
 * 模块的描述信息，仅用作数据传输和json反序列化，不用于缓存等其他业务
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public class ModuleDescriptor {
  /**
   * 模块的信息
   */
  private ApplicationModule module;
  /**
   *模块的选项 
   */
  private List<ModuleOptionDescriptor> options;
  /**
   * 模块输出
   */
  private ModuleOutputDescriptor output;
  /**
   * 可配置项
   */
  private List<ModuleRuleItem> items;
  public ModuleDescriptor() {
    super();
  }
  
  /**
   * @return the options
   */
  public List<ModuleOptionDescriptor> getOptions() {
    return this.options;
  }

  /**
   * @param options the options to set
   */
  public void setOptions(List<ModuleOptionDescriptor> options) {
    this.options = options;
  }

  /**
   * @return the output
   */
  public ModuleOutputDescriptor getOutput() {
    return this.output;
  }

  /**
   * @param output the output to set
   */
  public void setOutput(ModuleOutputDescriptor output) {
    this.output = output;
  }

  /**
   * @return the items
   */
  public List<ModuleRuleItem> getItems() {
    return this.items;
  }

  /**
   * @param items the items to set
   */
  public void setItems(List<ModuleRuleItem> items) {
    this.items = items;
  }
  
  

  /**
   * @return the module
   */
  public ApplicationModule getModule() {
    return this.module;
  }

  /**
   * @param module the module to set
   */
  public void setModule(ApplicationModule module) {
    this.module = module;
  }

  /* 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    final int maxLen = 90;
    return "ModuleDescriptor [module=" + module + ", options="
        + (options != null ? options.subList(0, Math.min(options.size(), maxLen)) : null) + ", output=" + output + ", items="
        + (items != null ? items.subList(0, Math.min(items.size(), maxLen)) : null) + "]";
  }

  
  
  
}
