package org.quickstart.log4j;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerFactory;

public class TestLog {

    // org.apache.commons.logging
    private static Log commonsLog = LogFactory.getLog(TestLog.class);

    // org.slf4j
    private static Logger slf4jLogger = LoggerFactory.getLogger(TestLog.class);


    @Test
    public void testCode2() {
        ILoggerFactory test = LoggerFactory.getILoggerFactory();
        System.out.println(test);
        // org.slf4j.impl.Log4jLoggerFactory@763d9750


        Log4jLoggerFactory log4jLoggerFactory = (Log4jLoggerFactory)LoggerFactory.getILoggerFactory();
        String logLevel = "DEBUG";
    }

    @Test
    public void testLog() {

        commonsLog.info("commonsLog");

        slf4jLogger.info("slf4jLogger");

    }

}
