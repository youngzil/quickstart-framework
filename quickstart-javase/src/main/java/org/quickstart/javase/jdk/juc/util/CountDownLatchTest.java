/**
 * 项目名称：quickstart-javase 
 * 文件名：CountDownLatchTest.java
 * 版本信息：
 * 日期：2017年6月27日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.juc.util;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatchTest http://ifeve.com/talk-concurrency-countdownlatch/
 * 
 * @author：youngzil@163.com
 * @2017年6月27日 下午11:31:59
 * @version 1.0
 */
public class CountDownLatchTest {
    static CountDownLatch c = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
                c.countDown();
                System.out.println(2);
                c.countDown();
            }
        }).start();

        c.await();
        System.out.println("3");
    }
}
