package org.quickstart.javase.jdk.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/30 21:49
 * @version v1.0
 */
public class ThreadStateTest {

    public static void main(String[] args) throws InterruptedException {
        TestLock testLock = new TestLock();

        Thread thread2 = new Thread(() -> {
            testLock.myTestLock();
        }, "thread2");

        Thread thread1 = new Thread(() -> {
            testLock.myTestLock();
        }, "thread1");

        thread1.start();
        Thread.sleep(1000);

        thread2.start();
        Thread.sleep(1000);

        System.out.println("thread2****" + (thread2.getState()));
        System.out.println("thread1****" + (thread1.getState()));

        Thread.sleep(20000);
    }
}

@Slf4j
class TestLock {
    private final Lock lock = new ReentrantLock();

    public void myTestLock() {
        lock.lock();
        try {
            Thread.sleep(10000);
            log.info("testLock status");
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        } finally {
            lock.unlock();
        }
    }
}