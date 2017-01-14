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
   * 模块的ID，主键ID，一个模块只能对应一个输出
   */
  private int moduleId;
  
  /**
   * 输出所有预定义值或者JavaBean类型的输出信息的摘要信息(MD5)，如果有变动，则会先清除选项已有的值，再insert所有的值
   */
  private String digest;
  
  public ModuleOutput() {
    super();
  }
  
  

  public ModuleOutput(String name, String className) {
    super();
    this.name = name;
    this.className = className;
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
   * @return the digest
   */
  public String getDigest() {
    return this.digest;
  }



  /**
   * @param digest the digest to set
   */
  public void setDigest(String digest) {
    this.digest = digest;
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
    return "ModuleOutput [outputType=" + outputType + ", name=" + name + ", className=" + className + ", moduleId=" + moduleId + ", digest="
        + digest + "]";
  }

  
  
}
