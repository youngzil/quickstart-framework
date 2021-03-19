package org.quickstart.jul;

import org.junit.Test;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.JDK14LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-28 12:31
 */
public class Slf4jJdkTest {

    private static final Logger logger = LoggerFactory.getLogger(Slf4jJdkTest.class);
    static LogManager logManager = LogManager.getLogManager();

    @Test
    public void testCode2() {

        ILoggerFactory test = LoggerFactory.getILoggerFactory();
        System.out.println(test);
        // org.slf4j.impl.JDK14LoggerFactory@5f282abb

        JDK14LoggerFactory jdk14LoggerFactory = (JDK14LoggerFactory)LoggerFactory.getILoggerFactory();
        String logLevel = "DEBUG";

    }

    public static void main(String[] args) throws IOException {

        InputStream in = JULTest.class.getResourceAsStream("/logging.properties");
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
