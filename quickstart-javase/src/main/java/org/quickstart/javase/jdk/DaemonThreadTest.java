/**
 * 项目名称：quickstart-javase 
 * 文件名：DaemonThreadTest.java
 * 版本信息：
 * 日期：2017年8月25日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk;

import java.util.concurrent.TimeUnit;

/**
 * DaemonThreadTest
 * 
 * @author：youngzil@163.com
 * @2017年8月25日 上午11:12:08
 * @since 1.0
 */
public class DaemonThreadTest {
    public static void main(String[] args) {
        Thread mainThread = new Thread(new Runnable() {
            public void run() {
                System.out.println("主线程开始...");
                Thread sonThread = new Thread(new Thread1(Thread.currentThread()));
                sonThread.setDaemon(false);
                sonThread.start();

                try {
                    TimeUnit.MILLISECONDS.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("主线程结束");
            }
        });
        mainThread.start();
    }
}


class Thread1 implements Runnable {
    private Thread mainThread;

    public Thread1(Thread mainThread) {
        this.mainThread = mainThread;
    }

    @Override
    public void run() {
        while (mainThread.isAlive()) {
            System.out.println("子线程运行中....");
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
