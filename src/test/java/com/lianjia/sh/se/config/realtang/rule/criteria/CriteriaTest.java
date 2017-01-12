package com.lianjia.sh.se.config.realtang.rule.criteria;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import com.lianjia.sh.se.config.regaltang.rule.criteria.EqualCriteria;
import com.lianjia.sh.se.config.regaltang.rule.criteria.GreaterThanCriteria;
import com.lianjia.sh.se.config.regaltang.rule.criteria.GreaterThanOrEqualCriteria;
import com.lianjia.sh.se.config.regaltang.rule.criteria.InAnyCriteria;
import com.lianjia.sh.se.config.regaltang.rule.criteria.LessThanCriteria;
import com.lianjia.sh.se.config.regaltang.rule.criteria.LessThanOrEqualCriteria;


public class CriteriaTest {
  private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

  enum TestEnum {
    信息方, 巴拉方, 独家出租方
  }

  @Test
  public void testInAny() {
    InAnyCriteria inAny = new InAnyCriteria();
    logger.info(inAny.toString());
    Assert.assertTrue(inAny.evaluate(23, new HashSet<>(Arrays.asList("1", "2", "23", "12"))));
    Assert.assertTrue(inAny.evaluate(TestEnum.信息方, new HashSet<>(Arrays.asList(TestEnum.values()))));
  }

  @Test
  public void testEqual() {
    EqualCriteria criteria = new EqualCriteria();
    logger.info(criteria.toString());
    logger.info("" + criteria.evaluate(12, "12"));
    logger.info("" + criteria.evaluate(TestEnum.信息方, ""));
    logger.info("" + criteria.evaluate(TestEnum.信息方, "独家出租方"));
    logger.info("" + criteria.evaluate(TestEnum.信息方, "信息方"));
  }

  @Test
  public void testGt() {
    GreaterThanCriteria gt = new GreaterThanCriteria();
    logger.info(gt.toString());
    logger.info("" + gt.evaluate(12, "23.0"));
    logger.info("" + gt.evaluate(25.0, "23"));
  }

  @Test
  public void testGe() {
    GreaterThanOrEqualCriteria ge = new GreaterThanOrEqualCriteria();
    logger.info(ge.toString());
    logger.info("" + ge.evaluate(12.0, "12.000"));
    logger.info("" + ge.evaluate(25.0, "23"));
  }

  @Test
  public void testLt() {
    LessThanCriteria lt = new LessThanCriteria();
    logger.info(lt.toString());
    logger.info("" + lt.evaluate(12.1, "23.0"));
    logger.info("" + lt.evaluate(25.0, "23"));
  }

  @Test
  public void testLe() {
    LessThanOrEqualCriteria le = new LessThanOrEqualCriteria();
    logger.info(le.toString());
    logger.info("" + le.evaluate(12.1, "12.1"));
    logger.info("" + le.evaluate(25.0, "23"));
  }
}
