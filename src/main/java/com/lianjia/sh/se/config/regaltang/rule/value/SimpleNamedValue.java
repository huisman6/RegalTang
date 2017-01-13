package com.lianjia.sh.se.config.regaltang.rule.value;

import java.util.Objects;

/**
 * 简单的值类型
 * 
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public final class SimpleNamedValue implements NamedValue {
  private String name;
  private Object value;
  private Class<?> type;


  /**
   * 仅支持类型为八种基本类型：boolean,byte,short,char,int,long,float,double和字符串String、java.util.Date、java.time.LocalDate/LocalDateTime、
   * 枚举常量的value为{@code Enum.name()}，name为枚举的显示名称;
   * 
   * @param name 名称
   * @param value 实际的值
   * @exception NullPointerException 如果name或者value为null
   * @exception IllegalArgumentException 仅支持Java8种基本类型、String、日期、枚举常量，否则报此错误
   */
  public SimpleNamedValue(String name, Object value) {
    super();
    Objects.requireNonNull(name, "name不能为null");
    Objects.requireNonNull(value, "value不能为null");

    if (!TypeInfo.isSimpleType(value.getClass())) {
      throw new IllegalArgumentException(String.format("value:%s，class:%s，仅支持Java8种基本类型、String、枚举常量", value, value.getClass().getName()));
    }
    this.name = name;
    this.type = value.getClass();
    this.value = TypeInfo.formatValue(value);
  }

  @Override
  public String name() {
    return this.name;
  }

  @Override
  public Object value() {
    return this.value;
  }


  /*
   * @see com.lianjia.sh.se.config.regaltang.descriptor.NamedValue#type()
   */
  @Override
  public Class<?> type() {
    return this.type;
  }

  /* 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "SimpleNamedValue [name=" + name + ", value=" + value + ", type=" + type + "]";
  }

}
