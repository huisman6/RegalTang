package com.lianjia.sh.se.config.regaltang.util;

import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

/**
 * 负责数据转换
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public class ConverterUtil {
  private static Logger logger = LoggerFactory.getLogger(ConverterUtil.class);
  /**
   * 负责数据转换
   */
  private final static ConversionService CONVERSION_SERVICE=new DefaultConversionService();
  
  private ConverterUtil() {
    throw new UnsupportedOperationException("ConverterUtil can't be instantiate via reflect or method-handle");
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
  public final static Optional<Object> convertTo(Class<?> targetClass, Object source) {
    Objects.requireNonNull(targetClass, "target class is null");
    Objects.requireNonNull(source, "source is null");
    //如果类型相同，则不用转换
    if (targetClass.isInstance(source)) {
      return Optional.of(source);
    }
    if (CONVERSION_SERVICE.canConvert(source.getClass(), targetClass)) {
      try {
        return Optional.ofNullable(CONVERSION_SERVICE.convert(source, targetClass));
      } catch (Exception e) {
        //ignored 
        logger.error(String.format("convert expected value: %s(class=%s) to class: %s", source, source.getClass(), targetClass.getName()),
          e);
      }
    }
    return Optional.empty();
  }
}
