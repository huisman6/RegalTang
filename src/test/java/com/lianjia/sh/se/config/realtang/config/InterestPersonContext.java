package com.lianjia.sh.se.config.realtang.config;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.lianjia.sh.se.config.regaltang.rule.context.Context;
import com.lianjia.sh.se.config.regaltang.rule.option.CriteriaOption;
import com.lianjia.sh.se.config.regaltang.rule.option.PredefinedValuesOption;
import com.lianjia.sh.se.config.regaltang.rule.value.SimpleNamedValue;

public class InterestPersonContext  implements Context{
  public static final String MODULE_NAMESPACE = InterestPerson.class.getName();

  interface Options {
    CriteriaOption CITY = new PredefinedValuesOption("city","城市", 
        Stream.of(City.values()).map((item) -> new SimpleNamedValue(item.name(), item)).collect(Collectors.toList()));
  }

  @Override
  public Object get(String optionKey) {
    return null;
  }
}
