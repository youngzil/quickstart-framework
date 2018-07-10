/**
 * 项目名称：quickstart-javase 
 * 文件名：CyclicBarrierTest2.java
 * 版本信息：
 * 日期：2017年6月27日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.juc.util;

import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrierTest2
 * 
 * @author：yangzl@asiainfo.com
 * @2017年6月27日 下午11:33:18
 * @version 1.0
 */
public class CyclicBarrierTest2 {

    // 在c.await();之后执行new A()
    static CyclicBarrier c = new CyclicBarrier(1, new A());

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

    static class A implements Runnable {

        @Override
        public void run() {
            System.out.println(3);
        }

    }

}
