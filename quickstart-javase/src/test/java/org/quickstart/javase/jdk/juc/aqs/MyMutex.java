package org.quickstart.javase.jdk.juc.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * <p>自定义互斥锁 </p >
 *
 * @author yangzl
 * @date 2020/8/30 23:10
 * @version v1.0
 */
public class MyMutex implements Lock {

    // 静态内部类-自定义同步器
    private static class MySync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            // 调用AQS提供的方法，通过CAS保证原子性
            if (compareAndSetState(0, arg)) {
                // 我们实现的是互斥锁，所以标记获取到同步状态（更新state成功）的线程，
                // 主要为了判断是否可重入（一会儿会说明）
                setExclusiveOwnerThread(Thread.currentThread());
                //获取同步状态成功，返回 true
                return true;
            }
            // 获取同步状态失败，返回 false
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            // 未拥有锁却让释放，会抛出IMSE
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            // 可以释放，清空排它线程标记
            setExclusiveOwnerThread(null);
            // 设置同步状态为0，表示释放锁
            setState(0);
            return true;
        }

        // 是否独占式持有
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        // 后续会用到，主要用于等待/通知机制，每个condition都有一个与之对应的条件等待队列，在锁模型中说明过
        Condition newCondition() {
            return new ConditionObject();
        }
    }

    // 聚合自定义同步器
    private final MySync sync = new MySync();

    @Override
    public void lock() {
        // 阻塞式的获取锁，调用同步器模版方法独占式，获取同步状态
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        // 调用同步器模版方法可中断式获取同步状态
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        // 调用自己重写的方法，非阻塞式的获取同步状态
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        // 调用同步器模版方法，可响应中断和超时时间限制
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        // 释放锁
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        // 使用自定义的条件
        return sync.newCondition();
    }
}
