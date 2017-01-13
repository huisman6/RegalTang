package com.lianjia.sh.se.config.regaltang.rule.value;

import java.util.List;

/**
 * 有多个key结构的值，比如map，java bean
 * 
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public interface MultiKeyBasedValues extends Values<List<KeyWithValueType>> {

  /**
   * 只能提供key以及值的类型，但没有实际值
   */
  List<KeyWithValueType> elements();
}
