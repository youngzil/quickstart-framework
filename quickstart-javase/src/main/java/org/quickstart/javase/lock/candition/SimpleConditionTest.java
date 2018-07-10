/**
 * 项目名称：quickstart-javase 
 * 文件名：SimpleConditionTest.java
 * 版本信息：
 * 日期：2018年4月28日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.lock.candition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.experimental.theories.Theories;

/**
 * SimpleConditionTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年4月28日 下午3:52:32
 * @since 1.0
 */
public class SimpleConditionTest {

    public static void main(String[] args) throws InterruptedException {

        // 线程池的空闲等待时间，是使用BlockingQueue的poll超时时间，是调用ReentrantLock的Condition的awaitNanos实现，再调用LockSupport.parkNanos，最后还是调用unsafe的park方法

        final ReentrantLock lock = new ReentrantLock();

        final Condition condition = lock.newCondition();

        long timeout = 1000L;
        TimeUnit unit = TimeUnit.MILLISECONDS;
        long nanos = unit.toNanos(timeout);
        condition.awaitNanos(nanos);

        LockSupport.parkNanos(condition, nanos);// 内部调用unsafe的park方法
        
        condition.signal();
        condition.signalAll();
        LockSupport.unpark(Thread.currentThread());//内部调用unsafe的unpark方法

    }

}
