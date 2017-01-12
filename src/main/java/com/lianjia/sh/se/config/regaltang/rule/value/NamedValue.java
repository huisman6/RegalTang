package com.lianjia.sh.se.config.regaltang.rule.value;

/**
 * 有显示名称的值
 * 
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public interface NamedValue {
  /**
   * 名称
   */
  String name();

  /**
   * 实际的值，目前仅支持八种基本类型、字符串、枚举常量以及Java Bean，如果是Java Bean，Java Bean的字段只能是8种基本类型或字符串
   */
  Object value();
}
