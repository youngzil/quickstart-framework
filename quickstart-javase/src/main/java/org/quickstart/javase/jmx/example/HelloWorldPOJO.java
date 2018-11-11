/**
 * 项目名称：quickstart-javase 
 * 文件名：HelloWorldPOJO.java
 * 版本信息：
 * 日期：2018年6月11日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jmx.example;

import org.softee.management.annotation.Description;
import org.softee.management.annotation.MBean;
import org.softee.management.annotation.ManagedAttribute;
import org.softee.management.annotation.ManagedOperation;
import org.softee.management.annotation.Parameter;
import org.softee.management.helper.MBeanRegistration;

/**
 * HelloWorldPOJO
 * 
 * https://blog.csdn.net/hanxingwang0806/article/details/25161243
 * 
 * @author：youngzil@163.com
 * @2018年6月11日 下午9:01:17
 * @since 1.0
 */
@MBean(objectName = "pojo-agent-test:name=HelloWorld")
@Description("HelloWorld MBean by pojo-mbean")
public class HelloWorldPOJO {
    private String name;

    private int age;

    @ManagedOperation
    @Description("print the name and age")
    public void print() {
        System.out.println("name=" + name + ",age=" + age);
    }

    @ManagedOperation
    @Description("increment the age and then return the new age")
    public int incrementAge(@Parameter("age") int age) {
        this.age = this.age + age;
        return this.age;
    }

    @ManagedAttribute
    @Description("about name")
    public String getName() {
        return name;
    }

    @ManagedAttribute
    public void setName(String name) {
        this.name = name;
    }

    @ManagedAttribute
    @Description("about age")
    public int getAge() {
        return age;
    }

    @ManagedAttribute
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 把MBean注册到MBeanServer中
     * 
     * @throws Exception
     */
    public void initMBeanServer() throws Exception {
        new MBeanRegistration(this).register();
    }

    public static void main(String[] args) throws Exception {
        HelloWorldPOJO a = new HelloWorldPOJO();
        a.initMBeanServer();
        System.in.read();
    }
}
