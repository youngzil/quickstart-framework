/**
 * 项目名称：quickstart-javase 
 * 文件名：TransmittableThreadLocalTest2.java
 * 版本信息：
 * 日期：2018年5月31日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.example.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.quickstart.javase.example.thread.TransmittableThreadLocalTest.Span;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

/**
 * TransmittableThreadLocalTest2
 * 
 * @author：yangzl@asiainfo.com
 * @2018年5月31日 下午12:25:31
 * @since 1.0
 */
public class TransmittableThreadLocalTest2 {
    public static void main(String[] args) throws Exception {
        final ThreadLocal<Person> threadLocal = new TransmittableThreadLocal<>();
        threadLocal.set(new Person("Java架构沉思录"));
        System.out.println("初始值：" + threadLocal.get());
        Runnable task = () -> {
            System.out.println("----------start------------");
            System.out.println("父线程的值：" + threadLocal.get());
            threadLocal.set(new Person("沉思君"));
            System.out.println("子线程覆盖后的值：" + threadLocal.get());
            System.out.println("------------end---------------");
        };
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Runnable runnable = TtlRunnable.get(task);//关键，只有包装了才可以实现多线程获取
        executorService.submit(runnable);
        TimeUnit.SECONDS.sleep(1);
        executorService.submit(runnable);
        TimeUnit.SECONDS.sleep(1);
        executorService.submit(runnable);

    }
}
