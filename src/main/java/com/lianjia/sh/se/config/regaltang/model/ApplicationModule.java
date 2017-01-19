package com.lianjia.sh.se.config.regaltang.model;

import java.io.Serializable;

/**
 * 应用程序的模块
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public class ApplicationModule implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * 模块主键
   */
  private int id;
  /**
   * 模块所属应用的ID
   */
  private int appId;
  /**
   * 模块的命名空间，唯一标识，在同一个appId里唯一
   */
  private String moduleKey;
  /**
   * 模块对外显示的名称
   */
  private String name;
  /**
   * 排序，优先级越高，值越大
   */
  private int sort;
  public ApplicationModule() {
    super();
  }
  
  
  public ApplicationModule(String moduleKey, String name) {
    super();
    this.moduleKey = moduleKey;
    this.name = name;
  }


  public ApplicationModule(int appId, String moduleKey, String name) {
    super();
    this.appId = appId;
    this.moduleKey = moduleKey;
    this.name = name;
  }


  /**
   * @return the id
   */
  public int getId() {
    return this.id;
  }
  /**
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }
  /**
   * @return the appId
   */
  public int getAppId() {
    return this.appId;
  }
  /**
   * @param appId the appId to set
   */
  public void setAppId(int appId) {
    this.appId = appId;
  }
  /**
   * @return the moduleKey
   */
  public String getModuleKey() {
    return this.moduleKey;
  }
  /**
   * @param moduleKey the moduleKey to set
   */
  public void setModuleKey(String moduleKey) {
    this.moduleKey = moduleKey;
  }
  /**
   * @return the name
   */
  public String getName() {
    return this.name;
  }
  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }
  /**
   * @return the sort
   */
  public int getSort() {
    return this.sort;
  }
  /**
   * @param sort the sort to set
   */
  public void setSort(int sort) {
    this.sort = sort;
  }
  /* 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "RuleApplicationModule [id=" + id + ", appId=" + appId + ", moduleKey=" + moduleKey + ", name=" + name + ", sort=" + sort + "]";
  }
  
}
