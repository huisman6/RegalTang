package com.lianjia.sh.se.config.regaltang.rule.output;

/**
 * 规则的输出
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public interface RuleOutput {
  /**
   * 用于描述模块的输出，比如权益方-有效出租方，如果配置规则的话，其输出结果为double，称做分佣比率；<br>
   * name就是分佣比率，用于页面展示；
   */
  String name();
}
