package com.lianjia.sh.se.config.regaltang.rule.option;

public interface CriteriaOption {
  /**
   * 选项的名称，常用于对外显示
   */
  String name();

  /**
   * 用于从上下文{@code Context#get(String)}获取数据的key
   */
  String key();
}
