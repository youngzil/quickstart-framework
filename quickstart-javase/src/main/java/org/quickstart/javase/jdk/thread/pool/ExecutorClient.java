/**
 * 项目名称：ddmp-test 
 * 文件名：ExecutorClient.java
 * 版本信息：
 * 日期：2017年10月12日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.thread.pool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ExecutorClient
 * 
 * @author：youngzil@163.com
 * @2017年10月12日 下午11:22:02
 * @since 1.0
 */
public class ExecutorClient {

    private static final Logger logger = LoggerFactory.getLogger(ExecutorClient.class);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        BlockingQueue<Runnable> clientQueue = new LinkedBlockingQueue<Runnable>(1);

        ThreadPoolExecutor mqttClientExecutor = new ThreadPoolExecutor(//
                1, //
                1000, //
                3000, //
                TimeUnit.MILLISECONDS, //
                clientQueue, //
                new ThreadFactoryImpl("client_executor_thread"));

        for (int i = 3; i < 8; i++) {
            mqttClientExecutor.submit(new ClientExecutorThread(i));
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

        while (true) {

            Date d = new Date(System.currentTimeMillis());
            System.out.println("----------------------------" + sdf.format(d) + "-------------------------------------");

            // 线程池的核心线程数【等于设置的CorePoolSize】
            System.out.println("this.mqttClientExecutor.getCorePoolSize()=" + mqttClientExecutor.getCorePoolSize());
            // 线程池的已经完成任务数【不断增加】
            System.out.println("this.mqttClientExecutor.getTaskCount()=" + mqttClientExecutor.getTaskCount());
            // 线程池的活跃线程数【介于CorePoolSize和MaximumPoolSize之间变化，在队列被填充满的情况下】
            System.out.println("this.mqttClientExecutor.getActiveCount()=" + mqttClientExecutor.getActiveCount());
            // 线程池的大小【不会变化，等于CorePoolSize】
            System.out.println("this.mqttClientExecutor.getPoolSize()=" + mqttClientExecutor.getPoolSize());

            Thread.sleep(1000);
        }

    }

}
