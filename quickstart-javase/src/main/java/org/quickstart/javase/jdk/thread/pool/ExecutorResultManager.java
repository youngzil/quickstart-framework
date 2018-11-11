/**
 * 项目名称：quickstart-javase 
 * 文件名：ExecutorResultManager.java
 * 版本信息：
 * 日期：2018年4月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk.thread.pool;

/**
 * ExecutorResultManager 
 *  
 * @author：youngzil@163.com
 * @2018年4月27日 下午12:39:45 
 * @since 1.0
 */
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

public class ExecutorResultManager {

    public static void main(String[] args) {
        // 队列
        BlockingQueue<Future<String>> futures = new LinkedBlockingQueue<>();

        // 生产者
        new Thread() {
            @Override
            public void run() {
                ExecutorService pool = Executors.newCachedThreadPool();

                for (int i = 0; i < 10; i++) {
                    int index = i;
                    Future<String> submit = pool.submit(new Callable<String>() {
                        @Override
                        public String call() throws Exception {
                            return "task done" + index;
                        }
                    });
                    try {
                        futures.put(submit);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        // 消费者
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    for (Future<String> future : futures) {
                        if (future.isDone()) {
                            // 处理业务
                            // .............

                        } ;
                    }
                }
            }
        }.start();
    }

}
