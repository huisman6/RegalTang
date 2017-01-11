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
   * 实际的值，字符串表达方式，如果是Java Bean，可以考虑序列化成JSON
   */
  String value();
  
  /**
   * 值的类型
   */
  Type type();
  
  enum Type{
    /**
     * 类型为八种基本类型：boolean,byte,short,char,int,long,float,double和字符串String、枚举常量
     */
    SCALAR(2);
    
    int type;
    private Type(int type) {
      this.type = type;
    }
  }
}
