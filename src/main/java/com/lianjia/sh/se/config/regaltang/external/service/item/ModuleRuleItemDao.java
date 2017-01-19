package com.lianjia.sh.se.config.regaltang.external.service.item;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.lianjia.sh.se.config.regaltang.model.ModuleRuleItem;

/**
 * 模块有那些可配置的选项
 * 
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
@Repository
@Lazy
/* non-public */ class ModuleRuleItemDao {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  /**
   * 根据配置项ID查询
   */
  public ModuleRuleItem findById(int id) {
    return this.jdbcTemplate.queryForObject("select id,moduleId,name,itemKey from t_rule_application_module_item where id =? AND status=1",
        ModuleRuleItem.class, id);
  }

  /**
   * 根据模块Id查找应用所有的有效配置项信息
   */
  public List<ModuleRuleItem> findByModuleId(int moduleId) {
    return this.jdbcTemplate.queryForList(
        "select id,moduleId,name,itemKey from t_rule_application_module_item where moduleId =? AND status=1", ModuleRuleItem.class,
        moduleId);
  }

  /**
   * 更新模块的配置项信息
   * 
   * @param id 配置项的ID
   * @param newName 新的名称
   */
  public boolean updateRuleItemById(int id, String newName) {
    return this.jdbcTemplate.update("update t_rule_application_module_item set name=? where id=? AND status=1", newName, id) > 0;
  }

  /**
   * 根据模块ID和itemKey更新模块配置项
   * 
   * @param moduleId 模块ID
   * @param newName 新的名称
   * @param itemKey 配置项的key
   */
  public boolean updateRuleItemByKey(int moduleId, String itemKey, String newName) {
    return this.jdbcTemplate.update("update t_rule_application_module_item set name=? where moduleId=? AND itemKey=? AND status=1", newName,
        moduleId, itemKey) > 0;
  }

  /**
   * 批量新增配置项
   * @param items
   */
  public void batchAddItems(Collection<ModuleRuleItem> items) {
    this.jdbcTemplate.batchUpdate("insert into t_rule_application_module_item(itemKey,name,moduleId,status)values(?,?,?,1)", items, 100,
        new ParameterizedPreparedStatementSetter<ModuleRuleItem>() {
          @Override
          public void setValues(PreparedStatement ps, ModuleRuleItem argument) throws SQLException {
            ps.setString(1, argument.getItemKey());
            ps.setString(2, argument.getName());
            ps.setInt(3,argument.getModuleId());
          }
        });
  }
}
