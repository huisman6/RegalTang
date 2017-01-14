package com.lianjia.sh.se.config.regaltang.model;

import java.io.Serializable;

/**
 * 业务模块支持那些选项
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public class ModuleOption implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * 主键ID
   */
  private int id;
  /**
   * 业务模块的ID
   */
  private int moduleId;
  /**
   * 选项的唯一标识，用于查找
   */
  private String optionKey;
  /**
   * 选项的类型，1=SimpleOption,2=PredifinedValuesOption
   */
  private int optionType;
  /**
   * 选项期望的数据类型，如果是预定义的选项值，则表示预定义的值都是这个类型
   */
  private String className;

  /**
   * 条件对外显示的名称
   */
  private String name;
  
  /**
   *此选项所有预定义值的摘要信息(MD5)，如果有变动，则会先清除选项已有的值，再insert所有此选项预定义的值
   */
  private String digest;

  /**
   * 排序，属于越大，越靠前
   */
  private int sort;

  public ModuleOption() {
    super();
  }
  
  

  public ModuleOption(String optionKey, String className, String name) {
    super();
    this.optionKey = optionKey;
    this.className = className;
    this.name = name;
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

  /**
   * @return the optionKey
   */
  public String getOptionKey() {
    return this.optionKey;
  }

  /**
   * @param optionKey the optionKey to set
   */
  public void setOptionKey(String optionKey) {
    this.optionKey = optionKey;
  }

  /**
   * @return the optionType
   */
  public int getOptionType() {
    return this.optionType;
  }

  /**
   * @param optionType the optionType to set
   */
  public void setOptionType(int optionType) {
    this.optionType = optionType;
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
   * @return the sort
   */
  public int getSort() {
    return this.sort;
  }

  /**
   * @param sort the sort to set
   */
  public void setSort(int sort) {
    this.sort = sort;
  }

  /* 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "ModuleOption [id=" + id + ", moduleId=" + moduleId + ", optionKey=" + optionKey + ", optionType=" + optionType + ", className="
        + className + ", name=" + name + ", sort=" + sort + "]";
  }
  
  
}
