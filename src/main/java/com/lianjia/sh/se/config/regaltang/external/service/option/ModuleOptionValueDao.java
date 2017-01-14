package com.lianjia.sh.se.config.regaltang.external.service.option;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lianjia.sh.se.config.regaltang.model.ModuleOptionNamedValue;

/**
 * 选项预定义的值，已不影响已经配置好的条件，不过还是建议保持选项向下兼容（只增不减）
 * 
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
@Repository
@Lazy
/* non-public */class ModuleOptionValueDao {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  /**
   * 根据选项值的ID查找选项值的信息
   */
  public ModuleOptionNamedValue findById(int id) {
    return this.jdbcTemplate.queryForObject(
        "select id,optionId,namedValue,className,name from t_rule_application_module_option_named_value where id =? AND status=1",
        ModuleOptionNamedValue.class, id);
  }

  /**
   * 根据模块选项的Id该选项下所有有效值
   */
  public List<ModuleOptionNamedValue> findByOptionId(int optionId) {
    return this.jdbcTemplate.queryForList("select id,optionId,namedValue,className,name from t_rule_application_module_option_named_value where optionId =? AND status=1",
      ModuleOptionNamedValue.class, optionId);
  }

  /**
   * 更新模块的配置项信息
   * 
   * @param id 配置项的ID
   * @param newName 新的名称
   */
  public boolean updateRuleItemById(int id, String newName) {
    return this.jdbcTemplate.update("update t_rule_application_module_item set name=? where id=?", newName, id) > 0;
  }

  /**
   * 根据模块ID和itemKey更新模块配置项
   * 
   * @param moduleId 模块ID
   * @param newName 新的名称
   * @param itemKey 配置项的key
   */
  public boolean updateRuleItemByKey(int moduleId, String itemKey, String newName) {
    return this.jdbcTemplate.update("update t_rule_application_module_item set name=? where moduleId=? AND itemKey=? ", newName, moduleId,
        itemKey) > 0;
  }
}
