package com.lianjia.sh.se.config.regaltang.criteria;

/**
 * 条件
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public interface Criteria<Input,ExpectedValue> {
  /**
   * 条件的名称，对外展示
   * @author Huisman
   * @since 2017年1月6日 上午9:52:19
   */
  String name();
  
  /**
   * Criteria的唯一标识，用于区分不同条件
   */
  String identity();
  /**
   * 给定的输入值input和待比较的expectedValue，判断它们的关系，如果满足条件，则返回true，否则，返回false；
   * @param input 运行时输入值，通常为单个值；
   * @param expectedValue 期望的值，可能是单个值，也可能是一个Set，List，运行时需要转换为input对应的类型;
   * @author Huisman
   * @since 2017年1月6日 上午9:51:04
   */
  boolean evaluate(Input input,final ExpectedValue expectedValue);
}
