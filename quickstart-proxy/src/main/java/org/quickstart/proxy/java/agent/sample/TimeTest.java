/**
 * 项目名称：quickstart-proxy 
 * 文件名：TimeTest.java
 * 版本信息：
 * 日期：2018年8月12日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.proxy.java.agent.sample;

/**
 * TimeTest
 * 
 * @author：youngzil@163.com
 * @2018年8月12日 下午12:00:17
 * @since 1.0
 */
public class TimeTest {
    
//    java -javaagent:PreMain.jar=Hello1  -jar Main.jar

    public static void main(String[] args) {
        sayHello();
        sayHello2("hello world222222222");
    }

    public static void sayHello() {
        try {
            Thread.sleep(2000);
            System.out.println("hello world!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sayHello2(String hello) {
        try {
            Thread.sleep(1000);
            System.out.println(hello);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
