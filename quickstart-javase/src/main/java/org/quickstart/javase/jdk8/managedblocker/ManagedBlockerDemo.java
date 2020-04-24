package org.quickstart.javase.jdk8.managedblocker;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ForkJoinPool;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2020/4/22 18:09
 */
public class ManagedBlockerDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> bq = new ArrayBlockingQueue<String>(2);
        bq.put("A");
        bq.put("B");
        QueueManagedBlocker<String> blocker  =  new QueueManagedBlocker<String>(bq);
        ForkJoinPool.managedBlock(blocker);
        System.out.println(blocker.getValue());
    }
}
