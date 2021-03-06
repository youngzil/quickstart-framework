/**
 * 项目名称：quickstart-javase 
 * 文件名：ExchangerTest.java
 * 版本信息：
 * 日期：2017年6月27日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.juc.util;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ExchangerTest http://ifeve.com/concurrency-exchanger/
 * 
 * @author：youngzil@163.com
 * @2017年6月27日 下午11:36:30
 * @version 1.0
 */
public class ExchangerTest {
    private static final Exchanger<String> exgr = new Exchanger<String>();

    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String A = "银行流水A";// A录入银行流水数据
                    exgr.exchange(A);
                } catch (InterruptedException e) {
                }
            }
        });

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String B = "银行流水B";// B录入银行流水数据
                    String A = exgr.exchange("B");
                    System.out.println("A和B数据是否一致：" + A.equals(B) + ",A录入的是：" + A + ",B录入是：" + B);
                } catch (InterruptedException e) {
                }
            }
        });

        threadPool.shutdown();

    }
}
