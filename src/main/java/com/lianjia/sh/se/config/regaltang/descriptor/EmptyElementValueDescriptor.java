package com.lianjia.sh.se.config.regaltang.descriptor;

/**
 * 默认可枚举的值为空
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public class EmptyElementValueDescriptor implements EnumerableValueDescriptor {

  @Override
  public NamedValue[] elements() {
    return new SimpleNamedValue[0];
  }

}
