/**
 * 项目名称：quickstart-javase 
 * 文件名：AtomicIntegerTest.java
 * 版本信息：
 * 日期：2017年7月25日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.juc.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicIntegerTest
 * 
 * @author：youngzil@163.com
 * @2017年7月25日 上午9:40:48
 * @version 2.0
 */
public class AtomicIntegerTest {

    // 备注：AtomicInteger 的最大值是2147483647，超过这个数字在递增的话就变成-2147483648

    private AtomicInteger value = new AtomicInteger();

    public int getValue() {
        return value.get();
    }

    public int increase() {
        return value.incrementAndGet();// 内部使用死循环for(;;)调用compareAndSet(current,
                                       // next)
        // return value.getAndIncrement();
    }

    public int increase(int i) {
        return value.addAndGet(i);// 内部使用死循环for(;;)调用compareAndSet(current,
                                  // next)
        // return value.getAndAdd(i);
    }

    public int decrease() {
        return value.decrementAndGet();// 内部使用死循环for(;;)调用compareAndSet(current,
                                       // next)
        // return value.getAndDecrement();
    }

    public int decrease(int i) {
        return value.addAndGet(-i);// 内部使用死循环for(;;)调用compareAndSet(current,
                                   // next)
        // return value.addAndGet(-i);
    }

    public static void main(String[] args) {
        final AtomicIntegerTest counter = new AtomicIntegerTest();
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(counter.increase());
                }
            });
        }
        service.shutdown();
    }

}
