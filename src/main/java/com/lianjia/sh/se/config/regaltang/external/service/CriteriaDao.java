package com.lianjia.sh.se.config.regaltang.external.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Lazy
class CriteriaDao {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  /**
   * @summary
   * @author Huisman
   * @version v1
   * @since 2017年1月9日 上午11:07:07
   */
  public void syncCriteria(String identity, String name,int order) {
    int count = jdbcTemplate.queryForObject("select count(*) from t_conditional_config_criteria where criteria=?", Integer.class, identity);
    if (count > 0) {
      this.jdbcTemplate.update("update t_conditional_config_criteria set name=?,sort=? where criteria=?", name,order, identity);
    } else {
      this.jdbcTemplate.update("insert into t_conditional_config_criteria(criteria, name,sort)values(?,?,?)",
        identity, name,order);
    }
  }
}
