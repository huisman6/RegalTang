package com.lianjia.sh.se.config.regaltang.external.service.module;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lianjia.sh.se.config.regaltang.model.ApplicationModule;

@Service
public class ApplicationModuleService {
   @Autowired
   private ApplicationModuleDao applicationModuleDao;
   
   /**
    * 查询应用所有可用的模块
   * @param appId
   */
  public List<ApplicationModule> findByAppId(int appId){
     return this.applicationModuleDao.findByAppId(appId);
  }
  
  public void batchAddMoudules(Collection<ApplicationModule> modules){
    this.applicationModuleDao.batchAddModules(modules);
  }
}
