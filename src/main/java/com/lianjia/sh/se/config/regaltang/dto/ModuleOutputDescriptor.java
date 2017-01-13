package com.lianjia.sh.se.config.regaltang.dto;

import java.util.List;

import com.lianjia.sh.se.config.regaltang.model.ModuleOutput;
import com.lianjia.sh.se.config.regaltang.model.ModuleOutputJavaBean;
import com.lianjia.sh.se.config.regaltang.model.ModuleOutputPredefinedValue;

/**
 * ModuleOutputPredefinedValue 和ModuleOutputJavaBean不会同时共存<br>
 * 模块输出，仅用作数据传输和json反序列化，不用于缓存等其他业务
 * 
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public class ModuleOutputDescriptor {
  /**
   * 模块的输出
   */
  private ModuleOutput output;
  /**
   * 如果有预定义的值的话
   */
  private List<ModuleOutputPredefinedValue> predefinedValues;
  /**
   * 模块的输出为javaBean，可配置多个字段
   */
  private List<ModuleOutputJavaBean> javaBeanFields;
  public ModuleOutputDescriptor() {
    super();
  }
  
  
  public ModuleOutputDescriptor(ModuleOutput output) {
    super();
    this.output = output;
  }


  /**
   * @return the output
   */
  public ModuleOutput getOutput() {
    return this.output;
  }
  /**
   * @param output the output to set
   */
  public void setOutput(ModuleOutput output) {
    this.output = output;
  }
  /**
   * @return the predefinedValues
   */
  public List<ModuleOutputPredefinedValue> getPredefinedValues() {
    return this.predefinedValues;
  }
  /**
   * @param predefinedValues the predefinedValues to set
   */
  public void setPredefinedValues(List<ModuleOutputPredefinedValue> predefinedValues) {
    this.predefinedValues = predefinedValues;
  }
  /**
   * @return the javaBeanFields
   */
  public List<ModuleOutputJavaBean> getJavaBeanFields() {
    return this.javaBeanFields;
  }
  /**
   * @param javaBeanFields the javaBeanFields to set
   */
  public void setJavaBeanFields(List<ModuleOutputJavaBean> javaBeanFields) {
    this.javaBeanFields = javaBeanFields;
  }
  /* 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    final int maxLen = 90;
    return "ModuleOutputDescriptor [output=" + output + ", predefinedValues="
        + (predefinedValues != null ? predefinedValues.subList(0, Math.min(predefinedValues.size(), maxLen)) : null) + ", javaBeanFields="
        + (javaBeanFields != null ? javaBeanFields.subList(0, Math.min(javaBeanFields.size(), maxLen)) : null) + "]";
  }
  
  
}
