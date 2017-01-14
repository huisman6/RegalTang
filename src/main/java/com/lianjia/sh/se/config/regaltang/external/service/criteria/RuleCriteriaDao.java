package com.lianjia.sh.se.config.regaltang.external.service.criteria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Lazy
/*non-public*/class RuleCriteriaDao {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  /**
   * @summary
   * @author Huisman
   * @version v1
   * @since 2017年1月9日 上午11:07:07
   */
  public void syncCriteria(String identity,int multiValue, String name,int order) {
    int count = jdbcTemplate.queryForObject("select count(*) from t_rule_criteria where criteria=?", Integer.class, identity);
    if (count > 0) {
      this.jdbcTemplate.update("update t_rule_criteria set name=?,multiValue=?,sort=? where criteria=?", name,multiValue,order, identity);
    } else {
      this.jdbcTemplate.update("insert into t_rule_criteria(criteria,multiValue, name,sort)values(?,?,?,?)",
        identity,multiValue, name,order);
    }
  }
}
