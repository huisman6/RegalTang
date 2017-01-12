package com.lianjia.sh.se.config.regaltang.rule.value;

/**
 * 类型信息
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public interface TypeInfo {
  /**
   * 值的类型
   */
  Type type();
  
  enum Type{
    /**
     * 类型为八种基本类型：boolean,byte,short,char,int,long,float,double和字符串String、枚举常量
     */
    SCALAR(2),
    
    /**
     * 标准Java Bean，即普通的model，字段类型仅包括八种基本类型：boolean,byte,short,char,int,long,float,double和字符串String
     */
    JAVA_BEAN(3);
    
    int type;
    private Type(int type) {
      this.type = type;
    }
  }
}
