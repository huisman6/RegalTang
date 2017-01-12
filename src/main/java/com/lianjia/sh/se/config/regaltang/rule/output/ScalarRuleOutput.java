package com.lianjia.sh.se.config.regaltang.rule.output;

/**
 * 简单输出，仅支持Java八种基本类型以及String，需要用户手动填写规则的输出
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public class ScalarRuleOutput implements RuleOutput {
  private String name;
  
  /**
   * @param name 界面显示的名称
   */
  public ScalarRuleOutput(String name) {
    super();
    this.name = name;
  }


  @Override
  public String name() {
    return this.name;
  }


  /* 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "ScalarRuleOutput [name=" + name + "]";
  }

  
}
