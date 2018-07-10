package com.quickstart.test.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class TestQueue {
    public static void main(String[] args) {

        Queue<String> queue = new LinkedList<String>();
        queue.offer("1");// 插入一个元素
        queue.offer("2");
        queue.offer("3");
        // 打印元素个数
        System.out.println("queue.size()---------->" + queue.size());
        // 遍历打印所有的元素,安装插入是顺序打印
        for (String string : queue) {
            System.out.println(string);
        }

        Queue<String> queue2 = new ConcurrentLinkedQueue<String>();
        queue2.offer("hehe");
        queue2.offer("haha");
        queue2.offer("xixi");

        System.out.println("ConcurrentLinkedQueue--------------------->");
        for (String string : queue2) {
            System.out.println(string);
        }

        BlockingQueue<String> blockingQueue1 = new ArrayBlockingQueue<String>(2);
        BlockingQueue<String> blockingQueue2 = new LinkedBlockingQueue<String>();
        BlockingQueue<String> blockingQueue3 = new SynchronousQueue<String>();

        blockingQueue1.offer("hello");
        blockingQueue1.offer("world");
        blockingQueue1.offer("ArrayBlockingQueue");
        System.out.println("ArrayBlockingQueue<String>(0)--------------------->");
        for (String string : blockingQueue1) {
            System.out.println(string);
        }

        blockingQueue2.offer("hello");
        blockingQueue2.offer("world");
        blockingQueue2.offer("LinkedBlockingQueue");
        System.out.println("LinkedBlockingQueue<String>()--------------------->");
        for (String string : blockingQueue2) {
            System.out.println(string);
        }

        blockingQueue3.offer("hello");
        blockingQueue3.offer("world");
        blockingQueue3.offer("SynchronousQueue");
        System.out.println("SynchronousQueue<String>()测试1--------------------->");
        for (String string : blockingQueue3) {
            System.out.println(string);
        }

        System.out.println("SynchronousQueue<String>()测试2--------------------->");
        try {
            blockingQueue3.take();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        blockingQueue3.offer("hello");
        System.out.println(blockingQueue3.contains("hello"));
    }
}
