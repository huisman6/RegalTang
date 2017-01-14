package com.lianjia.sh.se.config.regaltang.external.service.output;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lianjia.sh.se.config.regaltang.model.ModuleOutputJavaBean;

/**
 * 模块输出信息为JavaBean(多个字段）；
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
@Repository
@Lazy
/* non-public */class ModuleOutputJavaBeanDao {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  /**
   * 根据JavaBean值的ID查找
   */
  public ModuleOutputJavaBean findById(int id) {
    return this.jdbcTemplate.queryForObject(
        "select id,moduleId,fieldName,fieldClassName from t_rule_application_module_output_javabean where id =?",
        ModuleOutputJavaBean.class, id);
  }

  /**
   * 根据模块Id查询JavaBean的所有字段及其类型
   */
  public List<ModuleOutputJavaBean> findByModuleId(int moduleId) {
    return this.jdbcTemplate.queryForList(
      "select id,moduleId,fieldName,fieldClassName from t_rule_application_module_output_javabean where moduleId =?",
      ModuleOutputJavaBean.class, moduleId);
  }
}
