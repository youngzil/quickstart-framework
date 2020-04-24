package org.quickstart.javase.jdk8.managedblocker;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2020/4/22 18:07
 */
public class Locker implements ForkJoinPool.ManagedBlocker {
    final ReentrantLock rtlock;
    boolean isLocked = false;
    Locker(ReentrantLock rtlock) {
        this.rtlock = rtlock;
    }
    public boolean block() {
        if (!isLocked){
            rtlock.lock();
        }
        return true;
    }
    public boolean isReleasable() {
        return isLocked || (isLocked = rtlock.tryLock());
    }
}
