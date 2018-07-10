package com.quickstart.test.thread;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestScheduledThreadPoolExecutor {

    public static void main(String[] args) {

        System.out.println(System.currentTimeMillis());

        ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);

        exec.scheduleAtFixedRate(new Runnable() {// 每隔一段时间就触发异常

            @Override
            public void run() {
                // throw new RuntimeException();
                System.out.println("================");
            }

        }, 1000, 5000, TimeUnit.MILLISECONDS);

        exec.scheduleAtFixedRate(new Runnable() {// 每隔一段时间打印系统时间，证明两者是互不影响的

            @Override
            public void run() {
                System.out.println(System.nanoTime());
            }

        }, 1000, 2000, TimeUnit.MILLISECONDS);

    }

}
