package com.lianjia.sh.se.config.realtang.config;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lianjia.sh.se.config.regaltang.config.ManualRuleConfigurer;
import com.lianjia.sh.se.config.regaltang.dto.ApplicationDescriptor;
import com.lianjia.sh.se.config.regaltang.rule.item.RuleItem;
import com.lianjia.sh.se.config.regaltang.rule.output.ScalarRuleOutput;

public class ManualRuleConfigurerTest {

  @Test
  public void build() {
    //@formatter:off
    ApplicationDescriptor appDescriptor=  ManualRuleConfigurer.singleton()
      .appName("房源")
      .withModule()
          .identity(InterestPersonContext.MODULE_NAMESPACE)
          .name("权益方")
          .ruleItems(
              Stream.of(InterestPerson.values())
              .map((item) -> new RuleItem(String.valueOf(item.getCode()),item.name()))
              .collect(Collectors.toList())
           )
          .availableOptions(
             InterestPersonContext.Options.CITY
           )
          .output(new ScalarRuleOutput("分佣比率",Double.class))
          .and().springApplicationName("fy-entrust-server")
          .done();
      
     ObjectMapper mapper=new ObjectMapper();
     try {
     System.out.println(mapper.writeValueAsString(appDescriptor));
    } catch (JsonProcessingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
     //@formatter:on
  }
  
  @Test
  public void cotext(){
  }
}
