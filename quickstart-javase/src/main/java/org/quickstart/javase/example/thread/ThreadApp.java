/**
 * 项目名称：quickstart-javase 
 * 文件名：ThreadApp.java
 * 版本信息：
 * 日期：2017年7月26日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.example.thread;

/**
 * ThreadApp 生产者－消费者模型之程序入口
 * 
 * @author：youngzil@163.com
 * @2017年7月26日 下午7:38:16
 * @version 2.0
 */
public class ThreadApp {
    public static void main(String[] args) {

        /**
         * 仓库初始化
         */
        Godown godown = new Godown(30);

        /**
         * 消费者初始化
         */
        Consumer consumer0 = new Consumer(30, godown);
        Consumer consumer1 = new Consumer(20, godown);
        Consumer consumer2 = new Consumer(40, godown);

        /**
         * 生产者初始化
         */
        Producer producer0 = new Producer(10, godown);
        Producer producer1 = new Producer(10, godown);
        Producer producer2 = new Producer(10, godown);
        Producer producer3 = new Producer(10, godown);
        Producer producer4 = new Producer(10, godown);
        Producer producer5 = new Producer(10, godown);
        Producer producer6 = new Producer(10, godown);
        Producer producer7 = new Producer(10, godown);

        /**
         * 标记每个生产者/消费者
         */
        consumer0.setName("consumer0");
        consumer1.setName("consumer1");
        consumer2.setName("consumer2");
        producer0.setName("producer0");
        producer1.setName("producer1");
        producer2.setName("producer2");
        producer3.setName("producer3");
        producer4.setName("producer4");
        producer5.setName("producer5");
        producer6.setName("producer6");
        producer7.setName("producer7");

        /**
         * 开始进行生产－消费
         */
        consumer0.start();
        consumer1.start();
        consumer2.start();
        producer0.start();
        producer1.start();
        producer2.start();
        producer3.start();
        producer4.start();
        producer5.start();
        producer6.start();
        producer7.start();
    }
}
