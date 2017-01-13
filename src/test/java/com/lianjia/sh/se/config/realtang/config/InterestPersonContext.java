package com.lianjia.sh.se.config.realtang.config;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.lianjia.sh.se.config.regaltang.rule.context.MapBasedContext;
import com.lianjia.sh.se.config.regaltang.rule.option.CriteriaOption;
import com.lianjia.sh.se.config.regaltang.rule.option.PredefinedValuesOption;
import com.lianjia.sh.se.config.regaltang.rule.value.SimpleNamedValue;

/**
 * 权益方信息
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public class InterestPersonContext extends MapBasedContext<InterestPersonContext> {
  /**
   * 权益方模块的命名空间
   */
  public static final String MODULE_NAMESPACE = InterestPerson.class.getName();

  /**
   * 选项
   */
  interface Options {
    CriteriaOption CITY = new PredefinedValuesOption("city", "城市",
        Stream.of(City.values()).map((item) -> new SimpleNamedValue(item.name(), item)).collect(Collectors.toList()));
    CriteriaOption Privilege = new PredefinedValuesOption("city", "权限",
      Stream.of(City.values()).map((item) -> new SimpleNamedValue(item.name(), item)).collect(Collectors.toList()));

  }

  /**
   * 城市
   */
  public InterestPersonContext city(City city) {
    super.optionData(Options.CITY, city);
    return this;
  }
  
}
