package com.lianjia.sh.se.config.regaltang.model;

import java.io.Serializable;

/**
 * 规则的输出为预定义的元数据信息（只针对类型为PredifinedValuesRuleOutput）
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public class ModuleOutputPredefinedValue implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * 主键ID
   */
  private int id; 
  /**
   * 可枚举的output对应的值对外显示的名称
   */
  private String name ;
  /**
   * 实际的值，保存为字符串
   */
  private String value;
  /**
   * 输出值所关联的output的ID
   */
  private int outputId;
  public ModuleOutputPredefinedValue() {
    super();
  }
  
  public ModuleOutputPredefinedValue(String name, String value) {
    super();
    this.name = name;
    this.value = value;
  }

  /**
   * @return the id
   */
  public int getId() {
    return this.id;
  }
  /**
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }
  /**
   * @return the name
   */
  public String getName() {
    return this.name;
  }
  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }
  /**
   * @return the value
   */
  public String getValue() {
    return this.value;
  }
  /**
   * @param value the value to set
   */
  public void setValue(String value) {
    this.value = value;
  }
  /**
   * @return the outputId
   */
  public int getOutputId() {
    return this.outputId;
  }
  /**
   * @param outputId the outputId to set
   */
  public void setOutputId(int outputId) {
    this.outputId = outputId;
  }
  /* 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "ModuleOutputPredifinedValue [id=" + id + ", name=" + name + ", value=" + value + ", outputId=" + outputId + "]";
  }
  
}
