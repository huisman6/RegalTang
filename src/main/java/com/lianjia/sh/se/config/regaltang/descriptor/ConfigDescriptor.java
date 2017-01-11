package com.lianjia.sh.se.config.regaltang.descriptor;

/**
 * 配置的一些描述信息
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
interface ConfigDescriptor {
  /**
   * 界面显示的名称
   */
  String name();
  /**
   * 唯一标识符，用于区分不同模块，应用，可配置项等
   */
  String identity();
}
