package com.lianjia.sh.se.config.regaltang.model;

import java.io.Serializable;

/**
 * 条件
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public class RuleCriteria implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * 主键ID
   */
  private Integer id;
  /**
   * 条件的标识
   */
  private String criteria;
  /**
   * 条件的名称
   */
  private String name;
  /**
   * 条件是否有多个可选值
   */
  private int multiValue;
  /**
   * 排序，优先级越高，值越大
   */
  private int sort;
  
  public RuleCriteria() {
    super();
  }
  

  /**
   * @return the id
   */
  public Integer getId() {
    return this.id;
  }


  /**
   * @param id the id to set
   */
  public void setId(Integer id) {
    this.id = id;
  }


  /**
   * @return the criteria
   */
  public String getCriteria() {
    return this.criteria;
  }


  /**
   * @param criteria the criteria to set
   */
  public void setCriteria(String criteria) {
    this.criteria = criteria;
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
   * @return the multiValue
   */
  public int getMultiValue() {
    return this.multiValue;
  }


  /**
   * @param multiValue the multiValue to set
   */
  public void setMultiValue(int multiValue) {
    this.multiValue = multiValue;
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
    return "RuleCriteria [id=" + id + ", criteria=" + criteria + ", name=" + name + ", multiValue=" + multiValue + ", sort=" + sort + "]";
  }
  
  
}
