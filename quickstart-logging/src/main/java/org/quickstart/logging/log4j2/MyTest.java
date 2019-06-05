/**
 * 项目名称：quickstart-logging 
 * 文件名：MyTest.java
 * 版本信息：
 * 日期：2017年7月15日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.logging.log4j2;

import java.beans.PropertyEditor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MyTest
 * 
 * Web项目中使用log4j的变化，因为log4j2可以自动监听log配置文件改动，所以一般使用的spring log4j
 * listener就不再需要了，在servlet3.0中不需要在web.xml加入任何log4j2的配置，如果是servlet2.5就需要加入Log4jServletContextListener和Log4jServletFilter等配置，具体可见log4j2在Web中配置 http://logging.apache.org/log4j/2.x/manual/webapp.html
 * 
 * @author：youngzil@163.com
 * @2017年7月15日 下午10:45:46
 * @version 2.0
 */
public class MyTest {

    private final static Logger logger = LoggerFactory.getLogger(MyTest.class);

    public static void main(String[] args) {

        long beginTime = System.currentTimeMillis();
        try {
            System.out.println("log4j2 test");
        } catch (Exception e) {
            logger.error("发生了异常：", e);
        }

        long endTime = System.currentTimeMillis();

        // Tips：根据官方测试的数据，第一种用法比第二种快47倍！
        logger.info("请求处理结束，耗时：{}毫秒", (endTime - beginTime)); // 第一种用法
        logger.info("请求处理结束，耗时：" + (endTime - beginTime) + "毫秒"); // 第二种用法

        // 注意：在JVM启动参数中增加 -DLog4jContextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector 开启异步日志
    }

}
