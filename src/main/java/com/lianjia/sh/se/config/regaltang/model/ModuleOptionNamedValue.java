package com.lianjia.sh.se.config.regaltang.model;

import java.io.Serializable;

/**
 * 选项有可选值的元数据信息（只针对类型为{@code EnumerableValueOption}）
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public class ModuleOptionNamedValue implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * 主键ID
   */
  private int id ;
  /**
   * 可枚举的option对应的值对外显示的名称
   */
  private String name ;
  /**
   * 实际的值，保存为字符串，值的标识
   */
  private String namedValue;
  /**
   * 选项ID
   */
  private int  optionId ;
  public ModuleOptionNamedValue() {
    super();
  }
  
  
  public ModuleOptionNamedValue(String name, String namedValue) {
    super();
    this.name = name;
    this.namedValue = namedValue;
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
   * @return the namedValue
   */
  public String getNamedValue() {
    return this.namedValue;
  }
  /**
   * @param namedValue the namedValue to set
   */
  public void setNamedValue(String namedValue) {
    this.namedValue = namedValue;
  }
  /**
   * @return the optionId
   */
  public int getOptionId() {
    return this.optionId;
  }
  /**
   * @param optionId the optionId to set
   */
  public void setOptionId(int optionId) {
    this.optionId = optionId;
  }
  
  /* 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "ModuleOptionNamedValue [id=" + id + ", name=" + name + ", namedValue=" + namedValue + ", optionId=" + optionId + "]";
  }
  
  
}
