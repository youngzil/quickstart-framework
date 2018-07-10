/**
 * 项目名称：msgframe-client 
 * 文件名：ScheduledThreadPoolExec.java
 * 版本信息：
 * 日期：2017年12月18日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.thread.scheduledthread;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledThreadPoolExec
 * 
 * @author：yangzl@asiainfo.com
 * @2017年12月18日 下午5:13:56
 * @since 1.0
 */
public class ScheduledThreadPoolExec {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);

        System.out.println("sssssssssss");

        ScheduledFuture future2 = executor.schedule(new LongTask(), 3, TimeUnit.SECONDS);
        ScheduledFuture future1 = executor.schedule(new Task1(), 5, TimeUnit.SECONDS);

        BlockingQueue<ScheduledFuture> blockingQueue = new ArrayBlockingQueue<ScheduledFuture>(2, true);
        blockingQueue.add(future2);
        blockingQueue.add(future1);

        System.out.println("ddddddd");

        System.out.println(new Date());
        while (!blockingQueue.isEmpty()) {
            ScheduledFuture future = blockingQueue.poll();
            if (!future.isDone())
                blockingQueue.add(future);
            else
                System.out.println(future.get());
        }
        System.out.println(new Date());
        executor.shutdown();
    }

}
