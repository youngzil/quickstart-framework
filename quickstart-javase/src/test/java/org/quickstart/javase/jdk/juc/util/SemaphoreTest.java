/**
 * 项目名称：quickstart-javase 
 * 文件名：SemaphoreTest.java
 * 版本信息：
 * 日期：2017年6月27日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.juc.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * SemaphoreTest http://ifeve.com/concurrency-semaphore/
 * 
 * @author：youngzil@163.com
 * @2017年6月27日 下午11:13:31
 * @version 1.0
 */
public class SemaphoreTest {
    private static final int THREAD_COUNT = 30;

    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);

    private static Semaphore s = new Semaphore(10);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        s.acquire();
                        System.out.println("save data");
                        s.release();
                    } catch (InterruptedException e) {
                    }
                }
            });
        }

        threadPool.shutdown();
    }

    /*Semaphore还提供一些其他方法：
    int availablePermits() ：返回此信号量中当前可用的许可证数。
    int getQueueLength()：返回正在等待获取许可证的线程数。
    boolean hasQueuedThreads() ：是否有线程正在等待获取许可证。
    void reducePermits(int reduction) ：减少reduction个许可证。是个protected方法。
    Collection getQueuedThreads() ：返回所有等待获取许可证的线程集合。是个protected方法。*/
}
