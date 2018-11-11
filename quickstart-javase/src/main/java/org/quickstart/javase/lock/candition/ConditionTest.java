/**
 * 项目名称：quickstart-javase 
 * 文件名：ConditionTest.java
 * 版本信息：
 * 日期：2018年4月28日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.lock.candition;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ConditionTest
 * 
 * https://blog.csdn.net/chenchaofuck1/article/details/51592429
 * 
 * @author：youngzil@163.com
 * @2018年4月28日 下午2:39:38
 * @since 1.0
 */
public class ConditionTest {
    public static void main(String[] arg) {
        Buffer2 buffer2 = new Buffer2(10);
        Producer2 producer2 = new Producer2(buffer2);
        Consumer2 consumer2 = new Consumer2(buffer2);
        for (int i = 0; i < 3; i++) {
            new Thread(producer2, "producer-" + i).start();
        }
        for (int i = 0; i < 3; i++) {
            new Thread(consumer2, "consumer-" + i).start();
        }
    }
}


class Buffer2 {
    private final Lock lock;
    private final Condition notFull;
    private final Condition notEmpty;
    private int maxSize;
    private List<Date> storage;

    Buffer2(int size) {
        // 使用锁lock，并且创建两个condition，相当于两个阻塞队列
        lock = new ReentrantLock();
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
        maxSize = size;
        storage = new LinkedList<>();
    }

    public void put() {
        lock.lock();
        try {
            while (storage.size() == maxSize) {// 如果队列满了
                System.out.print(Thread.currentThread().getName() + ": wait \n");;
                notFull.await();// 阻塞生产线程
            }
            storage.add(new Date());
            System.out.print(Thread.currentThread().getName() + ": put:" + storage.size() + "\n");
            Thread.sleep(1000);
            notEmpty.signalAll();// 唤醒消费线程
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void take() {
        lock.lock();
        try {
            while (storage.size() == 0) {// 如果队列满了
                System.out.print(Thread.currentThread().getName() + ": wait \n");;
                notEmpty.await();// 阻塞消费线程
            }
            Date d = ((LinkedList<Date>) storage).poll();
            System.out.print(Thread.currentThread().getName() + ": take:" + storage.size() + "\n");
            Thread.sleep(1000);
            notFull.signalAll();// 唤醒生产线程

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}


class Producer2 implements Runnable {
    private Buffer2 buffer;

    Producer2(Buffer2 b) {
        buffer = b;
    }

    @Override
    public void run() {
        while (true) {
            buffer.put();
        }
    }
}


class Consumer2 implements Runnable {
    private Buffer2 buffer;

    Consumer2(Buffer2 b) {
        buffer = b;
    }

    @Override
    public void run() {
        while (true) {
            buffer.take();
        }
    }
}
