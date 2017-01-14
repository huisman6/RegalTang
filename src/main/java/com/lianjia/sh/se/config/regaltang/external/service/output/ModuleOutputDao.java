package com.lianjia.sh.se.config.regaltang.external.service.output;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lianjia.sh.se.config.regaltang.model.ModuleOutput;

/**
 * 模块输出信息和模块是一对一关系
 * 
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
@Repository
@Lazy
/* non-public */class ModuleOutputDao {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  /**
   * 根据模块ID查询模块的输出信息
   */
  public ModuleOutput findByModuleId(int moduleId) {
    return this.jdbcTemplate.queryForObject(
        "select moduleId,name,outputType,className from t_rule_application_module_output where id =? AND status=1", ModuleOutput.class,
        moduleId);
  }


  /**
   * 更新模块的输出信息
   * @param moduleId 模块ID
   * @param newName 新的名称
   * @param outputType 输出结果类型，预定义或者javabean
   * @param className 输出结果的类型
   */
  public boolean updateOutputByModuleId(int moduleId, String newName,int outputType,String className) {
    return this.jdbcTemplate.update("update t_rule_application_module_output set name=?,outputType=?,className=? where moduleId=?", newName,outputType,className,moduleId) > 0;
  }
}
