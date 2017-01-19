package com.lianjia.sh.se.config.regaltang.external.service.module;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.lianjia.sh.se.config.regaltang.model.Application;
import com.lianjia.sh.se.config.regaltang.model.ApplicationModule;

/**
 * 应用的模块信息
 * 
 * @summary
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
@Repository
@Lazy
/* non-public */ class ApplicationModuleDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  /**
   * 根据模块ID查找模块信息
   */
  public Application findById(int id) {
    return this.jdbcTemplate.queryForObject("select id,appId,name,sort,moduleKey from t_rule_application_module where id =? AND status=1",
        Application.class, id);
  }

  /**
   * 根据应用Id查找应用所有有效模块信息
   */
  public List<ApplicationModule> findByAppId(int appId) {
    return this.jdbcTemplate.queryForList("select id,appId,name,sort,moduleKey from t_rule_application_module where appId =? AND status=1",
        ApplicationModule.class, appId);
  }

  /**
   * 根据模块ID更新应用的基本信息
   * 
   * @param id 模块的ID
   * @param newName 新的名称
   * @param newSort 新的排序
   */
  public boolean updateModuleById(int id, String newName, int newSort) {
    return this.jdbcTemplate.update("update t_rule_application_module set name=?,sort=? where id=?", newName, newSort, id) > 0;
  }

  /**
   * 根据应用模块的唯一标识moduleKey更新应用的基本信息
   * 
   * @param id 应用的ID
   * @param newName 新的名称
   * @param newSort 新的排序
   */
  public boolean updateModuleByKey(int appId, String moduleKey, String newName, int newSort) {
    return this.jdbcTemplate.update("update t_rule_application_module set name=?,sort=? where appId=? AND moduleKey=? ", newName, newSort,
        appId, moduleKey) > 0;
  }

  /**
   * 批量新增应用的模块信息
   * 
   * @param items
   */
  public void batchAddModules(Collection<ApplicationModule> modules) {
    this.jdbcTemplate.batchUpdate("insert into t_rule_application_module(appId,name,moduleKey,status)values(?,?,?,1)", modules, 100,
        new ParameterizedPreparedStatementSetter<ApplicationModule>() {
          @Override
          public void setValues(PreparedStatement ps, ApplicationModule argument) throws SQLException {
            ps.setInt(1, argument.getAppId());
            ps.setString(2, argument.getName());
            ps.setString(3, argument.getModuleKey());
          }
        });
  }
}
