package com.lianjia.sh.se.config.regaltang.descriptor;

/**
 * 业务模块
 * @summary 
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public final class BizModule implements ConfigDescriptor {
  /**
   * 业务显示的名称
   */
  private String name;
  /**
   * 自身模块的标识
   */
  private String identity;
  /**
   * @param app 模块所属应用
   * @param identity 模块的标识
   * @param displayName 模块在页面显示上的显示名称
   */
  public BizModule(String identity, String name) {
    super();
    this.identity = identity;
    this.name = name;
  }

  @Override
  public String name() {
    return this.name;
  }

  @Override
  public String identity() {
    return this.identity;
  }

  /* 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "BizModule [name=" + name + ", identity=" + identity + "]";
  }
  

}
