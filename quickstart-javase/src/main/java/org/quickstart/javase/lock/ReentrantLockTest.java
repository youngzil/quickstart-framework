/**
 * 项目名称：quickstart-javase 
 * 文件名：ReentrantLockTest.java
 * 版本信息：
 * 日期：2017年7月18日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.lock;

/**
 * ReentrantLockTest 
 *  大多数情况下，大家可能都会选择使用synchronized来加锁，ReentrantLock确实是一种高级加锁工具，在确实需要一些 synchronized 所没有的特性的时候，比如时间锁等候、可中断锁等候、无块结构锁、多个条件变量或者锁投票。  
以下实现公平锁和非公平锁，公平锁在性能上会多消耗点
 * @author：yangzl@asiainfo.com
 * @2017年7月18日 下午9:17:20 
 * @version 2.0
 */
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final ReentrantLock fairlock = new ReentrantLock(true);
    private int n;

    public static void main(String[] args) {
        ReentrantLockTest rlt = new ReentrantLockTest();
        for (int i = 0; i < 100; i++) {
            Thread nonT = new Thread(new NonFairTestThread(rlt));
            nonT.setName("nonFair[" + (i + 1) + "]");
            nonT.start();

            Thread fairT = new Thread(new FairTestThread(rlt));
            fairT.setName("fair[" + (i + 1) + "]");
            fairT.start();
        }
    }

    // 非公平锁
    static class NonFairTestThread implements Runnable {
        private ReentrantLockTest rlt;

        public NonFairTestThread(ReentrantLockTest rlt) {
            this.rlt = rlt;
        }

        public void run() {
            lock.lock();
            try {
                rlt.setNum(rlt.getNum() + 1);
                System.out.println(Thread.currentThread().getName() + " nonfairlock***************" + rlt.getNum());
            } finally {
                lock.unlock();
            }
        }
    }

    // 公平锁
    static class FairTestThread implements Runnable {
        private ReentrantLockTest rlt;

        public FairTestThread(ReentrantLockTest rlt) {
            this.rlt = rlt;

        }

        public void run() {
            fairlock.lock();
            try {
                rlt.setNum(rlt.getNum() + 1);
                System.out.println(Thread.currentThread().getName() + "   fairlock=======" + rlt.getNum() + "   " + fairlock.getHoldCount() + " queuelength=" + fairlock.getQueueLength());
            } finally {
                fairlock.unlock();
            }

        }
    }

    public void setNum(int n) {
        this.n = n;
    }

    public int getNum() {
        return n;
    }
}
