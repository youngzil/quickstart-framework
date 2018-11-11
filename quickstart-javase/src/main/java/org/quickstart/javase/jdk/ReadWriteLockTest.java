/**
 * 项目名称：quickstart-javase 
 * 文件名：ReadWriteLockTest.java
 * 版本信息：
 * 日期：2018年4月12日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.junit.Test;

/**
 * ReadWriteLockTest
 * 
 * @author：youngzil@163.com
 * @2018年4月12日 下午4:22:13
 * @since 1.0
 */
public class ReadWriteLockTest {

    /* 线程进入读锁的前提条件：
    　　 1. 没有其他线程的写锁
    2. 没有写请求，或者有写请求但调用线程和持有锁的线程是同一个线程
    进入写锁的前提条件：
    1. 没有其他线程的读锁
    2. 没有其他线程的写锁*/

    @Test
    public void testReadRead() {

        ReadWriteLock rtLock = new ReentrantReadWriteLock();
        rtLock.readLock().lock();
        System.out.println("get readLock.");
        rtLock.readLock().lock();
        System.out.println("non blocking");

    }

    @Test
    public void testReadWrite() {

        ReadWriteLock rtLock = new ReentrantReadWriteLock();
        rtLock.readLock().lock();
        System.out.println("get readLock.");
        rtLock.writeLock().lock();
        System.out.println("blocking");

    }

    @Test
    public void testWriteWrite() {

        ReadWriteLock rtLock = new ReentrantReadWriteLock();
        rtLock.writeLock().lock();
        System.out.println("get readLock.");
        rtLock.writeLock().lock();
        System.out.println("blocking");

    }

    @Test
    public void testWriteRead() {

        ReadWriteLock rtLock = new ReentrantReadWriteLock();
        rtLock.writeLock().lock();
        System.out.println("get readLock.");
        rtLock.readLock().lock();
        System.out.println("blocking");

    }

}
