/**
 * 项目名称：quickstart-javase 
 * 文件名：BlockingQueueTest.java
 * 版本信息：
 * 日期：2017年7月23日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.juc.queue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * BlockingQueueTest
 * 
 * @author：youngzil@163.com
 * @2017年7月23日 下午6:21:47
 * @version 2.0
 */
public class BlockingQueueTest {
    private int queueSize = 10;
    private ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(queueSize);

    public static void main(String[] args) {
        BlockingQueueTest test = new BlockingQueueTest();
        Producer producer = test.new Producer();
        Consumer consumer = test.new Consumer();

        producer.start();
        consumer.start();
    }

    class Consumer extends Thread {

        @Override
        public void run() {
            consume();
        }

        private void consume() {
            while (true) {
                try {
                    queue.take();
                    System.out.println("从队列取走一个元素，队列剩余" + queue.size() + "个元素");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Producer extends Thread {

        @Override
        public void run() {
            produce();
        }

        private void produce() {
            while (true) {
                try {
                    queue.put(1);
                    System.out.println("向队列取中插入一个元素，队列剩余空间：" + (queueSize - queue.size()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
