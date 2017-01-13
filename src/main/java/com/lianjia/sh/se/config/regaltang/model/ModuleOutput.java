package com.lianjia.sh.se.config.regaltang.model;

import java.io.Serializable;

/**
 * 模块的输出
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public class ModuleOutput  implements Serializable{
  private static final long serialVersionUID = 1L;
  /**
   * 主键ID
   */
  private int id;
  
  /**
   * --规则输出的类型，1=ScalarRuleOutput,2=PredifinedValuesRuleOutput，3=JavaBeanRuleOutput
   * 类型为2和3的输出，可能有多个配置项
   */
  private int outputType;
  /**
   * 页面显示的名称
   */
  private String name;
  /**
   * 规则输出期望的类型
   */
  private String className;
  /**
   * 模块的ID
   */
  private int moduleId;
  
  public ModuleOutput() {
    super();
  }
  
  

  public ModuleOutput(String name, String className) {
    super();
    this.name = name;
    this.className = className;
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
   * @return the outputType
   */
  public int getOutputType() {
    return this.outputType;
  }

  /**
   * @param outputType the outputType to set
   */
  public void setOutputType(int outputType) {
    this.outputType = outputType;
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
   * @return the className
   */
  public String getClassName() {
    return this.className;
  }

  /**
   * @param className the className to set
   */
  public void setClassName(String className) {
    this.className = className;
  }

  /**
   * @return the moduleId
   */
  public int getModuleId() {
    return this.moduleId;
  }

  /**
   * @param moduleId the moduleId to set
   */
  public void setModuleId(int moduleId) {
    this.moduleId = moduleId;
  }

  /* 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "ModuleOutput [id=" + id + ", outputType=" + outputType + ", name=" + name + ", className=" + className + ", moduleId="
        + moduleId + "]";
  }
  
}
