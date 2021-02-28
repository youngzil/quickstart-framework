package org.quickstart.log4j2;

// import org.apache.commons.logging.Log;
// import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLog {

    // org.apache.commons.logging
    // private static Log commonsLog = LogFactory.getLog(TestLog.class);

    // org.slf4j
    private static Logger slf4jLogger = LoggerFactory.getLogger(TestLog.class);


    @Test
    public void testCode2() {

        ILoggerFactory test = LoggerFactory.getILoggerFactory();
        System.out.println(test);
        // org.apache.logging.slf4j.Log4jLoggerFactory@13c3c1e1
    }

    @Test
    public void testLog() {

        // commonsLog.info("commonsLog");

        slf4jLogger.info("slf4jLogger");

    }

}
