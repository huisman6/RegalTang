package com.lianjia.sh.se.config.regaltang.external.service.item;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lianjia.sh.se.config.regaltang.model.ModuleRuleItem;

@Service
public class ModuleRuleItemService {
  @Autowired
  private ModuleRuleItemDao moduleRuleItemDao;
  
  public void batchAddItems(Collection<ModuleRuleItem> items){
     this.moduleRuleItemDao.batchAddItems(items);
  }
}
