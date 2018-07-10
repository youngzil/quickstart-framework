/**
 * 项目名称：quickstart-spring-framework 
 * 文件名：Mainfactory.java
 * 版本信息：
 * 日期：2018年1月17日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.spring.framework.factory.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Mainfactory
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月17日 上午9:52:52
 * @since 1.0
 */
public class Mainfactory {
    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("bean-factory.xml");

        // 静态工厂方法测试:
        Car car1 = (Car) ctx.getBean("car1");
        // CarFactoryBean car11 = (CarFactoryBean) ctx.getBean("&car1");
        System.out.println(car1);

        // 实例工厂方法测试:
        Car car2 = (Car) ctx.getBean("car2");
        System.out.println(car2);

        Car car3 = (Car) ctx.getBean("car3");
        CarFactoryBean car4 = (CarFactoryBean) ctx.getBean("&car3");
        System.out.println(car3);
        System.out.println(car4);

    }
}
