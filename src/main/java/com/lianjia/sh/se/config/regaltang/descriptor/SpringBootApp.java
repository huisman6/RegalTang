package com.lianjia.sh.se.config.regaltang.descriptor;

import java.util.List;

/**
 * 应用程序的描述信息
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public final class SpringBootApp implements ConfigDescriptor {
  private String name;
  private String identity;
  /**
   * 所有业务模块
   */
  private List<BizModule> modules;
  /**
   * 业务系统的说明 
   * @see com.lianjia.sh.se.config.regaltang.descriptor.ConfigDescriptor#getDisplayName()
   */
  @Override
  public String name() {
    return this.name;
  }

  /**  
   * 通常为spring.application.name
   * @see com.lianjia.sh.se.config.regaltang.descriptor.ConfigDescriptor#identity()
   */
  @Override
  public String identity() {
    return this.identity;
  }

  /**
   * @param identity 应用程序的标识，通常为spring.application.name
   * @param displayName 页面显示的名称
   */
  public SpringBootApp(String identity, String name) {
    super();
    this.identity = identity;
    this.name = name;
  }
  
  

  /**
   * @return the modules
   */
  public List<BizModule> getModules() {
    return this.modules;
  }

  /**
   * @param modules the modules to set
   */
  public void setModules(List<BizModule> modules) {
    this.modules = modules;
  }

  /* 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "SpringBootApp [name=" + name + ", identity=" + identity + "]";
  }
  
  

}
