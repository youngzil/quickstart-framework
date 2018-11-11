/**
 * 项目名称：quickstart-zookeeper 
 * 文件名：NamedThreadFactory.java
 * 版本信息：
 * 日期：2017年7月2日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.example;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * NamedThreadFactory
 * 
 * @author：youngzil@163.com
 * @2017年7月2日 下午9:09:23
 * @version 1.0
 */
public class NamedThreadFactory implements ThreadFactory {
    private static final AtomicInteger POOL_SEQ = new AtomicInteger(1);

    private final AtomicInteger mThreadNum = new AtomicInteger(1);

    private final String mPrefix;

    private final boolean mDaemo;

    private final ThreadGroup mGroup;

    public NamedThreadFactory() {
        this("pool-" + POOL_SEQ.getAndIncrement(), false);
    }

    public NamedThreadFactory(String prefix) {
        this(prefix, false);
    }

    public NamedThreadFactory(String prefix, boolean daemo) {
        mPrefix = prefix + "-thread-";
        mDaemo = daemo;
        SecurityManager s = System.getSecurityManager();
        mGroup = (s == null) ? Thread.currentThread().getThreadGroup() : s.getThreadGroup();
    }

    public Thread newThread(Runnable runnable) {
        String name = mPrefix + mThreadNum.getAndIncrement();
        Thread ret = new Thread(mGroup, runnable, name, 0);
        ret.setDaemon(mDaemo);
        return ret;
    }

    public ThreadGroup getThreadGroup() {
        return mGroup;
    }
}
