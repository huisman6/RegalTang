package com.lianjia.sh.se.config.regaltang.rule.output;

import java.util.Objects;

import com.lianjia.sh.se.config.regaltang.rule.value.TypeInfo;

/**
 * 简单输出，仅支持Java八种基本类型以及String，需要用户手动填写规则的输出；手动输入的数据都不支持枚举；
 * 
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public final class ScalarRuleOutput implements RuleOutput {
  private String name;
  private Class<?> type;

  /**
   * 简单输出，仅支持Java八种基本类型以及String、java.util.Date、java.time.LocalDate/LocalDateTime，不能包含枚举，需要用户手动填写规则的输出
   * @param name 界面显示的名称
   * @param type 业务规则输出结果的类型
   * @exception NullPointerException 如果name或者value为null
   * @exception IllegalArgumentException 仅支持Java8种基本类型、String、java.util.Date、java.time.LocalDate/LocalDateTime，否则报此错误
   */
  public ScalarRuleOutput(String name, Class<?> outputType) {
    super();
    Objects.requireNonNull(name, "name不能为null");
    Objects.requireNonNull(type, "type不能为null");

    if (!TypeInfo.isSimpleType(type) || type.isEnum()) {
      throw new IllegalArgumentException(String.format("outputType:%s，仅支持Java8种基本类型、String、java.util.Date、java.time.LocalDate/LocalDateTime", type.getName()));
    }
    this.type=outputType;
    this.name = name;
  }


  @Override
  public String name() {
    return this.name;
  }


  /* 
   * @see com.lianjia.sh.se.config.regaltang.rule.value.TypeInfo#type()
   */
  @Override
  public Class<?> type() {
    return this.type;
  }

  /* 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "ScalarRuleOutput [name=" + name + ", type=" + type + "]";
  }


  


}
