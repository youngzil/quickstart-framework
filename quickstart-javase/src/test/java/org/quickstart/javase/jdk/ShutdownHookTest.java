/**
 * 项目名称：quickstart-javase 
 * 文件名：ShutdownHookTest.java
 * 版本信息：
 * 日期：2017年8月25日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk;

/**
 * ShutdownHookTest
 * 
 * @author：youngzil@163.com
 * @2017年8月25日 上午11:07:02
 * @since 1.0
 */
public class ShutdownHookTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // 定义线程1
        Thread thread1 = new Thread() {
            public void run() {
                System.out.println("thread1...");
            }
        };
        // 定义线程2
        Thread thread2 = new Thread() {
            public void run() {
                System.out.println("thread2...");
            }
        };
        // 定义关闭线程
        Thread shutdownThread = new Thread() {
            public void run() {
                System.out.println("shutdownThread...");
            }
        };
        // jvm关闭的时候先执行该线程钩子
        Runtime.getRuntime().addShutdownHook(shutdownThread);
        thread1.start();
        thread2.start();
    }

}
