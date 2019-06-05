/**
 * 项目名称：quickstart-javase 
 * 文件名：JoinCountDownLatchTest.java
 * 版本信息：
 * 日期：2017年6月27日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.juc.util;

/**
 * JoinCountDownLatchTest
 * 
 * @author：youngzil@163.com
 * @2017年6月27日 下午11:31:28
 * @version 1.0
 */
public class JoinCountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        Thread parser1 = new Thread(new Runnable() {
            @Override
            public void run() {}
        });

        Thread parser2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("parser2 finish");
            }
        });

        parser1.start();
        parser2.start();
        parser1.join();
        parser2.join();
        System.out.println("all parser finish");
    }

}
