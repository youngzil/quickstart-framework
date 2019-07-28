package org.quickstart.jul;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-28 11:30
 */
public class LoggerPropretiesTest {

  static Logger logger = Logger.getLogger("LoggerPropreties");
  static LogManager logManager = LogManager.getLogManager();

  public static void main(String[] args) throws IOException {
    try {// 读取配制文件
      // 注意配置，是有斜杠的；
      InputStream in = LoggerPropretiesTest.class.getResourceAsStream("/logging.properties");
      logManager.readConfiguration(in);
      logManager.addLogger(logger); // 添加Logger
      logger.severe("这是[severe]信息");
      logger.warning("这是[warning]信息");
      logger.info("这是[info]信息");
      logger.config("这是[config]信息");
      logger.fine("这是[fine]信息");
      logger.finer("这是[finer]信息");
      logger.finest("这是[finest]信息");
    } catch (SecurityException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ArithmeticException e) {
      e.printStackTrace();
    }
  }

}
