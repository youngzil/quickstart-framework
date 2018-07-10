/**
 * 项目名称：quickstart-spring-framework 
 * 文件名：ApplicationTest.java
 * 版本信息：
 * 日期：2018年1月17日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.spring.framework.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ApplicationTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月17日 上午10:36:10
 * @since 1.0
 */
public class ApplicationTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        // HelloBean hello = (HelloBean) context.getBean("helloBean");
        // hello.setApplicationContext(context);
        EmailEvent event = new EmailEvent("hello", "boylmx@163.com", "this is a email text!");
        context.publishEvent(event);
        // System.out.println();
    }
}
