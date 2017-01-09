package com.lianjia.sh.se.config.regaltang.criteria;

/**
 * 组成业务规则的条件
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public interface Criteria<Input, ExpectedValue> {
  /**
   * 条件的名称，对外展示
   * 
   * @author Huisman
   * @since 2017年1月6日 上午9:52:19
   */
  String name();

  /**
   * Criteria的唯一标识，用于区分不同条件
   */
  String key();

  /**
   * 用于条件排序，值越大界限显示越靠前
   */
  int order();

  /**
   * 给定的输入值input和待比较的expectedValue，判断它们的关系，如果满足条件，则返回true，否则，返回false；
   * 
   * @param input 运行时输入值，通常为单个值；
   * @param expectedValue 期望的值，可能是单个值，也可能是一个Set，List，运行时需要转换为input对应的类型;
   * @author Huisman
   * @since 2017年1月6日 上午9:51:04
   */
  boolean evaluate(Input input, final ExpectedValue expectedValue);

  /**
   * 为计算条件{@code Criteria#evaluate(Object, Object)}为true或者false，
   * 提供的运行时数据。
   */
  public interface Context {
    /**
     * 给定一个输入，返回一个值
     * 
     * @param key {@code Criteria.Option#getKey()}
     * @return 返回输入对应的输出，可能为null;
     */
    Object get(final String optionKey);
  }


  /**
   * 组成条件的一系列选项
   */
  public interface Option {
    /**
     * 选项的名称，常用于对外显示
     */
    String name();

    /**
     * 用于从上下文{@code Criteria.Context#get(String)}获取数据的key
     */
    String key();
  }



}
