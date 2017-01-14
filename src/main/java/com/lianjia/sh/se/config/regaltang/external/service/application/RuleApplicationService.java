package com.lianjia.sh.se.config.regaltang.external.service.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lianjia.sh.se.config.regaltang.model.Application;

@Service
public class RuleApplicationService {
  @Autowired
  RuleApplicationDao ruleApplicationDao;
  
  /**
   * 根据appKey查询应用信息
   */
  public Application findByAppKey(String appKey){
    return this.ruleApplicationDao.findByKey(appKey);
  }
}
