/**
 * 项目名称：quickstart-javase 
 * 文件名：BlockingQueueTest.java
 * 版本信息：
 * 日期：2018年3月31日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk.collection.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * BlockingQueueTest
 * 
 * @author：youngzil@163.com
 * @2018年3月31日 下午5:15:55
 * @since 1.0
 */
public class BlockingQueueTest {

    public static void main(String[] args) {
        // LinkedBlockingQueue,ConcurrentLinkedQueue
//        BlockingQueue<String> bq = new ArrayBlockingQueue<String>(20);
        BlockingQueue<String> bq = new LinkedBlockingQueue<>(20);

        System.out.println("---------------add、remove、element-----------------------");
        boolean add = bq.add("111");
        System.out.println("add= " + add);
        System.out.println("BlockingQueue=" + bq);

        String element = bq.element();
        System.out.println("element=" + element);
        System.out.println("BlockingQueue=" + bq);

        boolean remove = bq.remove("111");
        System.out.println("remove= " + remove);
        System.out.println("BlockingQueue=" + bq);

        System.out.println("---------------offer、poll、peek-----------------------");

        boolean offer = bq.offer("111");
        System.out.println("offer= " + offer);
        System.out.println("BlockingQueue=" + bq);

        String peek = bq.peek();
        System.out.println("peek=" + peek);
        System.out.println("BlockingQueue=" + bq);

        String poll = bq.poll();
        System.out.println("poll= " + poll);
        System.out.println("BlockingQueue=" + bq);

        try {
            System.out.println("---------------put、take-----------------------");
            bq.put("111");
            System.out.println("BlockingQueue=" + bq);

            String take = bq.take();
            System.out.println("take=" + take);
            System.out.println("BlockingQueue=" + bq);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
