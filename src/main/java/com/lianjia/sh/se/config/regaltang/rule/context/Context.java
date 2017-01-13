package com.lianjia.sh.se.config.regaltang.rule.context;


/**
 * 为计算条件{@code Criteria#evaluate(Object, Object)}为true或者false，
 * 提供的运行时数据。
 * @see MapBasedContext
 */
public interface Context {
  /**
   * 给定一个输入，返回一个值
   * 
   * @param key {@code Criteria.Option#getKey()}
   * @return 返回输入对应的输出，可能为null;
   */
  Object get(String optionKey);
}
