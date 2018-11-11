/**
 * 项目名称：quickstart-javase 
 * 文件名：ReentrantReadWriteLockTest.java
 * 版本信息：
 * 日期：2017年7月18日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLockTest
 * 类ReentrantLock具有完全互斥排他的效果，即同一时间只有一个线程在执行ReentrantLock.lock()方法后面的任务。这样做虽然保证了实例变量的线程安全性，但效率却是非常低下的。所以在JDK中提供了一种读写锁ReentrantReadWriteLock类，使用它可以加快运行效率，在某些不需要操作实例变量的方法中，完全可以使用读写锁ReentrantReadWriteLock来提升该方法的代码运行速度。
 * 读写锁表示也有两个锁，一个是读操作相关的锁，也称为共享锁;另一个是写操作相关的锁，也叫排他锁。也就是多个读锁之间不互斥，读锁与写锁互斥，写锁与写锁互斥。在没有线程Thread进行写人操作时，进行读取操作的多个Thread都可以获取读锁，而进行写人操作的Thread只有在获取写锁后才能进行写人操作。即多个Thread可以同时进行读取操作，但是同一时刻只允许一个Thread进行写人操作。
 * 
 * @author：youngzil@163.com
 * @2017年7月18日 下午9:18:47
 * @version 2.0
 */
public class ReentrantReadWriteLockTest {

    // “读写”“、”写读“、”写写“都是互斥的；而”读读“是异步的，非互斥的
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void read() {
        try {
            lock.readLock().lock();
            System.out.println("获得读锁：" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            Thread.sleep(3000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }

    public void write() {
        try {
            lock.writeLock().lock();
            System.out.println("获得写锁：" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            Thread.sleep(3000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

}
