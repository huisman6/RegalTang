package com.lianjia.sh.se.config.regaltang.external;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * 业务配置的服务端
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
@SpringBootApplication
public class Application {
  public static void main(String[] args) {
    SpringApplicationBuilder sab=new SpringApplicationBuilder(Application.class);
    //我们需要hsql的Swing界面
    sab.headless(false);
    sab.build().run(args);
  }
}
