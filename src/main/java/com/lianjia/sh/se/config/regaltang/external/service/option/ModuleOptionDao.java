package com.lianjia.sh.se.config.regaltang.external.service.option;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lianjia.sh.se.config.regaltang.model.ModuleOption;
/**
 * 模块选项信息
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
@Repository
@Lazy
/* non-public */class ModuleOptionDao {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  /**
   * 根据模块选项ID查询正常的条件选项
   */
  public ModuleOption findById(int id) {
    return this.jdbcTemplate.queryForObject("select id,moduleId,optionKey,optionType,className,name,sort from t_rule_application_module_option where id =? AND status=1", ModuleOption.class, id);
  }

  /**
   * 根据模块选项的key查询正常的条件选项
   */
  public ModuleOption findByOptionKey(int moduleId,String optionKey) {
    return this.jdbcTemplate.queryForObject("select id,moduleId,optionKey,optionType,className,name,sort from t_rule_application_module_option where moduleId =? AND optionKey=? AND status=1", ModuleOption.class, moduleId,optionKey);
  }
  
  /**
   * 根据模块选项的key查询正常的条件选项
   */
  public List<ModuleOption> findByModuleId(int moduleId,String optionKey) {
    return this.jdbcTemplate.queryForList("select id,moduleId,optionKey,optionType,className,name,sort from t_rule_application_module_option where moduleId =? AND status=1", ModuleOption.class, moduleId,optionKey);
  }
  
  /**
   * 根据选项ID更新模块的条件选项信息
   * @param id 选项的ID
   * @param newName 新的名称
   * @param newSort 新的排序
   * @param newClassName 新的选项类型
   * @param optionType 选项类型（是预定义呢，还是手输入的）
   */
  public boolean updateOptionById(int id,String newName,String newClassName,int optionType,int newSort){
   return this.jdbcTemplate.update("update t_rule_application_module_option set name=?,className=?,optionType=?,sort=? where id=? AND status=1",newName,newClassName,optionType, newSort, id) >0;
  }
  
  /**
   * 根据选项的key更新模块的条件选项信息
   * @param moduleId 选项所属的模块的ID
   * @param optionKey 选项的key
   * @param newName 新的名称
   * @param newSort 新的排序
   * @param newClassName 新的选项类型
   * @param optionType 选项类型（是预定义呢，还是手输入的）
   */
  public boolean updateOptionById(int moduleId,String optionKey,String newName,String newClassName,int optionType,int newSort){
    return this.jdbcTemplate.update("update t_rule_application_module_option set name=?,className=?,optionType=?,sort=? where moduleId=? AND optionKey=? AND status=1",newName,newClassName,optionType, newSort, moduleId,optionKey) >0;
  }
}
