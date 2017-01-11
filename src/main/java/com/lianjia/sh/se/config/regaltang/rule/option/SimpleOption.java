package com.lianjia.sh.se.config.regaltang.rule.option;

/**
 * 简单的选项，不指定Value，由用户输入；
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public final class SimpleOption implements CriteriaOption {
   private String name;
   private String key;
   
  public SimpleOption(String name, String key) {
    super();
    this.name = name;
    this.key = key;
  }

  @Override
  public String name() {
    return this.name;
  }

  @Override
  public String key() {
    return this.key;
  }

  /* 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "SimpleOption [name=" + name + ", key=" + key + "]";
  }

}
