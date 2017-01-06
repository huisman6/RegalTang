package com.lianjia.sh.se.config.regaltang.option;

/**
 * 常用的一些条件选项
 * @author Huisman (SE)
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 */
public interface CommonConditionOptions {
     /**
     * 城市
     */
    ConditionOption CITY=new ConditionOption("城市", "city");
    
    /**
     * 委托类型 
     */
    ConditionOption ENTRUST_TYPE=new ConditionOption("委托类型", "entrustType");
}
