/**
 * 项目名称：quickstart-javase 
 * 文件名：TestInheritableThreadLocal.java
 * 版本信息：
 * 日期：2017年9月18日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.example.thread;

/**
 * TestInheritableThreadLocal
 * 
 * @author：youngzil@163.com
 * @2017年9月18日 下午10:04:30
 * @since 1.0
 */
public class TestInheritableThreadLocal {

    public static void main(String[] args) {
        testThreadLocal();
        testInheritableThreadLocal();
    }

    public static void testThreadLocal() {
        final ThreadLocal<String> local = new ThreadLocal<String>();
        work(local);
    }

    public static void testInheritableThreadLocal() {
        final ThreadLocal<String> local = new InheritableThreadLocal<String>();
        work(local);
    }

    private static void work(final ThreadLocal<String> local) {
        local.set("a");
        System.out.println(Thread.currentThread() + "," + local.get());
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "," + local.get());
                local.set("b");
                System.out.println(Thread.currentThread() + "," + local.get());
            }
        });

        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread() + "," + local.get());
    }

}
