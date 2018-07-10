/**
 * 项目名称：quickstart-javase 
 * 文件名：AtomicLongTest.java
 * 版本信息：
 * 日期：2017年7月25日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.juc.atomic;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * AtomicLongTest
 * 
 * @author：yangzl@asiainfo.com
 * @2017年7月25日 上午9:42:52
 * @version 2.0
 */
public class AtomicLongTest {
    private AtomicLong balance;

    public AtomicLongTest(long money) {
        balance = new AtomicLong(money);
        System.out.println("Total Money:" + balance);
    }

    public void deposit(long money) {
        balance.addAndGet(money);
    }

    public void withdraw(long money) {
        for (;;) {// 保证即时同一时间有人也在取款也可以再次尝试取款，如果不需要并发尝试取款，可以去掉这句
            long oldValue = balance.get();
            if (oldValue < money) {
                System.out.println(Thread.currentThread().getName() + " 余额不足！ 余额：" + balance);
                break;
            }
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (Exception e) {
            } // 模拟取款时间
            if (balance.compareAndSet(oldValue, oldValue - money)) {
                System.out.println(Thread.currentThread().getName() + " 取款 " + money + " 成功！ 余额：" + balance);
                break;
            }
            System.out.println(Thread.currentThread().getName() + " 遇到并发，再次尝试取款！");
        }
    }

    public static void main(String[] args) {
        final AtomicLongTest account = new AtomicLongTest(1000);
        ExecutorService pool = Executors.newCachedThreadPool();
        int i = 0;
        while (i++ < 13) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    account.withdraw(100);
                }
            });
        }
        pool.shutdown();
    }
}
