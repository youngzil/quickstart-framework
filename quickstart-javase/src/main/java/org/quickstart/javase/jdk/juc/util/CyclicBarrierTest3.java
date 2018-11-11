/**
 * 项目名称：quickstart-javase 
 * 文件名：CyclicBarrierTest3.java
 * 版本信息：
 * 日期：2017年6月27日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.juc.util;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrierTest3
 * 
 * @author：youngzil@163.com
 * @2017年6月27日 下午11:33:49
 * @version 1.0
 */
public class CyclicBarrierTest3 {

    static CyclicBarrier c = new CyclicBarrier(2);

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    c.await();
                } catch (Exception e) {
                }
            }
        });
        thread.start();
        thread.interrupt();
        try {
            c.await();
        } catch (Exception e) {
            System.out.println(c.isBroken());
        }
    }
}
