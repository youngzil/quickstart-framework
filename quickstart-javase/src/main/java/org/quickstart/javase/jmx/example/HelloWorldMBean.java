/**
 * 项目名称：quickstart-javase 
 * 文件名：HelloWorldMBean.java
 * 版本信息：
 * 日期：2018年6月10日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jmx.example;

/**
 * HelloWorldMBean
 * 
 * http://www.cnblogs.com/dongguacai/p/5900507.html
 * 
 * Standard MBean是JMX管理构件中最简单的一种，只需要开发一个MBean接口（为了实现Standard MBean，必须遵循一套继承规范。必须每一个MBean定义一个接口， 而且这个接口的名字必须是其被管理的资源的对象类的名称后面加上”MBean”），一个实现MBean接口的类，并且把它们注册到MBeanServer中就可以了。
 * 
 * @author：youngzil@163.com
 * @2018年6月10日 下午11:09:05
 * @since 1.0
 */
public interface HelloWorldMBean {
    String getGreeting();

    void setGreeting(String greeting);

    void printGreeting();

    void printHello(String str);
}
