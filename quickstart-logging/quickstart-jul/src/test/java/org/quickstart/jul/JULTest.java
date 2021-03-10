package org.quickstart.jul;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.junit.Test;

public class JULTest {


  static Logger logger = Logger.getLogger("LoggerPropreties");
  static LogManager logManager = LogManager.getLogManager();

  @Test
  public void propertiesLog() throws IOException {
    // 读取配制文件
    // 注意配置，是有斜杠的；
    InputStream in = JULTest.class.getResourceAsStream("/logging.properties");
    logManager.readConfiguration(in);
    logManager.addLogger(logger); // 添加Logger
    logger.severe("这是[severe]信息");
    logger.warning("这是[warning]信息");
    logger.info("这是[info]信息");
    logger.config("这是[config]信息");
    logger.fine("这是[fine]信息");
    logger.finer("这是[finer]信息");
    logger.finest("这是[finest]信息");
  }

  @Test
  public void log() {
    Logger logger = Logger.getLogger(getClass().getName());

    Logger jdkLogger = Logger.getLogger("JDKLog");

    logger.info("jul log");
    jdkLogger.info("ddddd");
  }

  @Test
  public void testLogger() {

    Logger logger1 = Logger.getLogger("org.quickstart.jul");
    Logger logger2 = Logger.getLogger("org.quickstart.jul");
    System.out.println(logger1 == logger2);

  }

  @Test
  public void testLog2() {

    Logger logger = Logger.getLogger("org.quickstart.jul");
    logger.setLevel(Level.ALL);

    ConsoleHandler consoleHandler = new ConsoleHandler();
    consoleHandler.setLevel(Level.FINEST);
    logger.addHandler(consoleHandler);

    logger.severe("严重");
    logger.warning("警告");
    logger.info("信息");
    logger.config("配置");
    logger.fine("良好");
    logger.finer("较好");
    logger.finest("最好");

  }

  @Test
  public void testLogLevel() {
    // logger的名字是有层级关系的，或者可以说，子Logger继承父Logger的Level。

    Logger log = Logger.getLogger("org");
    log.setLevel(Level.WARNING);
    Logger log2 = Logger.getLogger("org.quickstart.jul");
    log2.info("111");
    log2.severe("222");
    log2.warning("333");

  }

  @Test
  public void testLogHandler() throws IOException {
    Logger log = Logger.getLogger("org");
    log.setLevel(Level.INFO);
    Logger log1 = Logger.getLogger("org.quickstart.jul");
    ConsoleHandler consoleHandler = new ConsoleHandler();
    consoleHandler.setLevel(Level.ALL);
    log.addHandler(consoleHandler);
    FileHandler fileHandler = new FileHandler("testlog.log");
    fileHandler.setLevel(Level.INFO);
    // fileHandler.setFormatter(new SimpleFormatter());
    log.addHandler(fileHandler);
    log.info("111");
    log1.info("222");
    log1.fine("333");
  }

  @Test
  public void testLogFormat() throws IOException {
    Logger logger = Logger.getLogger("org.quickstart.jul");
    logger.setLevel(Level.ALL);
    ConsoleHandler consoleHandler = new ConsoleHandler();
    logger.addHandler(consoleHandler);
    FileHandler fileHandler = new FileHandler("testLogFormat.log");
    fileHandler.setFormatter(new LoggerFormatter());
    logger.addHandler(fileHandler);
    logger.info("hi");

  }

}
