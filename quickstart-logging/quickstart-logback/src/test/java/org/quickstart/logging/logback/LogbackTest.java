package org.quickstart.logging.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import ch.qos.logback.core.util.FileSize;
import org.junit.Test;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-27 23:25
 */
public class LogbackTest {

    private static final Logger logger = LoggerFactory.getLogger(LogbackTest.class);

    // 测试多文件日志打印
    private static final Logger testLog = LoggerFactory.getLogger("testLog");

    public static final ch.qos.logback.classic.Logger HLOG = new LoggerContext().getLogger("TEST");
    public static final String CLIENT_LOG_PATH = "/Users/lengfeng/logs/quickstart/quickstart-logback/";

    @Test
    public void testCode2() {

        ILoggerFactory test = LoggerFactory.getILoggerFactory();
        Logger logger = test.getLogger("TEST");

        logger.error("sssss");

        System.out.println(test);
        // ch.qos.logback.classic.LoggerContext[default]


        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

        String logLevel = "DEBUG";
        loggerContext.getLogger("org.mybatis").setLevel(Level.valueOf(logLevel));

        loggerContext.getLogger("org.springframework").setLevel(Level.valueOf(logLevel));


    }

    @Test
    public void testCode3() {

        final String logRoot = getOrCreateFolderInAbsolutePath(CLIENT_LOG_PATH);
        final String logPath = getOrCreateFileInAbsolutePath(logRoot, "quickstart-logback.log");
        System.out.println("quickstart logback client log path : " + logPath);

        LoggerContext loggerContext = (LoggerContext)LoggerFactory.getILoggerFactory();
        ILoggerFactory test = LoggerFactory.getILoggerFactory();

        RollingFileAppender appender = new RollingFileAppender();
        appender.setContext(loggerContext);
        appender.setAppend(true);
        appender.setFile(logPath);

        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(loggerContext);
        encoder.setPattern("%date{ISO8601} %-5level [%thread] %logger{32} - [%X{uid}] [%X{coeus.uid}] %message%n");
        encoder.start();

        //TimeBasedRollingPolicy rollingPolicy = new TimeBasedRollingPolicy();
        SizeAndTimeBasedRollingPolicy rollingPolicy = new SizeAndTimeBasedRollingPolicy();
        rollingPolicy.setMaxFileSize(FileSize.valueOf("100mb"));
        rollingPolicy.setContext(loggerContext);
        rollingPolicy.setParent(appender);
        rollingPolicy.setFileNamePattern(logRoot + File.separator + "quickstart-logback-%d{yyyy-MM-dd}-%i.log");
        rollingPolicy.setMaxHistory(5);
        rollingPolicy.setTotalSizeCap(FileSize.valueOf("500mb"));
        rollingPolicy.start();

        appender.setEncoder(encoder);
        appender.setRollingPolicy(rollingPolicy);
        appender.start();

        HLOG.setLevel(Level.TRACE);
        HLOG.setAdditive(false);
        HLOG.addAppender(appender);

        HLOG.trace("quickstart-logback trace log info");
        HLOG.debug("quickstart-logback DEBUG log info");
        HLOG.info("quickstart-logback INFO log info");
        HLOG.warn("quickstart-logback warn log info");
        HLOG.error("quickstart-logback error log info");

    }

    @Test
    public void testCode() {

        final String logRoot = getOrCreateFolderInAbsolutePath(CLIENT_LOG_PATH);
        final String logPath = getOrCreateFileInAbsolutePath(logRoot, "quickstart-logback.log");
        System.out.println("quickstart logback client log path : " + logPath);

        LoggerContext loggerContext = (LoggerContext)LoggerFactory.getILoggerFactory();

        RollingFileAppender appender = new RollingFileAppender();
        appender.setContext(loggerContext);
        appender.setAppend(true);
        appender.setFile(logPath);

        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(loggerContext);
        encoder.setPattern("%date{ISO8601} %-5level [%thread] %logger{32} - [%X{uid}] [%X{coeus.uid}] %message%n");
        encoder.start();

        //TimeBasedRollingPolicy rollingPolicy = new TimeBasedRollingPolicy();
        SizeAndTimeBasedRollingPolicy rollingPolicy = new SizeAndTimeBasedRollingPolicy();
        rollingPolicy.setMaxFileSize(FileSize.valueOf("100mb"));
        rollingPolicy.setContext(loggerContext);
        rollingPolicy.setParent(appender);
        rollingPolicy.setFileNamePattern(logRoot + File.separator + "quickstart-logback-%d{yyyy-MM-dd}-%i.log");
        rollingPolicy.setMaxHistory(5);
        rollingPolicy.setTotalSizeCap(FileSize.valueOf("500mb"));
        rollingPolicy.start();

        appender.setEncoder(encoder);
        appender.setRollingPolicy(rollingPolicy);
        appender.start();

        HLOG.setLevel(Level.TRACE);
        HLOG.setAdditive(false);
        HLOG.addAppender(appender);

        HLOG.trace("quickstart-logback trace log info");
        HLOG.debug("quickstart-logback DEBUG log info");
        HLOG.info("quickstart-logback INFO log info");
        HLOG.warn("quickstart-logback warn log info");
        HLOG.error("quickstart-logback error log info");

    }

    @Test
    public void testroot() throws InterruptedException, IOException {
        logger.info("logback 成功了");
        logger.error("logback 成功了");
        logger.debug("logback 成功了");

        TimeUnit.SECONDS.sleep(60);

        logger.info("logback 成功了2");
        logger.error("logback 成功了2");
        logger.debug("logback 成功了2");

        System.in.read();

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

    public static String getOrCreateFolderInAbsolutePath(String folderName) {
        File folder = new File(folderName);
        if (!folder.exists()) {
            folder.mkdir();
        }
        return folder.getAbsolutePath();
    }

    public static String getOrCreateFolder(String folderName) {
        File folder = new File(System.getProperty("user.home"), folderName);
        if (!folder.exists()) {
            folder.mkdir();
        }
        return folder.getAbsolutePath();
    }

    public static String getOrCreateFileInAbsolutePath(String folderName, String fileName) {
        File folder = new File(folderName);
        if (!folder.exists()) {
            folder.mkdir();
        }
        File file = new File(folder, fileName);
        return file.getAbsolutePath();
    }

    public static String getOrCreateFile(String folderName, String fileName) {
        File folder = new File(System.getProperty("user.home"), folderName);
        if (!folder.exists()) {
            folder.mkdir();
        }
        File file = new File(folder, fileName);
        return file.getAbsolutePath();
    }

}
