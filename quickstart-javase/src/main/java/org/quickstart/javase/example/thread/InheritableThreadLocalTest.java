/**
 * 项目名称：quickstart-javase 
 * 文件名：InheritableThreadLocalTest.java
 * 版本信息：
 * 日期：2017年8月22日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.example.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * InheritableThreadLocalTest
 * 
 * @author：youngzil@163.com
 * @2017年8月22日 下午8:22:35
 * @since 1.0
 */
public class InheritableThreadLocalTest {

    public static void main(String[] args) throws InterruptedException {
        final InheritableThreadLocal<Span> inheritableThreadLocal = new InheritableThreadLocal<Span>();
        inheritableThreadLocal.set(new Span("xiexiexie"));
        // 输出 xiexiexie
        Object o = inheritableThreadLocal.get();
        System.out.println("初始主线程：" + o);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("******runnable****");
                System.out.println(inheritableThreadLocal.get());
                inheritableThreadLocal.set(new Span("zhangzhangzhang"));
                System.out.println(inheritableThreadLocal.get());
            }
        };

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("*****runnable2*****");
                Span span = inheritableThreadLocal.get();
                System.out.println(span);
                span.name = "liuliuliu";// 修改父引用为liuliuliu
                System.out.println(inheritableThreadLocal.get());
                inheritableThreadLocal.set(new Span("zhangzhangzhang2"));
                System.out.println(inheritableThreadLocal.get());
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(runnable);
        TimeUnit.SECONDS.sleep(1);
        executorService.submit(runnable2);
        // executorService.submit(runnable);
        TimeUnit.SECONDS.sleep(1);
        System.out.println("========");
        Span span = inheritableThreadLocal.get();

        System.out.println(span);
    }

    static class Span {
        public String name;
        public int age;

        public Span(String name) {
            this.name = name;
        }

        /* (non-Javadoc)
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return this.name;
        }
    }

}
