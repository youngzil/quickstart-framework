package org.quickstart.jul;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-28 12:31
 */
public class Slf4jJdkTest {

  private static final Logger logger = LoggerFactory.getLogger(Slf4jJdkTest.class);
  static LogManager logManager = LogManager.getLogManager();

  public static void main(String[] args) throws IOException {

    InputStream in = LoggerPropretiesTest.class.getResourceAsStream("/logging.properties");
    logManager.readConfiguration(in);
    // logManager.addLogger(logger); // 添加Logger

    logger.info("sssss");

    if (logger.isDebugEnabled()) {
      logger.debug("slf4j-jdk debug message");
    }
    if (logger.isInfoEnabled()) {
      logger.debug("slf4j-jdk info message");
    }
    if (logger.isTraceEnabled()) {
      logger.debug("slf4j-jdk trace message");
    }
  }

}
