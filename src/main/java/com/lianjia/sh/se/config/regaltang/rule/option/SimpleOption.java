package com.lianjia.sh.se.config.regaltang.rule.option;

import java.util.Objects;

import com.lianjia.sh.se.config.regaltang.rule.value.TypeInfo;

/**
 * 简单的选项，不指定Value，由用户输入；
 * 
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public final class SimpleOption implements CriteriaOption {
  private String name;
  private String key;
  private Class<?> type;

  /**
   * 简单的选项，仅支持八种基本类型、字符串，不指定Value，由用户输入；
   * 
   * @param type 用户可输入数据的类型
   * @param name 选项对外显示的名称
   * @param key 用于{@code Context#get(String)}查找运行时数据
   * @exception NullPointerException 如果type、name、key为null
   * @exception IllegalArgumentException 如果type不是八种基本类型、字符串
   */
  public SimpleOption(Class<?> type, String name, String key) {
    super();
    Objects.requireNonNull(type, "class type不能为null");
    Objects.requireNonNull(name, "name不能为null");
    Objects.requireNonNull(key, "key不能为null");
    
    if (!TypeInfo.isSimpleType(type)) {
      throw new IllegalArgumentException(String.format("type:%s，仅限八种基本类型及字符串", type.getName()));
    }
    this.name = name;
    this.key = key;
    this.type = type;
  }

  @Override
  public String name() {
    return this.name;
  }

  @Override
  public String key() {
    return this.key;
  }

  /**
   * @return 简单选项可输入数据的类型
   */
  public Class<?> type() {
    return this.type;
  }

  /* 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "SimpleOption [name=" + name + ", key=" + key + ", type=" + type + "]";
  }

  

}
