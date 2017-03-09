package com.lianjia.sh.se.config.regaltang.external.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lianjia.sh.se.config.regaltang.dto.ApplicationDescriptor;
import com.lianjia.sh.se.config.regaltang.external.service.application.AppDescriptorValidator;
import com.lianjia.sh.se.config.regaltang.external.service.application.ApplicationDescriptorService;

@RestController
public class RuleAppController {
  @Autowired
  ObjectMapper objectMapper;
  @Autowired
  private ApplicationDescriptorService applicationDescriptorService;

  @RequestMapping(value = "/v1/rule/app", method = RequestMethod.POST)
  public void addOrUpdateAppV1(@RequestBody String appConfigJSon) {
    if (appConfigJSon == null || appConfigJSon.trim().isEmpty()) {
      throw new IllegalArgumentException("app配置有误");
    }
    try {
      ApplicationDescriptor appDes = objectMapper.readValue(appConfigJSon, ApplicationDescriptor.class);
      if (appDes == null) {
        throw new IllegalArgumentException("无效的应用配置");
      }
      this.applicationDescriptorService.configApp(appDes, AppDescriptorValidator.md5Digest(appConfigJSon));
    } catch (IOException e) {
      e.printStackTrace();
      throw new IllegalArgumentException(e);
    }
  }
}
