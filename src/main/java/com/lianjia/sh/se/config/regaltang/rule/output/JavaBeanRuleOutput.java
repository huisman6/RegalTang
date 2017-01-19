package com.lianjia.sh.se.config.regaltang.rule.output;

import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;

import com.lianjia.sh.se.config.regaltang.rule.value.KeyWithValueType;
import com.lianjia.sh.se.config.regaltang.rule.value.MultiKeyBasedValues;
import com.lianjia.sh.se.config.regaltang.rule.value.TypeInfo;

/**
 * 支持同时配置多个属性的值，JavaBean字段仅支持Java八种基本类型以及String、java.util.Date、java.time.LocalDate/LocalDateTime，
 * 不能包含枚举，由用户手动配置Java Bean各字段的属性值
 * 
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public final class JavaBeanRuleOutput implements RuleOutput, MultiKeyBasedValues {
  private String name;
  private Class<?> type;
  private List<KeyWithValueType> elements;

  /**
   * 支持同时配置多个属性的值，JavaBean字段仅支持Java八种基本类型以及String、java.util.Date、java.time.LocalDate/LocalDateTime，
   * 不能包含枚举，由用户手动配置Java Bean各字段的属性值
   * 
   * @param name 界面显示的名称
   * @param type JavaBean的类型
   * @exception NullPointerException 如果name或者value为null
   * @exception IllegalArgumentException type不是有效的JavaBean（至少包含一个Getter/Setter)
   */
  public JavaBeanRuleOutput(String name, Class<?> javaBeanType) {
    super();
    Objects.requireNonNull(name, "name不能为null");
    Objects.requireNonNull(javaBeanType, "type不能为null");

    PropertyDescriptor[] propertys = BeanUtils.getPropertyDescriptors(javaBeanType);
    if (propertys == null || propertys.length == 0) {
      throw new IllegalArgumentException(String.format("type:%s，不是有效的JavaBean，没找到有效字段。", type.getName()));
    }

    // 至少存在一个有效的Getter/Setter
    this.elements = Stream.of(propertys)
        .filter(
            // 必须是简单类型、并且不是枚举
            (pd) -> (pd.getPropertyType() != null && TypeInfo.isSimpleType(pd.getPropertyType()) && !pd.getPropertyType().isEnum()))
        .map((pd) -> new KeyWithValueType(pd.getName(), pd.getPropertyType())).collect(Collectors.toList());

    if (this.elements == null || this.elements.isEmpty()) {
      throw new IllegalArgumentException(
          String.format("type:%s，不是有效的JavaBean，至少包含一个类型为：Java八种基本类型以及String、java.util.Date、java.time.LocalDate/LocalDateTime的Getter/Setter",
              type.getName()));
    }

    this.name = name;
    this.type = javaBeanType;
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
   * @see com.lianjia.sh.se.config.regaltang.rule.value.MultiKeyBasedValues#elements()
   */
  @Override
  public List<KeyWithValueType> elements() {
    return null;
  }


  /*
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "ScalarRuleOutput [name=" + name + ", type=" + type + "]";
  }



}
