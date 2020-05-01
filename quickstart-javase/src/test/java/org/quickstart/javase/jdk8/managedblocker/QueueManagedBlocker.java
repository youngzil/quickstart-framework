package org.quickstart.javase.jdk8.managedblocker;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ForkJoinPool;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2020/4/22 18:09
 */
public class QueueManagedBlocker<T> implements ForkJoinPool.ManagedBlocker {
    final BlockingQueue<T> queue;
    volatile T value = null;
    QueueManagedBlocker(BlockingQueue<T> queue) {
        this.queue = queue;
    }
    public boolean block() throws InterruptedException {
        if (value == null)
            value = queue.take();
        return true;
    }
    public boolean isReleasable() {
        return value != null || (value = queue.poll()) != null;
    }
    public T getValue() {
        return value;
    }
}
