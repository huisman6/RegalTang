package com.lianjia.sh.se.config.regaltang.rule.context;

import java.util.HashMap;
import java.util.Map;

import com.lianjia.sh.se.config.regaltang.rule.option.CriteriaOption;

/**
 * 基于Map的实现
 * 
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public abstract class MapBasedContext<SubType extends MapBasedContext<SubType>> implements Context {
  private Map<String, Object> inputData;

  protected MapBasedContext() {
    super();
    inputData = new HashMap<>();
  }

  /**
   * 新增运行时数据
   * 
   * @param option 那个条件选项
   * @param value 实际运行中传递的值
   */
  protected final void optionData(CriteriaOption option, Object value) {
    this.inputData.put(option.key(), value);
  }

  /**
   * 新增运行时数据
   * @param key 条件选项的key
   * @param value 运行时传递的数据
   */
  protected final void optionData(String key, Object value) {
    this.inputData.put(key, value);
  }

  /*
   * @see com.lianjia.sh.se.config.regaltang.rule.context.Context#get(java.lang.String)
   */
  @Override
  public final Object get(String optionKey) {
    return this.inputData.get(optionKey);
  }


}
