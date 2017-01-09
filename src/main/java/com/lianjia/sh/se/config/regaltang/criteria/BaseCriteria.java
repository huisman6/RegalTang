package com.lianjia.sh.se.config.regaltang.criteria;

import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

/**
 * 提供参数的转换，日志等；
 * 
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public abstract class BaseCriteria<Input, ExpectedValue> implements Criteria<Input, ExpectedValue> {
  protected Logger logger = LoggerFactory.getLogger(this.getClass());
  /**
   * 负责数据转换
   */
  private final ConversionService conversionService;

  public BaseCriteria() {
    super();
    this.conversionService = new DefaultConversionService();
  }
  

  /* 
   * @see com.lianjia.sh.se.config.regaltang.criteria.Criteria#order()
   */
  @Override
  public int order() {
    return 0;
  }


  /* 
   * @see com.lianjia.sh.se.config.regaltang.criteria.Criteria#evaluate(java.lang.Object, java.lang.Object)
   */
  @Override
  public boolean evaluate(Input input, ExpectedValue expectedValue) {
    // TODO Auto-generated method stub
    return false;
  }


  /**
   * 将某个{@code source}转换为指定类型{@code targetClass}<br>
   * 如果Optional.isPresent()==false，则转换失败，否则标识转换成功
   * 
   * @param targetClass 想转换为那个类型，not null
   * @param source 输入值，not null
   * @author Huisman
   * @since 2017年1月6日 下午2:20:28
   */
  protected Optional<Object> convertTo(Class<?> targetClass, Object source) {
    Objects.requireNonNull(targetClass, "target class is null");
    Objects.requireNonNull(source, "source is null");
    //如果类型相同，则不用转换
    if (targetClass.isInstance(source)) {
      return Optional.of(source);
    }
    if (this.conversionService.canConvert(source.getClass(), targetClass)) {
      try {
        return Optional.ofNullable(this.conversionService.convert(source, targetClass));
      } catch (Exception e) {
        logger.error(String.format("convert expected value: %s(class=%s) to class: %s", source, source.getClass(), targetClass.getName()),
            e);
      }
    }
    return Optional.empty();
  }
  
  

  /* 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return this.getClass().getSimpleName()+"[name=" + this.name() + ", key=" + this.key()+", order="+this.order() + "]";
  }
}
