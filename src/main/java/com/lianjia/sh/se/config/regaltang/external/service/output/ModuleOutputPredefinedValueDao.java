package com.lianjia.sh.se.config.regaltang.external.service.output;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lianjia.sh.se.config.regaltang.model.ModuleOutputPredefinedValue;

/**
 * 模块输出信息为预定义的一系列值；
 * 
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
@Repository
@Lazy
/* non-public */class ModuleOutputPredefinedValueDao {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  /**
   * 根据预定义值的ID查找预定义值的信息
   */
  public ModuleOutputPredefinedValue findById(int id) {
    return this.jdbcTemplate.queryForObject(
        "select id,moduleId,predefinedValue,name from t_rule_application_module_output_predefined_value where id =?",
        ModuleOutputPredefinedValue.class, id);
  }

  /**
   * 根据模块Id查询所有预定义的有效值
   */
  public List<ModuleOutputPredefinedValue> findByModuleId(int moduleId) {
    return this.jdbcTemplate.queryForList(
      "select id,moduleId,predefinedValue,name from t_rule_application_module_output_predefined_value where moduleId =?",
      ModuleOutputPredefinedValue.class, moduleId);
  }
}
