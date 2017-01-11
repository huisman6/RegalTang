package com.lianjia.sh.se.config.regaltang.rule.criteria;

import java.util.Set;

/**
 * 条件的可选值为多个
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public interface MultiValueCriteria<Input, ExpectedValue> extends Criteria<Input, Set<ExpectedValue>> {
  /**
   * 给定的输入值input和待比较的expectedValue，判断它们的关系，如果满足条件，则返回true，否则，返回false；
   * @param input 运行时输入值，通常为单个值；
   * @param expectedValue 期望的值，是一个Set，运行时需要转换为input对应的类型;
   * @author Huisman
   * @since 2017年1月6日 上午9:51:04
   */
  boolean evaluate(Input input, final Set<ExpectedValue> expectedValue);
}
