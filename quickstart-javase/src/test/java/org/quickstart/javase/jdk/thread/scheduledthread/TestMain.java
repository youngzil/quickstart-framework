/**
 * 项目名称：msgframe-client 
 * 文件名：TestMain.java
 * 版本信息：
 * 日期：2017年12月18日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.thread.scheduledthread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TestMain
 * 
 * @author：youngzil@163.com
 * @2017年12月18日 下午5:07:05
 * @since 1.0
 */
public class TestMain {
    public static void main(String[] args) throws InterruptedException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(5);

        Runnable dd = new Runnable() {

            public void run() {
                System.out.println("ScheduledThreadPoolExecutorTest.scheduledThreadPoolExecutor.getActiveCount()=" + pool.getActiveCount());
                System.out.println("ScheduledThreadPoolExecutorTest.scheduledThreadPoolExecutor.getPoolSize()=" + pool.getPoolSize());
                System.out.println("name=" + Thread.currentThread().getName());
            }
        };

        for (int i = 0; i < 5; i++) {
            final int temp = i + 1;
            pool.schedule(dd, 1, TimeUnit.SECONDS);

            /* pool.schedule(() -> {
                System.out.println("第" + temp + "个炸弹爆炸时间:" + simpleDateFormat.format(new Date()));
            }, temp * 5, TimeUnit.SECONDS);*/

        }
        // pool.shutdown();
        System.out.println("end main时间:" + simpleDateFormat.format(new Date()));

    }
}
