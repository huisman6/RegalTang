package com.lianjia.sh.se.config.regaltang.criteria;

import java.util.Optional;

/**
 * 等于操作符： a = b;
 * 
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public final class EqualCriteria extends BaseCriteria<Object, Object> {

  /*
   * @see com.lianjia.sh.se.fy.entrust.dynamic.Operator#name()
   */
  @Override
  public String name() {
    return "等于";
  }

  /*
   * @see com.lianjia.sh.se.fy.entrust.dynamic.Operator#identity()
   */
  @Override
  public String identity() {
    return "criteria_equals";
  }

  /*
   * @see com.lianjia.sh.se.fy.entrust.dynamic.Operator#evaluate(java.lang.Object, java.lang.Object)
   */
  @Override
  public boolean evaluate(Object input, Object expectedValue) {
    if (input == null || expectedValue == null) {
      return false;
    }
    Optional<Object> convertedValue = convertTo(input.getClass(), expectedValue);
    if (convertedValue.isPresent()) {
      return input.equals(convertedValue.get());
    }
    return false;
  }

}
