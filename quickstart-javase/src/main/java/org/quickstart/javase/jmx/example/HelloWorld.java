/**
 * 项目名称：quickstart-javase 
 * 文件名：HelloWorld.java
 * 版本信息：
 * 日期：2018年6月10日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jmx.example;

/**
 * HelloWorld
 * 
 * http://www.cnblogs.com/dongguacai/p/5900507.html
 * 
 * @author：yangzl@asiainfo.com
 * @2018年6月10日 下午11:09:24
 * @since 1.0
 */
public class HelloWorld implements HelloWorldMBean {
    private String greeting;

    public HelloWorld() {
        this.greeting = "hello world!";
    }

    public HelloWorld(String greeting) {
        this.greeting = greeting;
    }

    public String getGreeting() {
        System.out.println("method=getGreeting()");
        return greeting;
    }

    public void setGreeting(String greeting) {
        System.out.println("method=setGreeting()");
        this.greeting = greeting;
    }

    public void printGreeting() {
        System.out.println("method=printGreeting()--->" + greeting);
    }

    @Override
    public void printHello(String str) {
        System.out.println("method=printHello()--->" + str);
    }

}
