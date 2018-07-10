/**
 * 项目名称：quickstart-spring-framework 
 * 文件名：BeanLifeCycleTest.java
 * 版本信息：
 * 日期：2018年4月17日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.spring.framework.factory.bean.lifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * BeanLifeCycleTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年4月17日 下午3:54:11
 * @since 1.0
 */
public class BeanLifeCycleTest {

    public static void main(String[] args) {
        
        System.out.println("现在开始初始化容器");

        ApplicationContext factory = new ClassPathXmlApplicationContext("beans-lifecycle.xml");
        System.out.println("容器初始化成功");
        // 得到Preson，并使用
        Person person = factory.getBean("person", Person.class);
        System.out.println(person);

        System.out.println("现在开始关闭容器！");
        ((ClassPathXmlApplicationContext) factory).registerShutdownHook();
    }

}
