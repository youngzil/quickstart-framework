package org.quickstart.log4j2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-28 12:36
 */
public class Slf4jLog4j2Test {

  private static final Logger logger = LoggerFactory.getLogger(Slf4jLog4j2Test.class);

  public static void main(String[] args) {
    if (logger.isDebugEnabled()) {
      logger.debug("slf4j-log4j2 debug message");
    }
    if (logger.isInfoEnabled()) {
      logger.info("slf4j-log4j2 info message");
    }
    if (logger.isTraceEnabled()) {
      logger.debug("slf4j-log4j2 trace message");
    }
  }

}
