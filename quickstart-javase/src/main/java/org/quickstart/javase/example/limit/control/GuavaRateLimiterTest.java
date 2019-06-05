/**
 * 项目名称：quickstart-javase 
 * 文件名：GuavaRateLimiterTest.java
 * 版本信息：
 * 日期：2018年11月24日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.example.limit.control;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;

/**
 * GuavaRateLimiterTest
 * 
 * @author：youngzil@163.com
 * @2018年11月24日 上午12:06:31
 * @since 1.0
 */
public class GuavaRateLimiterTest {
    @Test
    public void test1() {
        // 新建一个每秒限制3个的令牌桶
        RateLimiter rateLimiter = RateLimiter.create(3.0);

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(100);
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                // 获取令牌桶中一个令牌，最多等待10秒
                if (rateLimiter.tryAcquire(1, 10, TimeUnit.SECONDS)) {
                    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                }
            });
        }

        executor.shutdown();
    }

    @Test
    public void test2() {

        // 线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        // 速率是每秒只有5个许可
        final RateLimiter rateLimiter = RateLimiter.create(3.0);

        for (int i = 0; i < 10; i++) {
            final int no = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        // 获取许可
                        rateLimiter.acquire();
                        System.out.println("Accessing: " + no + ",time:" + new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(new Date()));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            };
            // 执行线程
            exec.execute(runnable);
        }
        // 退出线程池
        exec.shutdown();

    }

    @Test
    public void testSemaphore() {
        // 线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        // 只能5个线程同时访问
        final Semaphore semp = new Semaphore(3);

        for (int i = 0; i < 10; i++) {
            final int no = i;
            // 执行线程
            exec.execute(() -> {
                try {
                    // 获取许可
                    semp.acquire();
                    System.out.println("Accessing: " + no + " --- " + new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(new Date()));
                    // 睡5s
                    Thread.sleep(5000);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // 访问完后,释放许可，如果注释掉下面的语句,则控制台只能打印3条记录,之后线程一直阻塞
                    semp.release();
                }
            });
        }
        // 退出线程池
        exec.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        // testA();

        GuavaRateLimiterTest.updateResourceQps("methodB", 5.0);
        testB();

    }

    // key-value (service,Qps) ，接口服务的限制速率
    private static final ConcurrentMap<String, Double> resourceMap = Maps.newConcurrentMap();
    // userkey-service,limiter ,限制用户对接口的访问速率
    private static final ConcurrentMap<String, RateLimiter> userResourceLimiterMap = Maps.newConcurrentMap();

    static {
        // init ，初始化方法A的Qps为50
        resourceMap.putIfAbsent("methodA", 10.0);
    }

    public static void updateResourceQps(String resource, double qps) {
        resourceMap.put(resource, qps);
    }

    public static void removeResource(String resource) {
        resourceMap.remove(resource);
    }

    public static int enter(String resource, String userKey) {
        long t1 = System.currentTimeMillis();
        Double qps = resourceMap.get(resource);

        // 不限流
        if (qps == null || qps.doubleValue() == 0.0) {
            return 0;
        }

        String keySer = resource + userKey;
        RateLimiter rateLimiter = userResourceLimiterMap.get(keySer);
        // if null , new limit
        if (rateLimiter == null) {
            rateLimiter = RateLimiter.create(qps);

            RateLimiter putByOtherThread = userResourceLimiterMap.putIfAbsent(keySer, rateLimiter);
            if (putByOtherThread != null) {
                rateLimiter = putByOtherThread;
            }
            rateLimiter.setRate(qps);
        }

        // 非阻塞
        if (!rateLimiter.tryAcquire()) {
            // 限速中，提示用户
            System.out.println("use :" + (System.currentTimeMillis() - t1) + "ms ; " + resource + " visited too frequently by key:" + userKey);
            return 99;
        } else {
            // 正常访问
            System.out.println("use :" + (System.currentTimeMillis() - t1) + "ms ; ");
            return 0;
        }

    }

    private static void testA() throws InterruptedException {
        int i = 0;
        while (true) {
            i++;
            long t2 = System.currentTimeMillis();
            System.out.println(" begin:" + t2 + " , hanchao:" + i);

            int result = GuavaRateLimiterTest.enter("methodA", "hanchao");
            if (result == 99) {
                i = 0;
                Thread.sleep(1000);
            }
        }
    }

    private static void testB() throws InterruptedException {
        // 测试other
        int y = 0;
        while (true) {
            y++;
            long t3 = System.currentTimeMillis();
            System.out.println(" begin:" + t3 + " , tom:" + y);

            int result2 = GuavaRateLimiterTest.enter("methodB", "tom");
            if (result2 == 99) {
                y = 0;
                Thread.sleep(1000);
            }
        }
    }

}
