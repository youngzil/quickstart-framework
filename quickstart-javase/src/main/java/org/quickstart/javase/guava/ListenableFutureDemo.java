/**
 * 项目名称：quickstart-javase 
 * 文件名：ListenableFutureDemo.java
 * 版本信息：
 * 日期：2017年6月27日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.guava;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.RateLimiter;

/**
 * ListenableFutureDemo
 * 
 * @author：youngzil@163.com
 * @2017年6月27日 下午10:40:00
 * @version 1.0
 */

/*摘要: ListenableFuture顾名思义就是可以监听的Future，它是对java原生Future的扩展增强 RateLimiter类似于JDK的信号量Semphore，他用来限制对资源并发访问的线程数，本文介绍RateLimiter使用
概念

        ListenableFuture顾名思义就是可以监听的Future，它是对java原生Future的扩展增强。我们知道Future表示一个异步计算任务，当任务完成时可以得到计算结果。如果我们希望一旦计算完成就拿到结果展示给用户或者做另外的计算，就必须使用另一个线程不断的查询计算状态。这样做，代码复杂，而且效率低下。使用ListenableFuture Guava帮我们检测Future是否完成了，如果完成就自动调用回调函数，这样可以减少并发程序的复杂度。      

        推荐使用第二种方法，因为第二种方法可以直接得到Future的返回值，或者处理错误情况。本质上第二种方法是通过调动第一种方法实现的，做了进一步的封装。

另外ListenableFuture还有其他几种内置实现：

SettableFuture：不需要实现一个方法来计算返回值，而只需要返回一个固定值来做为返回值，可以通过程序设置此Future的返回值或者异常信息

CheckedFuture： 这是一个继承自ListenableFuture接口，他提供了checkedGet()方法，此方法在Future执行发生异常时，可以抛出指定类型的异常。


    RateLimiter类似于JDK的信号量Semphore，他用来限制对资源并发访问的线程数，本文介绍RateLimiter使用*/

public class ListenableFutureDemo {

    public static void main(String[] args) {
        testRateLimiter();
        testListenableFuture();
    }

    /**
     * RateLimiter类似于JDK的信号量Semphore，他用来限制对资源并发访问的线程数
     */
    public static void testRateLimiter() {
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

        RateLimiter limiter = RateLimiter.create(5.0); // 每秒不超过4个任务被提交

        for (int i = 0; i < 10; i++) {
            limiter.acquire(); // 请求RateLimiter, 超过permits会被阻塞

            final ListenableFuture<Integer> listenableFuture = executorService.submit(new Task("is " + i));
        }
    }

    @SuppressWarnings("deprecation")
    public static void testListenableFuture() {
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

        final ListenableFuture<Integer> listenableFuture = executorService.submit(new Task("testListenableFuture"));

        // 同步获取调用结果
        try {
            System.out.println(listenableFuture.get());
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }

        // 第一种方式
        listenableFuture.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("get listenable future's result " + listenableFuture.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }, executorService);

        // 第二种方式
        Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                System.out.println("get listenable future's result with callback " + result);
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }
}


class Task implements Callable<Integer> {
    String str;

    public Task(String str) {
        this.str = str;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("call execute.." + str);
        TimeUnit.SECONDS.sleep(1);
        return 7;
    }

}
