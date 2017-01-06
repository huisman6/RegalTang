package com.lianjia.sh.se.config.regaltang.criteria;

import java.util.Comparator;
import java.util.Optional;

/**
 * 输入值和期望值可比较大小的条件，输入值必须实现Comparator或者Comparable，比如 a > b ，a >=b ,a<=b
 * 
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
abstract class ComparableCriteria extends BaseCriteria<Object, Object> {

  @SuppressWarnings("unchecked")
  @Override
  public boolean evaluate(Object input, Object expectedValue) {
    if (input == null || expectedValue == null) {
      return false;
    }
    // 比较大小，必须实现 Comparator或者Comparable
    if (input instanceof Comparable || input instanceof Comparator) {
      Optional<Object> result = convertTo(input.getClass(), expectedValue);
      if (result.isPresent()) {
        // 如果转换成功，则开始比较
        if (input instanceof Comparable) {
          Comparable<Object> comparable = Comparable.class.cast(input);
          return doComparable(comparable, result.get());
        } else if (input instanceof Comparator) {
          Comparator<Object> comparator = Comparator.class.cast(input);
          return doComparator(comparator, result.get());
        }
      }
    }
    return false;
  }

  /**
   * 如果输入值实现了Comparable接口，则调用此方法决定两个参数的大小关系
   * 
   * @param input 输入值
   * @param expectedValue 期望的值
   */
  protected abstract boolean doComparable(Comparable<Object> input, Object expectedValue);

  /**
   * 如果输入值实现了Comparator接口，则调用此方法决定两个参数的大小关系
   * 
   * @param input 输入值
   * @param expectedValue 期望的值
   */
  abstract boolean doComparator(Comparator<Object> input, Object expectedValue);

}
