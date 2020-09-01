package org.quickstart.javase.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/31 22:17
 * @version v1.0
 */
public class ReentrantReadWriteLockCache {

    // 定义一个非线程安全的 HashMap 用于缓存对象
    static Map<String, Object> map = new HashMap<>();
    // 创建读写锁对象
    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    // 构建读锁
    static Lock rl = readWriteLock.readLock();
    // 构建写锁
    static Lock wl = readWriteLock.writeLock();

    public static final Object get(String key) {
        rl.lock();
        try {
            return map.get(key);
        } finally {
            rl.unlock();
        }
    }

    public static final Object put(String key, Object value) {
        wl.lock();
        try {
            return map.put(key, value);
        } finally {
            wl.unlock();
        }
    }

}
