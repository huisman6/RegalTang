package com.lianjia.sh.se.config.regaltang.config;

import java.util.List;

import com.lianjia.sh.se.config.regaltang.descriptor.RuleItem;
import com.lianjia.sh.se.config.regaltang.rule.option.CriteriaOption;
import com.lianjia.sh.se.config.regaltang.rule.option.EnumerableValueOption;
import com.lianjia.sh.se.config.regaltang.rule.option.PredefinedValueOption;
import com.lianjia.sh.se.config.regaltang.rule.option.SimpleOption;
import com.lianjia.sh.se.config.regaltang.rule.output.RuleOutput;

/**
 * 业务模块的配置包含三部分：可配置项（{@code RuleItem}、可用的条件选项（{@code Criteria.Option}）、模块的输出结果
 * 
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public interface ModuleConfigurer {
  /**
   * 当前模块的命名空间，用于区分不同模块，必须指定，在同一个应用内不能重复;
   */
  ModuleConfigurer identity(String identity);
  
  /**
   * 模块名，推荐指定，用于页面显示
   */
  ModuleConfigurer name(String name);
  /**
   * 返回{@code RuleConfigurer}，可继续配置其他业务模块等
   */
  RuleConfigurer and();

  /**
   * 指定本模块有那些可配置的项，可以将任意配置项（字符串、枚举等）map成RuleItem；<br>
   * 对于数组类型，可通过 {@code Arrays.asList(RuleItem)}转换为List或者通过流Collectors.toList();<br>
   * <p>
   * 注意: RuleItem的identity同一个模块不能重复；
   * <p>
   * @exception IllegalArgumentException 如果ruleItems为null或者empty
   */
  ModuleConfigurer ruleItems(List<RuleItem> ruleItems);

  /**
   * 本模块支持那些条件选项，条件选项可以组成多个条件，比如业务条件：city = 上海 And entrustType=出售，city和entrustType就是条件选项；<br>
   * 有些条件选项的值是固定的（预设），比如city，其可选值只能是枚举CityEnum，我们可以使用{@code EnumerableValueOption}
   * 来表达此种类型的选项，预定义的一些值，供用户选择；
   * <p>
   * 注意: CriteriaOption的key同一个模块不能重复；
   * <p>
   * @exception IllegalArgumentException 如果options为null或者empty
   * @see EnumerableValueOption
   * @see SimpleOption
   * @see PredefinedValueOption
   */
  ModuleConfigurer availableOptions(List<CriteriaOption> options);
  
  /**
   * 业务模块可能产生的输出，一条配置项可能输出预定义的值，也可能是由用户手动输入的值<br>
   * 
   * @see EnumerableValueOption
   * @exception IllegalArgumentException 如果output为null
   */
  ModuleConfigurer output(RuleOutput output);
}
