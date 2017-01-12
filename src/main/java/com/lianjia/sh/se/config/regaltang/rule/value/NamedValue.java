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
   * 实际的值，转换为字符串存储，如果是Java Bean，可以考虑序列化成JSON
   */
  String value();
}
