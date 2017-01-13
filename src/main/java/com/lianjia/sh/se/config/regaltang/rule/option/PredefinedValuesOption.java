package com.lianjia.sh.se.config.regaltang.rule.option;

import java.util.List;
import java.util.Objects;

import com.lianjia.sh.se.config.regaltang.rule.value.NamedValue;
import com.lianjia.sh.se.config.regaltang.rule.value.SimpleNamedValue;
import com.lianjia.sh.se.config.regaltang.rule.value.TypeInfo;

/**
 * 预定义值的选项, 仅支持类型为八种基本类型：boolean,byte,short,char,int,long,float,double和字符串String、java.util.Date、java.time.LocalDate/LocalDateTime、
 * 枚举常量;预定义的数据都包含枚举；
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public final class PredefinedValuesOption implements EnumerableValueOption {
  private String name;
  private String key;
  private Class<?> type;
  private List<NamedValue> elements;

  /**
   * 包含预定义值的选项，仅支持类型为八种基本类型：boolean,byte,short,char,int,long,float,double和字符串String、java.util.Date、java.time.LocalDate/LocalDateTime、
   * 枚举常量；
   * @param key 选项的标识，用于从{@code Context#get(String)}里获取运行时数据
   * @param name 选项显示的名称
   * @param elements 选项内置的值，类型相同
   * @see SimpleNamedValue
   * @exception NullPointerException key、name、elements如果为空
   * @exception IllegalArgumentException  仅支持类型为八种基本类型：boolean,byte,short,char,int,long,float,double和字符串String、java.util.Date、java.time.LocalDate/LocalDateTime、
   * 枚举常量
   */
  public PredefinedValuesOption(String key, String name, List<NamedValue> elements) {
    super();
    Objects.requireNonNull(name, "name不能为null");
    Objects.requireNonNull(key, "key不能为null");
    Objects.requireNonNull(elements, "elements不能为null");
    
    if (elements.isEmpty()) {
      Objects.requireNonNull(null, "elements至少包含一个元素");
    }
    
    this.type=elements.get(0).type();
    if (!TypeInfo.isSimpleType(type)) {
      throw new IllegalArgumentException("elements类型不符合要求");
    }
    this.name = name;
    this.key = key;
    this.elements = elements;
  }

  @Override
  public String name() {
    return this.name;
  }

  @Override
  public String key() {
    return this.key;
  }

  /* 
   * @see com.lianjia.sh.se.config.regaltang.rule.value.TypeInfo#type()
   */
  @Override
  public Class<?> type() {
    return this.type;
  }

  @Override
  public List<NamedValue> elements() {
    return this.elements;
  }

  /* 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    final int maxLen = 90;
    return "PredefinedValuesOption [name=" + name + ", key=" + key + ", elements="
        + (elements != null ? elements.subList(0, Math.min(elements.size(), maxLen)) : null) + "]";
  }

}
