package com.lianjia.sh.se.config.regaltang.rule.output;

import java.util.List;
import java.util.Objects;

import com.lianjia.sh.se.config.regaltang.rule.value.EnumerableValues;
import com.lianjia.sh.se.config.regaltang.rule.value.NamedValue;

/**
 * 预定义了一些输出值，供用户选择
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public final class PredifinedValuesRuleOutput implements RuleOutput,EnumerableValues {

  private String name;
  private List<NamedValue> elements;
  
  /**
   * 预定义了一些输出值，供用户选择
   * @param name 界面显示的名称
   * @param elements 预定义的一些值，类型相同
   * @exception NullPointerException name、elements为null
   */
  public PredifinedValuesRuleOutput(String name, List<NamedValue> elements) {
    super();
    Objects.requireNonNull(name, "name不能为null");
    Objects.requireNonNull(elements, "elements不能为null");
    
    if (elements.isEmpty()) {
      Objects.requireNonNull(null, "elements至少包含一个元素");
    }
    this.name = name;
    this.elements = elements;
  }

  @Override
  public List<NamedValue> elements() {
    return elements;
  }

  @Override
  public String name() {
    return this.name;
  }

  /* 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    final int maxLen = 90;
    return "PredifinedValuesRuleOutput [name=" + name + ", elements="
        + (elements != null ? elements.subList(0, Math.min(elements.size(), maxLen)) : null) + "]";
  }

  
}
