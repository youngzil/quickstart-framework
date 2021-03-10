package org.quickstart.log4j;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-28 16:35
 */
public class Log4jTest {

    Logger logger = Logger.getLogger(Log4jTest.class);

    @Test
    public void log() {
        logger.info("log4j1 log");
    }

    public static void main(String[] args) {
        Log4jTest log4j = new Log4jTest();
        log4j.log();
    }

}
