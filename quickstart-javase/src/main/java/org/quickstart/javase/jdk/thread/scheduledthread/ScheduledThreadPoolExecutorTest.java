/**
 * 项目名称：msgframe-client 
 * 文件名：ScheduledThreadPoolExecutorTest.java
 * 版本信息：
 * 日期：2017年12月18日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.thread.scheduledthread;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledThreadPoolExecutorTest
 * 
 * @author：yangzl@asiainfo.com
 * @2017年12月18日 下午5:01:37
 * @since 1.0
 */
public class ScheduledThreadPoolExecutorTest {

    private static ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(3);

    public static void main(String[] args) {

        scheduledThreadPoolExecutor.schedule(new Runnable() {

            public void run() {
                System.out.println("ScheduledThreadPoolExecutorTest.scheduledThreadPoolExecutor.getActiveCount()=" + ScheduledThreadPoolExecutorTest.scheduledThreadPoolExecutor.getActiveCount());
                System.out.println("ScheduledThreadPoolExecutorTest.scheduledThreadPoolExecutor.getPoolSize()=" + ScheduledThreadPoolExecutorTest.scheduledThreadPoolExecutor.getPoolSize());
                System.out.println("name=" + Thread.currentThread().getName());
            }

        }, 1, TimeUnit.SECONDS);

    }

}
