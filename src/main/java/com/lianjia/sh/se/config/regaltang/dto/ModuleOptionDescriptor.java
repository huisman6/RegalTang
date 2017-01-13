package com.lianjia.sh.se.config.regaltang.dto;

import java.util.List;

import com.lianjia.sh.se.config.regaltang.model.ModuleOption;
import com.lianjia.sh.se.config.regaltang.model.ModuleOptionNamedValue;

/**
 * 模块选项的描述，仅用作数据传输和json反序列化，不用于缓存等其他业务
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public class ModuleOptionDescriptor {
   /**
   * 如果有预定义的选项值的话
   */
  private List<ModuleOptionNamedValue> predefinedValues;
   /**
   * 条件选项
   */
  private ModuleOption option;
  public ModuleOptionDescriptor() {
    super();
  }
  
  
  /**
   * @param option
   */
  public ModuleOptionDescriptor(ModuleOption option) {
    super();
    this.option = option;
  }


  /**
   * @return the predefinedValues
   */
  public List<ModuleOptionNamedValue> getPredefinedValues() {
    return this.predefinedValues;
  }
  /**
   * @param predefinedValues the predefinedValues to set
   */
  public void setPredefinedValues(List<ModuleOptionNamedValue> predefinedValues) {
    this.predefinedValues = predefinedValues;
  }
  /**
   * @return the option
   */
  public ModuleOption getOption() {
    return this.option;
  }
  /**
   * @param option the option to set
   */
  public void setOption(ModuleOption option) {
    this.option = option;
  }
  /* 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    final int maxLen = 90;
    return "ModuleOptionDescriptor [predefinedValues="
        + (predefinedValues != null ? predefinedValues.subList(0, Math.min(predefinedValues.size(), maxLen)) : null) + ", option=" + option
        + "]";
  }
  
  
}
