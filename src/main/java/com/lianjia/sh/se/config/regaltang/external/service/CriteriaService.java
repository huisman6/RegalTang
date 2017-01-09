package com.lianjia.sh.se.config.regaltang.external.service;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lianjia.sh.se.config.regaltang.criteria.CriteriaRegistrar;

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
    CriteriaRegistrar.activeCriterias().forEach((criteria) -> criteriaDao.syncCriteria(criteria.key(), criteria.name(),criteria.order()));
  }
  
  
}
