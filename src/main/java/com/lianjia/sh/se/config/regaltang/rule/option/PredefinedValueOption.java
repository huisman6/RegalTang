package com.lianjia.sh.se.config.regaltang.rule.option;

import java.util.List;

import com.lianjia.sh.se.config.regaltang.rule.value.NamedValue;
import com.lianjia.sh.se.config.regaltang.rule.value.SimpleNamedValue;

/**
 * 预定义值的选项
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public final class PredefinedValueOption implements EnumerableValueOption {
  private String name;
  private String key;
  private List<NamedValue> elements;

  /**
   * @param key 选项的标识，用于从{@code Criteria.Context}里获取运行时数据
   * @param name 选项显示的名称
   * @param elements 选项内置的值
   * @see SimpleNamedValue
   */
  public PredefinedValueOption(String key, String name, List<NamedValue> elements) {
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

}
