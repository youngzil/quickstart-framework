/**
 * 项目名称：quickstart-spring-framework 
 * 文件名：MyFactoryBeanTest.java
 * 版本信息：
 * 日期：2018年4月16日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.spring.framework.beans.factory;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * MyFactoryBeanTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年4月16日 下午5:59:11
 * @since 1.0
 */
public class MyFactoryBeanTest {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        
        ApplicationContext appCtx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Date now = (Date) appCtx.getBean("myFactoryBean"); // 获取的是factory的Object
        System.out.println(now);
        MyFactoryBean factoryBean = (MyFactoryBean) appCtx.getBean("&myFactoryBean"); // 获取的是factory
        factoryBean.sayName();

    }

}
