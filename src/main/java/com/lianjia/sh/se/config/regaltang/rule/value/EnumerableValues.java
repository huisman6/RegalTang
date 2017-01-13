package com.lianjia.sh.se.config.regaltang.rule.value;

import java.util.List;

/**
 * 所代表的值类型可被枚举，包括Java枚举、基本类型的数组、或者可以被转换为数组的类的实例;
 * <br>
 * 比如Boolean值，可以转换为两个值： Boolean.True、Boolean.False
 * 
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
@FunctionalInterface
public interface EnumerableValues extends Values<List<NamedValue>>{
  /**
   * 可枚举的值，如果返回null或Empty，则表示没有值可枚举，可能需要人手动输入8种基本类型的数据等。
   */
  List<NamedValue> elements();
}
