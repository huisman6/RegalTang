package com.lianjia.sh.se.config.regaltang.external.service.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lianjia.sh.se.config.regaltang.dto.ApplicationDescriptor;
import com.lianjia.sh.se.config.regaltang.model.Application;

@Service
public class ApplicationDescriptorService {
   @Autowired
   private RuleApplicationService ruleApplicationService;
    /**
     * 将appDescriptor保存到数据库（修改或者新增）
     * @param appDescriptor 应用程序的描述信息
     */
    public boolean configApp(ApplicationDescriptor appDescriptor){
       Application app=  appDescriptor.getApp(); 
       //已经存在了的应用信息
       Application existedApp= this.ruleApplicationService.findByAppKey(app.getAppKey());
       
      return true;
    }
}
