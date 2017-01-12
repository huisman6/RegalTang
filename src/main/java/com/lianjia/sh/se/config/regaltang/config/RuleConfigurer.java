package com.lianjia.sh.se.config.regaltang.config;

/**
 * 当前应用规则的配置，包含一个或多个业务模块的配置；<br>
 * 业务模块的配置包含三部分：可配置项（{@code RuleItem}、可用的条件选项（{@code Criteria.Option}）、模块的输出结果
 * 
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public interface RuleConfigurer<RuleConfigurerImp> {
  /**
   * 设置spring.application.name在配置界面对外显示的名称
   * 
   * @param appName spring.application.name对外显示的名称
   */
  public RuleConfigurerImp appName(String appName);

  /**
   * 开始配置当前应用的业务配置模块；
   * <p>
   * 业务模块的配置包含三部分：可配置项（{@code RuleItem}、可用的条件选项（{@code CriteriaOption}）、模块的输出结果
   * </p>
   */
  ModuleConfigurer<RuleConfigurerImp> withModule();
}
