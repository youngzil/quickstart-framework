/**
 * 项目名称：quickstart-web 
 * 文件名：Log4jTest.java
 * 版本信息：
 * 日期：2016年12月22日
 * Copyright yangzl Corporation 2016
 * 版权所有 *
 */
package com.quickstart.test;

import org.apache.log4j.Logger;

/**
 * Log4jTest
 * 
 * @author：youngzil@163.com
 * @2016年12月22日 下午6:07:07
 * @version 1.0
 */
public class Log4jTest {

    private static Logger logger = Logger.getLogger(Log4jTest.class);

    public static void main(String[] args) {
        logger.debug("fffff");
    }

}
