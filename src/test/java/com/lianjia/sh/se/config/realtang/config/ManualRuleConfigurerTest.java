package com.lianjia.sh.se.config.realtang.config;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.lianjia.sh.se.config.regaltang.config.ManualRuleConfigurer;
import com.lianjia.sh.se.config.regaltang.rule.item.RuleItem;
import com.lianjia.sh.se.config.regaltang.rule.option.PredefinedValuesOption;
import com.lianjia.sh.se.config.regaltang.rule.output.ScalarRuleOutput;
import com.lianjia.sh.se.config.regaltang.rule.value.SimpleNamedValue;

public class ManualRuleConfigurerTest {

  @Test
  public void build() {
    //@formatter:off
      ManualRuleConfigurer.configurer()
      .appName("房源")
      .withModule()
          .identity(InterestPersonContext.module)
          .name("权益方")
          .ruleItems(
              Stream.of(InterestPerson.values())
              .map((item) -> new RuleItem(String.valueOf(item.getCode()),item.name()))
              .collect(Collectors.toList())
           )
          .availableOptions(
             new PredefinedValuesOption(InterestPersonContext.Options.CITY
               , "城市", Stream.of(City.values()).map((item) -> 
               new SimpleNamedValue(item.name(), item))
               .collect(Collectors.toList()))
           )
          .output(new ScalarRuleOutput("分佣比率",Double.class))
          .and()
          .print();
     //@formatter:on
  }
}
