package com.quickstart.test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class newSingleThreadExecutor {

    public static void main(String[] args) {

        // 创建一个可重用固定线程数的线程池
        ExecutorService pool = Executors.newSingleThreadExecutor();
        // 创建一个可重用固定线程数的线程池
        ExecutorService pool2 = Executors.newFixedThreadPool(2);
        // 创建一个可重用固定线程数的线程池
        ExecutorService pool3 = Executors.newCachedThreadPool();

        // 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
        Thread t11 = new MyThread();
        Thread t12 = new MyThread();
        Thread t13 = new MyThread();
        Thread t14 = new MyThread();
        Thread t15 = new MyThread();

        Runnable t21 = new MyThread2();
        Runnable t22 = new MyThread2();
        Runnable t23 = new MyThread2();
        Runnable t24 = new MyThread2();
        Runnable t25 = new MyThread2();

        // 将线程放入池中进行执行
        pool.execute(t21);
        pool.execute(t22);
        pool.execute(t23);
        pool.execute(t24);
        pool.execute(t25);

        /*pool.execute(t11);
        pool3.execute(t12);
        pool.execute(t13);
        
        pool.execute(t23);
        pool.execute(t24);
        pool.execute(t25);
        
        
        pool2.execute(t14);
        pool.execute(t15);*/

        // 关闭线程池
        pool.shutdown();

        /*//直接执行线程
         Thread t1 = new MyThread();
        t1.start();
        
        MyThread2 m2 = new MyThread2();
        Thread mt1 = new Thread(m2);
        mt1.start();*/

    }

}


class MyThread extends Thread {

    @Override
    public void run() {

        // super.run();
        System.out.println("test newSingleThreadExecutor");
        System.out.println(Thread.currentThread().getName() + "正在执行。。。。。。");

        TestThreadLocal.setTest();

        System.out.println(TestThreadLocal.getTest());
    }
}


class MyThread2 implements Runnable {

    @Override
    public void run() {
        // super.run();
        System.out.println("test implements Runnable");
        System.out.println("implements Runnable:" + Thread.currentThread().getName() + "正在执行。。。。。。");

        TestThreadLocal.setTest();

        System.out.println(TestThreadLocal.getTest());
    }
}


class TestThreadLocal {

    public static final ThreadLocal<Integer> local = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public static int a = 0;

    public static void setTest() {
        local.set(local.get() + 1);
    }

    public static Integer getTest() {
        return local.get();
    }

    public static void setTest1() {
        a++;
    }

    public static Integer getTest1() {
        return a;
    }

}
