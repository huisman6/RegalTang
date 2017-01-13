package com.lianjia.sh.se.config.regaltang.model;

/**
 * 某个模块可配置的项
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public class ModuleRuleItem {
  /**
   * 主键ID
   */
  private int id;
  /**
   * 模块的ID
   */
  private int moduleId;
  /**
   * 配置项对外显示的名称
   */
  private String name;
  /**
   * 配置项的唯一标识，用于查找，在同一个module里唯一
   */
  private String itemKey;
  
  public ModuleRuleItem() {
    super();
  }
  
  public ModuleRuleItem(String itemKey, String name) {
    super();
    this.itemKey = itemKey;
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
   * @return the moduleId
   */
  public int getModuleId() {
    return this.moduleId;
  }
  /**
   * @param moduleId the moduleId to set
   */
  public void setModuleId(int moduleId) {
    this.moduleId = moduleId;
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
   * @return the itemKey
   */
  public String getItemKey() {
    return this.itemKey;
  }
  /**
   * @param itemKey the itemKey to set
   */
  public void setItemKey(String itemKey) {
    this.itemKey = itemKey;
  }
  /* 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "RuleApplicaitonModuleItem [id=" + id + ", moduleId=" + moduleId + ", name=" + name + ", itemKey=" + itemKey + "]";
  }
  
  
}
