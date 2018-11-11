/**
 * 项目名称：quickstart-javase 
 * 文件名：TransmittableThreadLocalTest.java
 * 版本信息：
 * 日期：2017年8月22日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.example.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * TransmittableThreadLocalTest
 * 
 * @author：youngzil@163.com
 * @2017年8月22日 下午8:33:02
 * @since 1.0
 */
public class TransmittableThreadLocalTest {

    // https://github.com/alibaba/transmittable-thread-local/blob/master/src/main/java/com/alibaba/ttl/TransmittableThreadLocal.java

    public static void main(String[] args) throws InterruptedException {
        final TransmittableThreadLocal<Span> transmittableThreadLocal = new TransmittableThreadLocal<Span>();
        transmittableThreadLocal.set(new Span("xiexiexie"));
        // 输出 xiexiexie
        Object o = transmittableThreadLocal.get();
        System.out.println("初始主线程：" + o);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("**********");
                System.out.println(transmittableThreadLocal.get());
                transmittableThreadLocal.set(new Span("zhangzhangzhang"));
                System.out.println(transmittableThreadLocal.get());
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(runnable);
        TimeUnit.SECONDS.sleep(1);
        executorService.submit(runnable);
        TimeUnit.SECONDS.sleep(1);
        System.out.println("========");
        Span span = transmittableThreadLocal.get();

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
