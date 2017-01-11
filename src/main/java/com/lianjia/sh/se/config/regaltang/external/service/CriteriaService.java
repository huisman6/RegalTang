package com.lianjia.sh.se.config.regaltang.external.service;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lianjia.sh.se.config.regaltang.rule.criteria.CriteriaRegistrar;
import com.lianjia.sh.se.config.regaltang.rule.criteria.MultiValueCriteria;


@Service
public class CriteriaService implements SmartInitializingSingleton {
  @Autowired
  private CriteriaDao criteriaDao;

  /* 
   * @see org.springframework.beans.factory.SmartInitializingSingleton#afterSingletonsInstantiated()
   */
  @Override
  public void afterSingletonsInstantiated() {
    CriteriaRegistrar.manualRegister();
    CriteriaRegistrar.activeCriterias().forEach((criteria) -> criteriaDao.syncCriteria(criteria.key(),criteria instanceof MultiValueCriteria?1:0, criteria.name(),criteria.order()));
  }
  
  
}
