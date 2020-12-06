/**
 * 项目名称：quickstart-javase 
 * 文件名：ReentrantLock2.java
 * 版本信息：
 * 日期：2017年7月9日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock2 本文里面讲的是广义上的可重入锁，而不是单指JAVA下的ReentrantLock。
 * 
 * 可重入锁，也叫做递归锁，指的是同一线程 外层函数获得锁之后 ，内层递归函数仍然有获取该锁的代码，但不受影响。 在JAVA环境下 ReentrantLock 和synchronized 都是 可重入锁 可重入锁最大的作用是避免死锁
 * 
 * @author：youngzil@163.com
 * @2017年7月9日 下午4:19:11
 * @version 1.0
 */
public class ReentrantLockTest2 implements Runnable {
    ReentrantLock lock = new ReentrantLock();

    public void get() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        set();
        lock.unlock();
    }

    public void set() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        lock.unlock();
    }

    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        ReentrantLockTest2 ss = new ReentrantLockTest2();
        new Thread(ss).start();
        new Thread(ss).start();
        new Thread(ss).start();
    }

}
