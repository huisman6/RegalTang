package com.lianjia.sh.se.config.regaltang.external.service.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lianjia.sh.se.config.regaltang.model.ModuleRuleItem;

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
    return this.jdbcTemplate.queryForList("select id,moduleId,name,itemKey from t_rule_application_module_item where moduleId =? AND status=1",
        ModuleRuleItem.class, moduleId);
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
    return this.jdbcTemplate.update("update t_rule_application_module_item set name=? where moduleId=? AND itemKey=? AND status=1", newName, moduleId,
        itemKey) > 0;
  }
}
