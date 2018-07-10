/**
 * 项目名称：quickstart-web 
 * 文件名：CountDownLatchTest.java
 * 版本信息：
 * 日期：2017年1月10日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package com.quickstart.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatchTest
 * 
 * @author：yangzl@asiainfo.com
 * @2017年1月10日 下午3:36:08
 * @version 1.0
 */
public class CountDownLatchTest {

    // 模拟了100米赛跑，10名选手已经准备就绪，只等裁判一声令下。当所有人都到达终点时，比赛结束。
    public static void main(String[] args) throws InterruptedException {

        // 开始的倒数锁
        final CountDownLatch begin = new CountDownLatch(1);

        // 结束的倒数锁
        final CountDownLatch end = new CountDownLatch(10);

        // 十名选手
        final ExecutorService exec = Executors.newFixedThreadPool(10);

        // for (int index = 0; index < 10; index++) {
        // final int NO = index + 1;
        // Runnable run = new Runnable() {
        // public void run() {
        // try {
        // // 如果当前计数为零，则此方法立即返回。
        // // 等待
        // begin.await();
        // Thread.sleep((long) (Math.random() * 10000));
        // System.out.println("No." + NO + " arrived");
        // } catch (InterruptedException e) {
        // } finally {
        // // 每个选手到达终点时，end就减一
        // end.countDown();
        // }
        // }
        // };
        // exec.submit(run);
        // }
        System.out.println("Game Start");
        // begin减一，开始游戏
        begin.countDown();
        // 等待end变为0，即所有选手到达终点
        end.await(20000, TimeUnit.MILLISECONDS);
        System.out.println("Game Over");
        exec.shutdown();
    }
}
