package org.quickstart.javase.jdk.collection.queue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueTest {

    public static class conn {
        public static void main(String[] args) throws Exception {
            Queue<String> queue = new ConcurrentLinkedQueue<String>();
            for (int i = 0; i < 1000000; i++) {
                queue.add(String.valueOf(i));
            }
            int num = 10;//线程人个数
            for (int i = 0; i < num; i++) {
                new ThreadConn(queue);
            }

        }
    }

    public static class ThreadConn implements Runnable {
        Queue<String> queue;

        public ThreadConn(Queue<String> queue) {
            this.queue = queue;
            Thread thread = new Thread(this);
            thread.start();
        }

        public void run() {
            try {
                long sd = System.currentTimeMillis();
                while (queue.poll() != null) {
                    //这里是业务逻辑
                }
                long sn = System.currentTimeMillis();
                System.out.println(sn - sd);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * 一个基于链接节点的、无界的、线程安全的队列。此队列按照 FIFO（先进先出）原则对元素进行排序。队列的头部 是队列中时间最长的元素。队列的尾部
     * 是队列中时间最短的元素。新的元素插入到队列的尾部，队列检索操作从队列头部获得元素。当许多线程共享访问一个公共 collection
     * 时，ConcurrentLinkedQueue 是一个恰当的选择。此队列不允许 null 元素。
     */
    private void concurrentLinkedQueueTest() {
        ConcurrentLinkedQueue<String> concurrentLinkedQueue = new ConcurrentLinkedQueue<String>();
        concurrentLinkedQueue.add("a");
        concurrentLinkedQueue.add("b");
        concurrentLinkedQueue.add("c");
        concurrentLinkedQueue.offer("d"); // 将指定元素插入到此队列的尾部。
        concurrentLinkedQueue.peek(); // 检索并移除此队列的头，如果此队列为空，则返回 null。
        concurrentLinkedQueue.poll(); // 检索并移除此队列的头，如果此队列为空，则返回 null。

        for (String str : concurrentLinkedQueue) {
            System.out.println(str);
        }
    }

}
