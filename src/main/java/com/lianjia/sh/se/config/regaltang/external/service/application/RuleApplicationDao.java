package com.lianjia.sh.se.config.regaltang.external.service.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lianjia.sh.se.config.regaltang.model.Application;

/**
 * 应用的信息
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
@Repository
@Lazy
/* non-public */class RuleApplicationDao {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  /**
   * 根据应用ID查找应用
   */
  public Application findById(int id) {
    return this.jdbcTemplate.queryForObject("select id,appKey,sort,name from t_rule_application where id =? AND status=1",
        Application.class, id);
  }

  /**
   * 根据应用key查询
   */
  public Application findByKey(int appKey) {
    return this.jdbcTemplate.queryForObject("select id,appKey,sort,name from t_rule_application where appKey =? AND status=1",
        Application.class, appKey);
  }

  /**
   * 更新应用的name信息
   * 
   * @param id 应用的ID
   * @param newName 新的名称
   * @param newSort 新的排序
   */
  public boolean updateAppById(int id, String newName, int newSort) {
    return this.jdbcTemplate.update("update t_rule_application set name=?,sort=? where id=?", newName, newSort, id) > 0;
  }

  /**
   * 根据应用的唯一标识appKey更新应用的基本信息
   * 
   * @param id 应用的ID
   * @param newName 新的名称
   * @param newSort 新的排序
   */
  public boolean updateAppByAppKey(String appKey, String newName, int newSort) {
    return this.jdbcTemplate.update("update t_rule_application set name=?,sort=? where appKey=?", newName, newSort, appKey) > 0;
  }

}
