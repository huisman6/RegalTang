package com.lianjia.sh.se.config.regaltang.rule.criteria;

import java.util.Set;
import java.util.stream.Collectors;

import com.lianjia.sh.se.config.regaltang.util.ConverterUtil;

/**
 * 输入值是任意可选值之一，类似SQL 的IN；
 * 
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public final class InAnyCriteria implements MultiValueCriteria<Object, Object> {

  @Override
  public String name() {
    return "包含";
  }


  /*
   * @see com.lianjia.sh.se.fy.entrust.dynamic.Operator#identity()
   */
  @Override
  public String key() {
    return "criteria_in_any";
  }



  /*
   * @see com.lianjia.sh.se.config.regaltang.criteria.BaseCriteria#order()
   */
  @Override
  public int order() {
    return 800;
  }


  @Override
  public boolean evaluate(Object input, final Set<Object> expectedValues) {
    if (input == null || expectedValues == null || expectedValues.isEmpty()) {
      return false;
    }
    //@formatter:off
    //转换expectedValues为指定类型
    return expectedValues.stream()
          .map((source) -> ConverterUtil.convertTo(input.getClass(), source))
          .filter(source -> source.isPresent())
          .collect(Collectors.mapping((source)-> source.get(), Collectors.toSet()))
          .contains(input);
    //@formatter:on
  }

}
