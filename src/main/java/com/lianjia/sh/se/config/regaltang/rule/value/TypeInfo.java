package com.lianjia.sh.se.config.regaltang.rule.value;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.util.ClassUtils;

/**
 * 类型信息
 * 
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public interface TypeInfo {
  /**
   * java.util.Date、java.time.LocalDate使用yyyy-MM-dd的格式保存为日期字符串
   * 
   */
  String DATE_VALUE_FORMAT = "yyyy-MM-dd";

  /**
   * ava.time.LocalDateTime使用yyyy-MM-dd HH:mm:ss的格式保存为日期字符串
   */
  String DATE_TIME_VALUE_FORMAT = "yyyy-MM-dd HH:mm:ss";

  /**
   * 判断一个class是否为简单类型，仅包括Java八种基本类型(boolean,byte,short,char,int,long,float,double)
   * 和字符串、枚举常量、java.util.Date、java.time.LocalDate、java.time.LocalDateTime；<br>
   * 注意：java.util.Date、java.time.LocalDate仅表示年月日，而java.time.LocalDateTime可表示时分秒
   * 
   * @param clazz
   * @author Huisman
   * @since 2017年1月12日 下午3:24:02
   */
  static boolean isSimpleType(Class<?> clazz) {
    return (ClassUtils.isPrimitiveOrWrapper(clazz) || clazz.isEnum() || String.class == clazz || clazz == LocalDate.class
        || clazz == LocalDateTime.class || clazz == Date.class);
  }

  /**
   * 枚举元素实际保存的值为其name()，java.util.Date&java.time.LocalDate保存为yyyy-MM-dd字符串；<br>
   * java.time.LocalDateTime保存为yyyy-MM-dd HH:mm:ss格式的字符串
   * @author Huisman
   */
  static Object formatValue(Object value) {
    //
    Class<?> type = value.getClass();
    // 如果是枚举，保存的值为枚举的name
    if (type.isEnum()) {
      return ((Enum<?>) value).name();
    } else if (Date.class.isAssignableFrom(type) || LocalDate.class.isAssignableFrom(type)) {
      // 年月日
      LocalDate date = (Date.class.isAssignableFrom(type)) ? Date.class.cast(value).toLocalDate() : LocalDate.class.cast(value);
      return date.format(DateTimeFormatter.ofPattern(DATE_VALUE_FORMAT));
    } else if (LocalDateTime.class.isAssignableFrom(type)) {
      // 年月日时分秒
      return LocalDateTime.class.cast(value).format(DateTimeFormatter.ofPattern(DATE_TIME_VALUE_FORMAT));
    }
    return value;
  }

  /**
   * 类型信息
   */
  Class<?> type();
}
