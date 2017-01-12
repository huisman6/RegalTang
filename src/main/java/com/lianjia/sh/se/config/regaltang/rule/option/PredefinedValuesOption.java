package com.lianjia.sh.se.config.regaltang.rule.option;

import java.util.List;

import com.lianjia.sh.se.config.regaltang.rule.value.NamedValue;
import com.lianjia.sh.se.config.regaltang.rule.value.SimpleNamedValue;

/**
 * 预定义值的选项
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public final class PredefinedValuesOption implements EnumerableValueOption {
  private String name;
  private String key;
  private List<NamedValue> elements;

  /**
   * @param key 选项的标识，用于从{@code Context#get(String)}里获取运行时数据
   * @param name 选项显示的名称
   * @param elements 选项内置的值
   * @see SimpleNamedValue
   */
  public PredefinedValuesOption(String key, String name, List<NamedValue> elements) {
    super();
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
