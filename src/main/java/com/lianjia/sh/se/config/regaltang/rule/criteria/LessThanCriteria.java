package com.lianjia.sh.se.config.regaltang.rule.criteria;

import java.util.Comparator;

/**
 * 比较输入值是否小于期望的值
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public final class LessThanCriteria  extends ComparableCriteria{

  @Override
  public String name() {
    return "小于";
  }

  @Override
  public String key() {
    return "criteria_less_than";
  }

  @Override
  protected boolean doComparable(Comparable<Object> input, Object expectedValue) {
    return input.compareTo(expectedValue) < 0;
  }

  @Override
  boolean doComparator(Comparator<Object> input, Object expectedValue) {
    return input.compare(input, expectedValue) <0;
  }

}
