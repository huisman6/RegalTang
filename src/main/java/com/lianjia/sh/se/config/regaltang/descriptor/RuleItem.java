package com.lianjia.sh.se.config.regaltang.descriptor;

/**
 * 一条可配置的规则项，可表示字符串、枚举等
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public class RuleItem implements ConfigDescriptor {
   private String name;
   private String identity;
  
  /**
   * @param identity 可配置规则项的唯一标识，在同一个module里必须唯一 
   * @param name  规则项的显示名称
   */
  public RuleItem(String identity, String name) {
    this.identity = identity;
    this.name = name;
  }

  /**
   * 规则的名称，界面显示
   * 
   * @return never return null;
   */
  public  String name(){
    return this.name;
  }

  /**
   * 唯一识别一条业务规则的ID<br>
   * 警告： 如果规则保存在HashSet等hash类集合内，请以identity来比较是否是同一个规则
   * @return nerver return null;
   */
  public String identity(){
    return this.identity;
  }


  /* 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "RuleItem [name=" + name + ", identity=" + identity + "]";
  }
  
}
