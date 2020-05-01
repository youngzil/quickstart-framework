/**
 * 项目名称：quickstart-javase 
 * 文件名：SynchronizedTest.java
 * 版本信息：
 * 日期：2018年4月28日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.lock.candition;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * SynchronizedTest
 * 
 * https://blog.csdn.net/chenchaofuck1/article/details/51592429
 * 
 * @author：youngzil@163.com
 * @2018年4月28日 下午2:37:45
 * @since 1.0
 */
public class SynchronizedTest {

    public static void main(String[] arg) {
        Buffer buffer = new Buffer(10);
        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);
        // 创建线程执行生产和消费
        for (int i = 0; i < 3; i++) {
            new Thread(producer, "producer-" + i).start();
        }
        for (int i = 0; i < 3; i++) {
            new Thread(consumer, "consumer-" + i).start();
        }
    }
}


// 模拟生产和消费的对象
class Buffer {
    private int maxSize;
    private List<Date> storage;

    Buffer(int size) {
        maxSize = size;
        storage = new LinkedList<>();
    }

    // 生产方法
    public synchronized void put() {
        try {
            while (storage.size() == maxSize) {// 如果队列满了
                System.out.print(Thread.currentThread().getName() + ": wait \n");;
                wait();// 阻塞线程
            }
            storage.add(new Date());
            System.out.print(Thread.currentThread().getName() + ": put:" + storage.size() + "\n");
            Thread.sleep(1000);
            notifyAll();// 唤起线程
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 消费方法
    public synchronized void take() {
        try {
            while (storage.size() == 0) {// 如果队列满了
                System.out.print(Thread.currentThread().getName() + ": wait \n");;
                wait();// 阻塞线程
            }
            Date d = ((LinkedList<Date>) storage).poll();
            System.out.print(Thread.currentThread().getName() + ": take:" + storage.size() + "\n");
            Thread.sleep(1000);
            notifyAll();// 唤起线程
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}


// 生产者
class Producer implements Runnable {
    private Buffer buffer;

    Producer(Buffer b) {
        buffer = b;
    }

    @Override
    public void run() {
        while (true) {
            buffer.put();
        }
    }
}


// 消费者
class Consumer implements Runnable {
    private Buffer buffer;

    Consumer(Buffer b) {
        buffer = b;
    }

    @Override
    public void run() {
        while (true) {
            buffer.take();
        }
    }
}
//
