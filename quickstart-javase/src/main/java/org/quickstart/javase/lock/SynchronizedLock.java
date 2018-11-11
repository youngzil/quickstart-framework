/**
 * 项目名称：quickstart-javase 
 * 文件名：SynchronizedLock.java
 * 版本信息：
 * 日期：2017年7月9日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.lock;

/**
 * SynchronizedLock 本文里面讲的是广义上的可重入锁，而不是单指JAVA下的ReentrantLock。
 * 
 * 可重入锁，也叫做递归锁，指的是同一线程 外层函数获得锁之后 ，内层递归函数仍然有获取该锁的代码，但不受影响。 在JAVA环境下 ReentrantLock 和synchronized 都是 可重入锁 可重入锁最大的作用是避免死锁
 * 
 * @author：youngzil@163.com
 * @2017年7月9日 下午4:18:24
 * @version 1.0
 */
public class SynchronizedLock implements Runnable {
    public synchronized void get() {
        System.out.println(Thread.currentThread().getId());
        set();
    }

    public synchronized void set() {
        System.out.println(Thread.currentThread().getId());
    }

    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        SynchronizedLock ss = new SynchronizedLock();
        new Thread(ss).start();
        new Thread(ss).start();
        new Thread(ss).start();
    }
}
