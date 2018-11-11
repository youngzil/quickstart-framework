/**
 * 项目名称：quickstart-javase 
 * 文件名：CyclicBarrierTest.java
 * 版本信息：
 * 日期：2017年6月27日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.juc.util;

import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrierTest http://ifeve.com/concurrency-cyclicbarrier/
 * 
 * @author：youngzil@163.com
 * @2017年6月27日 下午11:32:25
 * @version 1.0
 */
public class CyclicBarrierTest {
    static CyclicBarrier c = new CyclicBarrier(2);

    public static void main(String[] args) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    c.await();
                } catch (Exception e) {

                }
                System.out.println(1);
            }
        }).start();

        try {
            c.await();
        } catch (Exception e) {

        }
        System.out.println(2);
    }
}
