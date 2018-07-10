/**
 * 项目名称：quickstart-javase 
 * 文件名：AtomicTest.java
 * 版本信息：
 * 日期：2017年7月9日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicTest 几天前，我偶然地将之前写的用来测试AtomicInteger和synchronized的自增性能的代码跑了一下，意外地发现AtomicInteger的性能比synchronized更好了，经过一番原因查找，有了如下发现：
 * 
 * 在jdk1.7中，AtomicInteger的getAndIncrement是这样的：
 * 
 * 
 * 01 public final int getAndIncrement() { 02 for (;;) { 03 int current = get(); 04 int next = current + 1; 05 if (compareAndSet(current, next)) 06 return current; 07 } 08 } 09 public final boolean
 * compareAndSet(int expect, int update) { 10 return unsafe.compareAndSwapInt(this, valueOffset, expect, update); 11 } 而在jdk1.8中，是这样的：
 * 
 * 1 public final int getAndIncrement() { 2 return unsafe.getAndAddInt(this, valueOffset, 1); 3 }
 * 可以看出，在jdk1.8中，直接使用了Unsafe的getAndAddInt方法，而在jdk1.7的Unsafe中，没有此方法。（PS:为了找出原因，我反编译了Unsafe，发现CAS的失败重试就是在getAndAddInt方法里完成的，我用反射获取到Unsafe实例，编写了跟getAndAddInt相同的代码，但测试结果却跟jdk1.7的getAndIncrement一样慢，不知道Unsafe里面究竟玩了什么黑魔法，还请高人不吝指点）（补充：文章末尾已有推论）
 * 
 * 通过查看AtomicInteger的源码可以发现，受影响的还有getAndAdd、addAndGet等大部分方法。
 * 
 * 有了这次对CAS的增强，我们又多了一个使用非阻塞算法的理由。
 * 
 * 最后给出测试代码，需要注意的是，此测试方法简单粗暴，compareAndSet的性能不如synchronized，并不能简单地说synchronized就更好，两者的使用方式是存在差异的，而且在实际使用中，还有业务处理，不可能有如此高的竞争强度，此对比仅作为一个参考，该测试能够证明的是，AtomicInteger.getAndIncrement的性能有了大幅提升。
 * 
 * @author：yangzl@asiainfo.com
 * @2017年7月9日 下午4:25:19
 * @version 1.0
 */
public class AtomicTest {
    // 测试规模，调用一次getAndIncreaseX视作提供一次业务服务，记录提供TEST_SIZE次服务的耗时
    private static final int TEST_SIZE = 100000000;
    // 客户线程数
    private static final int THREAD_COUNT = 10;
    // 使用CountDownLatch让各线程同时开始
    private CountDownLatch cdl = new CountDownLatch(THREAD_COUNT + 1);

    private int n = 0;
    private AtomicInteger ai = new AtomicInteger(0);
    private long startTime;

    public void init() {
        startTime = System.nanoTime();
    }

    /**
     * 使用AtomicInteger.getAndIncrement，测试结果为1.8比1.7有明显性能提升
     * 
     * @return
     */
    private final int getAndIncreaseA() {
        int result = ai.getAndIncrement();
        if (result == TEST_SIZE) {
            System.out.println(System.nanoTime() - startTime);
            System.exit(0);
        }
        return result;
    }

    /**
     * 使用synchronized来完成同步，测试结果为1.7和1.8几乎无性能差别
     * 
     * @return
     */
    private final int getAndIncreaseB() {
        int result;
        synchronized (this) {
            result = n++;
        }
        if (result == TEST_SIZE) {
            System.out.println(System.nanoTime() - startTime);
            System.exit(0);
        }
        return result;
    }

    /**
     * 使用AtomicInteger.compareAndSet在java代码层面做失败重试（与1.7的AtomicInteger.getAndIncrement的实现类似）， 测试结果为1.7和1.8几乎无性能差别
     * 
     * @return
     */
    private final int getAndIncreaseC() {
        int result;
        do {
            result = ai.get();
        } while (!ai.compareAndSet(result, result + 1));
        if (result == TEST_SIZE) {
            System.out.println(System.nanoTime() - startTime);
            System.exit(0);
        }
        return result;
    }

    public class MyTask implements Runnable {
        @Override
        public void run() {
            cdl.countDown();
            try {
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (true)
                getAndIncreaseA();// getAndIncreaseB();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicTest at = new AtomicTest();
        for (int n = 0; n < THREAD_COUNT; n++)
            new Thread(at.new MyTask()).start();
        System.out.println("start");
        at.init();
        at.cdl.countDown();
    }
}
