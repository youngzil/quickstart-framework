package org.quickstart.javase.jdk.juc.util;

import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @version v1.0
 * @date 2020/12/6 21:59
 */

/**
 * CyclicBarrier是一个同步屏障
 * CyclicBarrier让一个线程达到屏障时被阻塞，直到最后一个线程达到屏障时，屏障才会开门，所有被屏障拦截的线程才会继续执行
 * CyclicBarrier(int parties, Runnable barrierAction)构造函数，用于在所有线程都到达屏障后优先执行barrierAction的run()方法
 * CyclicBarrier使用场景：
 * 可以用于多线程计算以后，最后使用合并计算结果的场景；
 * <p>
 * 以下列子就是：使用5个线程分别向Map中放置计算好的数据，最后由Action来执行合并结果的功能；
 * <p>
 * 原理：
 * CyclicBarrier中有一个计数器，每当一个线程调用await()方法时计数器就会减1
 * 计数器不等于0时，会通过ReentrantLock重入所的condition的await()方法将线程阻塞
 * 直到计数器等于0时，会检测是否有barrierAction，如果有则执行barrierAction的run方法，然后唤醒signalAll()所有阻塞线程
 * 如果没有barrierAction则直接通过signalAll()唤醒所有阻塞线程
 */
public class CyclicBarrierTest0 {

    private static ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<String, Integer>();

    public static void main(String[] args) {
        /**
         * CyclicBarrier会阻塞5个线程，当5个线程都到达屏障时会优先执行Action的run()方法
         */
        CyclicBarrier c = new CyclicBarrier(5, new Action());
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                /**
                 * 将计算完成的结果放入Map中
                 */
                map.put(String.valueOf(Thread.currentThread().getId()), 5);
                try {
                    /**
                     * 被屏障拦截
                     */
                    c.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    /**
     * 屏障开启后，优先执行Action的run()方法合并结果
     */
    private static class Action implements Runnable {

        @Override
        public void run() {
            int j = 0;
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                System.out.println(entry.getValue());
                j = j + entry.getValue();
            }
            System.out.println("j=" + j);
        }
    }
}
