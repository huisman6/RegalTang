package com.lianjia.sh.se.config.regaltang.model;

import java.io.Serializable;

/**
 * 规则输出为JavaBean的元数据信息（只针对类型为JavaBeanRuleOutput）
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public class ModuleOutputJavaBean implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * ID
   */
  private int id;
  /**
   * 字段的名称
   */
  
  private String fieldName;
  /**
   * 字段的数据类型
   */
  private String fieldClassName ;
  /**
   * 输出结果的ID
   */
  
  private int outputId ;
  public ModuleOutputJavaBean() {
    super();
  }
  
  public ModuleOutputJavaBean(String fieldName, String fieldClassName) {
    super();
    this.fieldName = fieldName;
    this.fieldClassName = fieldClassName;
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
   * @return the fieldName
   */
  public String getFieldName() {
    return this.fieldName;
  }
  /**
   * @param fieldName the fieldName to set
   */
  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }
  /**
   * @return the fieldClassName
   */
  public String getFieldClassName() {
    return this.fieldClassName;
  }
  /**
   * @param fieldClassName the fieldClassName to set
   */
  public void setFieldClassName(String fieldClassName) {
    this.fieldClassName = fieldClassName;
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
    return "ModuleOutputJavaBean [id=" + id + ", fieldName=" + fieldName + ", fieldClassName=" + fieldClassName + ", outputId=" + outputId
        + "]";
  }
  
  
}
