package com.lianjia.sh.se.config.regaltang.external.service.application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.lianjia.sh.se.config.regaltang.model.Application;

/**
 * 应用的信息
 * 
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
  public Application findByKey(String appKey) {
    try {
      return this.jdbcTemplate.queryForObject("select id,appKey,sort,name from t_rule_application where appKey =? AND status=1",
          Application.class, appKey);
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  /**
   * 更新应用的name信息
   * 
   * @param id 应用的ID
   * @param newName 新的名称
   * @param digest 新的摘要信息
   */
  public boolean updateAppById(int id, String newName, String digest) {
    return this.jdbcTemplate.update("update t_rule_application set name=?,digest=? where id=?", newName, digest, id) > 0;
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

  /**
   * 新增应用信息，如果成功，返回主键ID，否则返回-1
   * 
   * @param appKey 应用的标识
   * @param appName 显示名称
   */
  public int insert(String appKey, String appName, String digest) {
    KeyHolder identity = new GeneratedKeyHolder();
    int rows = this.jdbcTemplate.update(new PreparedStatementCreator() {
      public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
            "insert into t_rule_application(appKey,name,digest,status,sort) values(?,?,?,1,0)", new String[] {"appKey", "name", "digest"});
        ps.setString(1, appKey);
        ps.setString(2, appName);
        ps.setString(3, digest);
        return ps;
      }
    }, identity);
    if (rows > 0) {
      return identity.getKey().intValue();
    }
    return -1;
  }
}
