package com.lianjia.sh.se.config.regaltang.model;

import java.io.Serializable;

/**
 * 应用程序
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public class Application implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * 主键ID
   */
  private int id;
  /**
   * 应用程序的标识，通常为spring.application.name
   */
  private String appKey;
  /**
   * spring.application.name对外显示的名称
   */
  private String name;
  /**
   * 排序，优先级越高，值越大
   */
  private int sort;
  public Application() {
    super();
  }
  
  
  public Application(String appKey, String name) {
    super();
    this.appKey = appKey;
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
   * @return the appKey
   */
  public String getAppKey() {
    return this.appKey;
  }
  /**
   * @param appKey the appKey to set
   */
  public void setAppKey(String appKey) {
    this.appKey = appKey;
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
    return "RuleApplication [id=" + id + ", appKey=" + appKey + ", name=" + name + ", sort=" + sort + "]";
  }
  
}
