/**
 * 项目名称：quickstart-javase 
 * 文件名：AtomicReferenceTest2.java
 * 版本信息：
 * 日期：2017年7月25日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.juc.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/**
 * AtomicReferenceTest2
 * 
 * @author：youngzil@163.com
 * @2017年7月25日 上午9:40:10
 * @version 2.0
 */
public class AtomicReferenceTest2 {
    public static void main(String[] args) throws InterruptedException {
        dfasd111();
    }

    private static AtomicReference<Integer> ar = new AtomicReference<Integer>(0);

    public static void dfasd111() throws InterruptedException {
        int t = 100;
        final int c = 100;
        final CountDownLatch latch = new CountDownLatch(t);
        for (int i = 0; i < t; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < c; i++) {
                        while (true) {
                            Integer temp = ar.get();
                            if (ar.compareAndSet(temp, temp + 1)) {
                                break;
                            }
                        }
                    }
                    latch.countDown();
                }
            }).start();
        }
        latch.await();
        System.out.println(ar.get()); // 10000000
    }

    public final void test() {
        System.out.println(this.getClass());
    }
}
