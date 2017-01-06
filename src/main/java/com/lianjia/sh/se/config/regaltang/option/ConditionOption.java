package com.lianjia.sh.se.config.regaltang.option;

import java.io.Serializable;

/**
 * 条件选项
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public final class ConditionOption implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * 条件的名称，常用于对外显示
   */
  private String name;
  /**
   * 用于从上下文信息里获取数据的key
   */
  private String key;
  
  /**
   * 描述
   */
  private String description;

  /**
   * @param name 名称
   * @param key 用于从上下文信息里获取运行时数据
   * @param description 选项的描述
   */
  public ConditionOption(String name, String key, String description) {
    super();
    this.name = name;
    this.key = key;
    this.description = description;
  }

  /**
   * @param name 名称，用于展示
   * @param key 用于从上下文信息里获取运行时数据
   */
  public ConditionOption(String name, String key) {
    super();
    this.name = name;
    this.key = key;
  }
  
  

  /**
   * 条件选项的描述
   * @return the description
   */
  public String getDescription() {
    return this.description;
  }


  /**
   * 条件的名称，常用于对外显示
   * @return the name
   */
  public String getName() {
    return this.name;
  }

  /**
   * 用于从上下文信息里获取数据的key
   * @return the key
   */
  public String getKey() {
    return this.key;
  }

  /* 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "ConditionOption [name=" + name + ", key=" + key + ", description=" + description + "]";
  }
  
  
  
  
}
