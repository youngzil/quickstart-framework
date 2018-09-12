/**
 * 项目名称：quickstart-javase 
 * 文件名：StampedLockTest.java
 * 版本信息：
 * 日期：2018年8月28日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk8;

import java.util.concurrent.locks.StampedLock;

/**
 * StampedLockTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年8月28日 下午10:52:34
 * @since 1.0
 */
public class StampedLockTest {

    public static void main(String[] args) {

        StampedLock lock = new StampedLock();

        long stamp = lock.tryOptimisticRead(); // 非阻塞路径——超级快
        work(); // 我们希望不要有写操作在这时发生
        if (lock.validate(stamp)) {
            // 成功！没有写操作干扰
        } else {
            // 肯定同时有另外一个线程获得了写操作锁，改变了时间戳
            // 懒汉说——我们切换到开销更大的锁吧

            stamp = lock.readLock(); // 这是传统的读操作锁，会阻塞
            try {
                // 现在不可能有写操作发生了
                work();

            } finally {
                lock.unlock(stamp); // 使用对应的时间戳解锁
            }
        }

    }

    public static void work() {
        System.out.println("work!!!");
    }

}
