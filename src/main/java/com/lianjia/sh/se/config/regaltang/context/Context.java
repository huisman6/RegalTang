package com.lianjia.sh.se.config.regaltang.context;

public interface Context {
  /**
   * 给定一个输入，返回一个值
   * 
   * @param key 给定一个
   * @return 返回输入对应的输出，可能为null;
   */
  Object get(final String optionKey);
}
