/**
 * 项目名称：quickstart-javase
 * 文件名：ThreadPoolExecutorExtendTest.java
 * 版本信息：
 * 日期：2017年8月25日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.thread.pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

/**
 * ThreadPoolExecutorExtendTest
 *
 * @author：youngzil@163.com
 * @2017年8月25日 上午11:34:07
 * @since 1.0
 */
public class ThreadPoolExecutorExtendTest extends ThreadPoolExecutor {

    /*
     在测试类CheckTimingThreadPool中通过execute了五个线程，然后分别对这五个线程进行统计，最后统计出各个线程的耗时平均时间。
    这里说明下TimingThreadPool的构造函数，它直接调用了父类的构造方法，在ThreadPoolExecutor中有许多构造方法，有兴趣的朋友可以查看jdk api或者源码进行查看。
    简要说明下构造函数的参数的含义：
    corePoolSize:线程池维护线程的最少数量
    maximumPoolSize：线程池维护线程的最大数量
    keepAliveTime：线程池维护线程所允许的空闲时间
    unit：线程池维护所允许的空闲时间的单位
    workQueue：线程池所使用的缓存队列
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // ThreadPoolExecutor exec = new ThreadPoolExecutorExtendTest(2, 2, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        ThreadPoolExecutor exec = new ThreadPoolExecutorExtendTest(2, 2, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        Future task1 = exec.submit(new DoSomething(5));
        Future task2 = exec.submit(new DoSomething(4));
        Future task3 = exec.submit(new DoSomething(3));
        Future task4 = exec.submit(new DoSomething(2));
        Future task5 = exec.submit(new DoSomething(1));

        System.out.println("Future=" + task1);
        System.out.println("Future=" + task2);
        System.out.println("Future=" + task3);
        System.out.println("Future=" + task4);
        System.out.println("Future=" + task5);

        task1.get();
        task2.get();
        task3.get();
        task4.get();
        task5.get();

        exec.shutdown();
    }

    private final ThreadLocal<Long> startTime = new ThreadLocal<>();
    private final Logger log = Logger.getAnonymousLogger();
    private final AtomicLong numTasks = new AtomicLong();
    private final AtomicLong totalTime = new AtomicLong();

    public ThreadPoolExecutorExtendTest(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        System.out.println("Thread=" + t + ",Runnable=" + r);
        log.info(String.format("Thread %s: start %s", t, r));
        startTime.set(System.nanoTime());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        try {
            System.out.println("Runnable=" + r);

            long endTime = System.nanoTime();
            long taskTime = endTime - startTime.get();
            numTasks.incrementAndGet();
            totalTime.addAndGet(taskTime);
            log.info(String.format("Thread %s: end %s, time=%dns", t, r, taskTime));
        } finally {
            super.afterExecute(r, t);
        }
    }

    @Override
    protected void terminated() {
        try {
            log.info(String.format("Terminated: avg time=%dns", totalTime.get() / numTasks.get()));
        } finally {
            super.terminated();
        }
    }

    static class DoSomething implements Callable {
        private int sleepTime;

        public DoSomething(int sleepTime) {
            this.sleepTime = sleepTime;
        }

        @Override
        public String call() {
            System.out.println(Thread.currentThread().getName() + " is running.");
            try {
                TimeUnit.SECONDS.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "22";
        }

    }

}
