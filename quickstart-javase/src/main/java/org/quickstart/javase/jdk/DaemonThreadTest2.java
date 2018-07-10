/**
 * 项目名称：quickstart-javase 
 * 文件名：DaemonThreadTest.java
 * 版本信息：
 * 日期：2017年8月25日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk;

import java.util.concurrent.TimeUnit;

/**
 * DaemonThreadTest
 * 
 * @author：yangzl@asiainfo.com
 * @2017年8月25日 上午11:12:08
 * @since 1.0
 */
public class DaemonThreadTest2 {
    public static void main(String[] args) {
        Thread mainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread childThread = new Thread(new ClildThread());
                childThread.setDaemon(true);
                childThread.start();
                System.out.println("I'm main thread...");
            }
        });
        mainThread.start();

        // 不是说当子线程是守护线程，主线程结束，子线程就跟着结束，这里的前提条件是：当前jvm应用实例中没有用户线程继续执行，如果有其他用户线程继续执行，那么后台线程不会中断，
        Thread otherThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("I'm other user thread...");
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        otherThread.start();

        try {
            TimeUnit.SECONDS.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}


class ClildThread implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("I'm child thread..");
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
