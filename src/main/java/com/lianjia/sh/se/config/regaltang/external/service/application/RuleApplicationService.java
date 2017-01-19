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
  
  /**
   * 新增app
   * @param appKey
   * @param appName
   * @param digest
   */
  public int  addApp(String appKey,String appName,String digest){
    return this.ruleApplicationDao.insert(appKey,appName,digest);
  }

  /**
   * 根据主键ID更新app信息
   * @param existedApplication
   */
  public boolean updateApp(int id,String name,String digest) {
    return this.ruleApplicationDao.updateAppById(id,name,digest);
  }
}
