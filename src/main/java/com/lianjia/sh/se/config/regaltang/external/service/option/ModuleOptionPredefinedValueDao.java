package com.lianjia.sh.se.config.regaltang.external.service.option;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lianjia.sh.se.config.regaltang.model.ModuleOptionPredefinedValue;

/**
 * 选项预定义的值，已不影响已经配置好的条件，不过还是建议保持选项向下兼容（只增不减）；
 * 每次更新时，会先删除选项已有的值，再insert进去；
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
@Repository
@Lazy
/* non-public */class ModuleOptionPredefinedValueDao {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  /**
   * 根据选项值的ID查找选项值的信息
   */
  public ModuleOptionPredefinedValue findById(int id) {
    return this.jdbcTemplate.queryForObject(
        "select id,optionId,predefinedValue,name from t_rule_application_module_option_predefined_value where id =?",
        ModuleOptionPredefinedValue.class, id);
  }

  /**
   * 根据模块选项的Id该选项下所有有效值
   */
  public List<ModuleOptionPredefinedValue> findByOptionId(int optionId) {
    return this.jdbcTemplate.queryForList("select id,optionId,predefinedValue,name from t_rule_application_module_option_predefined_value where optionId =?",
      ModuleOptionPredefinedValue.class, optionId);
  }

}
