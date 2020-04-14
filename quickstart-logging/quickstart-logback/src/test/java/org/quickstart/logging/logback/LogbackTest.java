package org.quickstart.logging.logback;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-27 23:25
 */
public class LogbackTest {

  private static final Logger logger = LoggerFactory.getLogger(LogbackTest.class);

  // 测试多文件日志打印
  private static final Logger testLog = LoggerFactory.getLogger("testLog");

  @Test
  public void testroot() {
    logger.info("logback 成功了");
    logger.error("logback 成功了");
    logger.debug("logback 成功了");
  }

  @Test
  public void testLevelMulti() {
    if (logger.isInfoEnabled()) {
      logger.debug("debug");
      logger.info("info");
    }
  }

  @Test
  public void testFile() {
    testLog.info("testFile文件测试");
    System.out.println("over");
  }

}
