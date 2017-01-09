package com.lianjia.sh.se.config.regaltang.criteria;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * 条件注册，我们手动注册，不采用自动扫描注册
 * 
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public final class CriteriaRegistrar {
  private final static Map<String, Criteria<?, ?>> CRITERIA_MAP = new HashMap<>();

  /**
   * 手动注册支持的条件
   * 
   * @author Huisman
   */
  public static synchronized void manualRegister() {
    //@formatter:off
    Arrays.asList(
      new InAnyCriteria(), 
      new EqualCriteria(),
      new GreaterThanCriteria(),
      new GreaterThanOrEqualCriteria(),
      new LessThanCriteria(),
      new LessThanOrEqualCriteria()
      )
      .stream()
      .forEach(
          (
              criteria) -> CRITERIA_MAP.put(criteria.key(), criteria)
          );
    
    //@formatter:on
  }

  /**
   * 当前激活的条件
   * 
   * @author Huisman
   * @since 2017年1月9日 上午11:29:06
   */
  public static Collection<Criteria<?, ?>> activeCriterias() {
    return Collections.unmodifiableCollection(CRITERIA_MAP.values());
  }

  /**
   * 根据条件表达式的identity来查找对应的条件
   * @param criteriaIdentity 条件的标识
   * @since 2017年1月9日 上午11:31:53
   */
  public static Optional<Criteria<?, ?>> lookup(String criteriaIdentity) {
    return Optional.ofNullable(CRITERIA_MAP.get(criteriaIdentity));
  }
}
