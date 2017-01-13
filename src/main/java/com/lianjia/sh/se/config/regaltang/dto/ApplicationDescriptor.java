package com.lianjia.sh.se.config.regaltang.dto;

import java.util.ArrayList;
import java.util.List;

import com.lianjia.sh.se.config.regaltang.model.Application;


/**
 * 应用程序描述信息，仅用作数据传输和json反序列化，不用于缓存等其他业务
 * 
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public class ApplicationDescriptor {
  /**
   * 应用的信息
   */
  private Application app;

  /**
   * 应用所有的模块
   */
  private List<ModuleDescriptor> modules;

  public ApplicationDescriptor() {
    super();
    this.modules = new ArrayList<>();
  }



  /**
   * @return the app
   */
  public Application getApp() {
    return this.app;
  }



  /**
   * @param app the app to set
   */
  public void setApp(Application app) {
    this.app = app;
  }



  /**
   * @return the modules
   */
  public List<ModuleDescriptor> getModules() {
    return this.modules;
  }



  /**
   * @param modules the modules to set
   */
  public void setModules(List<ModuleDescriptor> modules) {
    if (modules == null || modules.isEmpty()) {
      return;
    }
    this.modules = (modules);
  }
  
  /**
   * 新增一个module
   * @param module
   */
  public void addModule(ModuleDescriptor module){
    if (module == null) {
      return ;
    }
    this.modules.add(module);
  }



  /*
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    final int maxLen = 90;
    return "ApplicationDescriptor [app=" + app + ", modules="
        + (modules != null ? modules.subList(0, Math.min(modules.size(), maxLen)) : null) + "]";
  }

}
