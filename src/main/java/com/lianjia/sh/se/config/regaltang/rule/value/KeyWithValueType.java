package com.lianjia.sh.se.config.regaltang.rule.value;

/**
 * key-value结构数据的描述信息，可以知道value的类型
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public final class KeyWithValueType implements TypeInfo {

  /**
   * key
   */
  private String key;
  /**
   * 值的类型
   */
  private Class<?> valueType;

  public KeyWithValueType(String key, Class<?> valueType) {
    super();
    this.key = key;
    this.valueType = valueType;
  }

  /**
   * @return the key
   */
  public String key() {
    return this.key;
  }


  @Override
  public Class<?> type() {
    return this.valueType;
  }

}
