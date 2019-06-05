/**
 * 项目名称：quickstart-javase 
 * 文件名：CLHLock.java
 * 版本信息：
 * 日期：2017年7月9日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.lock;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * CLHLock CLHLock 和MCSLock 则是两种类型相似的公平锁，采用链表的形式进行排序， CLHlock是不停的查询前驱变量， 导致不适合在NUMA 架构下使用（在这种结构下，每个线程分布在不同的物理内存区域）
 * 
 * 从代码上 看，CLH 要比 MCS 更简单， CLH 的队列是隐式的队列，没有真实的后继结点属性。 MCS 的队列是显式的队列，有真实的后继结点属性。 JUC ReentrantLock 默认内部使用的锁 即是 CLH锁（有很多改进的地方，将自旋锁换成了阻塞锁等等）。
 * 
 * @author：youngzil@163.com
 * @2017年7月9日 下午4:05:38
 * @version 1.0
 */
public class CLHLock {
    public static class CLHNode {
        private volatile boolean isLocked = true;
    }

    @SuppressWarnings("unused")
    private volatile CLHNode tail;
    private static final ThreadLocal<CLHNode> LOCAL = new ThreadLocal<CLHNode>();
    private static final AtomicReferenceFieldUpdater<CLHLock, CLHNode> UPDATER = AtomicReferenceFieldUpdater.newUpdater(CLHLock.class, CLHNode.class, "tail");

    public void lock() {
        CLHNode node = new CLHNode();
        LOCAL.set(node);
        CLHNode preNode = UPDATER.getAndSet(this, node);
        if (preNode != null) {
            while (preNode.isLocked) {
            }
            preNode = null;
            LOCAL.set(node);
        }
    }

    public void unlock() {
        CLHNode node = LOCAL.get();
        if (!UPDATER.compareAndSet(this, node, null)) {
            node.isLocked = false;
        }
        node = null;
    }
}
