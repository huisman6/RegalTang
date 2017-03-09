package com.lianjia.sh.se.config.regaltang.external.service.output;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lianjia.sh.se.config.regaltang.model.ModuleOutput;
import com.lianjia.sh.se.config.regaltang.model.ModuleOutputJavaBean;
import com.lianjia.sh.se.config.regaltang.model.ModuleOutputPredefinedValue;

/**
 * 模块的输出
 */
@Service
public class ModuleOutputService {
  @Autowired
  private ModuleOutputJavaBeanDao moduleOutputJavaBeanDao;
  @Autowired
  private ModuleOutputPredefinedValueDao moduleOutputPredefinedValueDao;
  @Autowired
  private ModuleOutputDao outputDao;

  /**
   * 批量保存输出的元数据信息
   * 
   * @param outputs 应用的所有模块的输出值
   * @param outputPredefinedValues 预定义的值
   * @param outputJavaBeans 预定义的javaBeans
   */
  public void batchAddOutputs(List<ModuleOutput> outputs, List<ModuleOutputPredefinedValue> outputPredefinedValues,
      List<ModuleOutputJavaBean> outputJavaBeans) {
    this.outputDao.batchInsert(outputs);
    if (outputJavaBeans !=null && !outputJavaBeans.isEmpty()) {
      this.moduleOutputJavaBeanDao.batchInsert(outputJavaBeans);
    }
    if (outputPredefinedValues !=null && !outputPredefinedValues.isEmpty()) {
      this.moduleOutputPredefinedValueDao.batchInsert(outputPredefinedValues);
    }
  }


}
