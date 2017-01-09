package com.lianjia.sh.se.config.regaltang.descriptor;

/**
 * 一条可配置的规则项
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public class RuleItem implements ConfigDescriptor {
   private String name;
   private String identity;
   private int priority;
   
  
  /**
   * @param identity 可配置规则项的标识
   * @param name 用于展示的名称
   * @param priority 优先级，默认为0
   */
  public RuleItem(String identity, String name, int priority) {
    super();
    this.identity = identity;
    this.name = name;
    this.priority = priority;
  }
  
  /**
   * @param identity 可配置规则项的标识 
   * @param name  规则项的名称
   */
  public RuleItem(String identity, String name) {
    this(identity, name, 0);
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

  /**
   * 业务规则的优先级，值越大，优先级越高；
   * 通常来说，越具体的规则优先级越高;
   * 一般的业务规则默认为0即可；
   */
  public int priority(){
    return this.priority;
  }

  /* 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "RuleItem [name=" + name + ", identity=" + identity + ", priority=" + priority + "]";
  }
  
}
