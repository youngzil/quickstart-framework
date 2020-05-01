/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.quickstart.javase.lock;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Spin lock Implementation to put message, suggest using this witb low race conditions 自旋锁是采用让当前线程不停地的在循环体内执行实现的，当循环的条件被其他线程改变时 才能进入临界区。 由于自旋锁只是将当前线程不停地执行循环体，不进行线程状态的改变，所以响应速度更快。但当线程数不停增加时，性能下降明显，
 * 因为每个线程都需要执行，占用CPU时间。如果线程竞争不激烈，并且保持锁的时间段。适合使用自旋锁。 注：该例子为非公平锁，获得锁的先后顺序，不会按照进入lock的先后顺序进行。
 */
public class SpinLock {
    // true: Can lock, false : in lock.
    private AtomicBoolean spinLock = new AtomicBoolean(true);

    public void lock() {
        boolean flag;
        do {
            flag = this.spinLock.compareAndSet(true, false);
        } while (!flag);
    }

    public void unlock() {
        this.spinLock.compareAndSet(false, true);
    }

    // 方法2
    /**
     * 使用了CAS原子操作，lock函数将owner设置为当前线程，并且预测原来的值为空。unlock函数将owner设置为null， 并且预测值为当前线程。
     * 
     * 当有第二个线程调用lock操作时由于owner值不为空，导致循环一直被执行，直至第一个线程调用unlock函数将owner设置为null， 第二个线程才能进入临界区。
     * 
     * 对于自旋锁来说， 1、若有同一线程两调用lock() ，会导致第二次调用lock位置进行自旋，产生了死锁 说明这个锁并不是可重入的。（在lock函数内，应验证线程是否为已经获得锁的线程） 2、若1问题已经解决，当unlock（）第一次调用时，就已经将锁释放了。实际上不应释放锁。 （采用计数次进行统计）
     */
    private AtomicReference<Thread> sign = new AtomicReference<>();

    public void lock2() {
        Thread current = Thread.currentThread();
        while (!sign.compareAndSet(null, current)) {
        }
    }

    public void unlock2() {
        Thread current = Thread.currentThread();
        sign.compareAndSet(current, null);
    }

}
