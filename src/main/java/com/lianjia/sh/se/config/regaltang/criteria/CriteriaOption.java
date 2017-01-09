package com.lianjia.sh.se.config.regaltang.criteria;

import java.io.Serializable;

public class CriteriaOption implements Criteria.Option, Serializable {
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
  public CriteriaOption(String name, String key, String description) {
    super();
    this.name = name;
    this.key = key;
    this.description = description;
  }

  /**
   * @param name 名称，用于展示
   * @param key 用于从上下文信息里获取运行时数据
   */
  public CriteriaOption(String name, String key) {
    this(name, key, "");
  }


  /**
   * 条件选项的描述
   * 
   * @return the description
   */
  public String description() {
    return this.description;
  }


  /**
   * 条件的名称，常用于对外显示
   * 
   * @return the name
   */
  public String name() {
    return this.name;
  }

  /**
   * 用于从上下文信息里获取数据的key
   * 
   * @return the key
   */
  public String key() {
    return this.key;
  }

  /*
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "CriteriaOption [name=" + name + ", key=" + key + ", description=" + description + "]";
  }
}
