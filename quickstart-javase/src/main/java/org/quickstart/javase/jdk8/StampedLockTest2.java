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
 * @author：youngzil@163.com
 * @2018年8月28日 下午10:52:34
 * @since 1.0
 */
public class StampedLockTest2 {

    private int balance;
    private StampedLock lock = new StampedLock();

    public void conditionReadWrite(int value) {
        // 首先判断balance的值是否符合更新的条件
        long stamp = lock.readLock();
        while (balance > 0) {
            long writeStamp = lock.tryConvertToWriteLock(stamp);
            if (writeStamp != 0) { // 成功转换成为写锁
                stamp = writeStamp;
                balance += value;
                break;
            } else {
                // 没有转换成写锁，这里需要首先释放读锁，然后再拿到写锁
                lock.unlockRead(stamp);
                // 获取写锁
                stamp = lock.writeLock();
            }
        }
        lock.unlock(stamp);
    }

    public void optimisticRead() {
        long stamp = lock.tryOptimisticRead();
        int c = balance;
        // 这里可能会出现了写操作，因此要进行判断
        if (!lock.validate(stamp)) {
            // 要从新读取
            long readStamp = lock.readLock();
            c = balance;
            stamp = readStamp;
        }
        ///
        lock.unlockRead(stamp);
    }

    public void read() {
        long stamp = lock.readLock();
        lock.tryOptimisticRead();
        int c = balance;
        // ...
        lock.unlockRead(stamp);
    }

    public void write(int value) {
        long stamp = lock.writeLock();
        balance += value;
        lock.unlockWrite(stamp);
    }

}
