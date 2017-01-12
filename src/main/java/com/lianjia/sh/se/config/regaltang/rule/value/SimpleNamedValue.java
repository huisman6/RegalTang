package com.lianjia.sh.se.config.regaltang.rule.value;

/**
 * 简单的值类型
 * 
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public final class SimpleNamedValue implements NamedValue, TypeInfo {
  private String name;
  private String value;


  /**
   * @param name 名称
   * @param value 值
   */
  public SimpleNamedValue(String name, String value) {
    super();
    this.name = name;
    this.value = value;
  }

  @Override
  public String name() {
    return this.name;
  }

  @Override
  public String value() {
    return this.value;
  }


  /*
   * @see com.lianjia.sh.se.config.regaltang.descriptor.NamedValue#type()
   */
  @Override
  public Type type() {
    return Type.SCALAR;
  }

  /*
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "SimpleNamedValue [name=" + name + ", value=" + value + "]";
  }



}
