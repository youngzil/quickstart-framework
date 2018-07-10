/**
 * 项目名称：quickstart-javase 
 * 文件名：ThreadTest.java
 * 版本信息：
 * 日期：2017年7月23日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.thread;

import java.io.IOException;

/**
 * ThreadTest
 * 
 * @author：yangzl@asiainfo.com
 * @2017年7月23日 下午7:10:30
 * @version 2.0
 */
public class ThreadTest {
    private int i = 10;
    private Object object = new Object();

    public static void main(String[] args) throws IOException {
        ThreadTest test = new ThreadTest();
        MyThread thread1 = test.new MyThread();
        MyThread thread2 = test.new MyThread();
        
        thread1.start();
        thread2.start();
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                i++;
                System.out.println("i:" + i);
                try {
                    System.out.println("线程" + Thread.currentThread().getName() + "进入睡眠状态");
                    Thread.currentThread().sleep(10000);
                } catch (InterruptedException e) {
                    // TODO: handle exception
                }
                System.out.println("线程" + Thread.currentThread().getName() + "睡眠结束");
                i++;
                System.out.println("i:" + i);
            }
        }
    }
}
