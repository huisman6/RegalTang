package com.lianjia.sh.se.config.regaltang.criteria;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * 输入值是任意可选值之一，类似SQL 的IN；
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public final class InAnyCriteria extends BaseCriteria<Object, Set<Object>> {

  @Override
  public String name() {
    return "包含";
  }
  

  /* 
   * @see com.lianjia.sh.se.fy.entrust.dynamic.Operator#identity()
   */
  @Override
  public String identity() {
    return "criteria_in_any";
  }


  @Override
  public boolean evaluate(Object input, final Set<Object> expectedValues) {
    if (input == null || expectedValues==null || expectedValues.isEmpty()) {
      return false;
    }
    //@formatter:off
    //转换expectedValues为指定类型
    return expectedValues.stream()
          .map((source) -> convertTo(input.getClass(), source))
          .filter(source -> source.isPresent())
          .collect(Collectors.mapping((source)-> source.get(), Collectors.toSet()))
          .contains(input);
    //@formatter:on
  }

}
