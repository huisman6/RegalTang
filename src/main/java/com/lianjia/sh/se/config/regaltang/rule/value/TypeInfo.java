package com.lianjia.sh.se.config.regaltang.rule.value;

import org.springframework.util.ClassUtils;

/**
 * 类型信息
 * 
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public interface TypeInfo {
  /**
   * 判断一个class是否为简单类型，仅包括Java八种基本类型(boolean,byte,short,char,int,long,float,double)
   * 和字符串以及枚举常量；
   * @param clazz
   * @author Huisman
   * @since 2017年1月12日 下午3:24:02
   */
  static boolean isSimpleType(Class<?> clazz) {
    return (ClassUtils.isPrimitiveOrWrapper(clazz) || clazz.isEnum() || String.class == clazz);
  }

  /**
   * 类型信息
   */
  Class<?> type();
}
