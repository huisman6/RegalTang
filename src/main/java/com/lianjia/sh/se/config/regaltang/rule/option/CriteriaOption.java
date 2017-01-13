package com.lianjia.sh.se.config.regaltang.rule.option;

import com.lianjia.sh.se.config.regaltang.rule.value.TypeInfo;

/**
 * 条件选项，用于组成条件，默认为需要手动输入选项期望的值；
 * 
 * @see EnumerableValueOption
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public interface CriteriaOption extends TypeInfo {
  /**
   * 选项的名称，常用于对外显示
   */
  String name();

  /**
   * 用于从上下文{@code Context#get(String)}获取数据的key
   */
  String key();
}
