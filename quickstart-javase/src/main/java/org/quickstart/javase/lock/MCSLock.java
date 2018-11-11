/**
 * 项目名称：quickstart-javase 
 * 文件名：MCSLock.java
 * 版本信息：
 * 日期：2017年7月9日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.lock;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * MCSLock CLHLock 和MCSLock 则是两种类型相似的公平锁，采用链表的形式进行排序， MCSLock则是对本地变量的节点进行循环。不存在CLHlock 的问题。
 * 
 * @author：youngzil@163.com
 * @2017年7月9日 下午4:06:29
 * @version 1.0
 */
public class MCSLock {
    public static class MCSNode {
        volatile MCSNode next;
        volatile boolean isLocked = true;
    }

    private static final ThreadLocal<MCSNode> NODE = new ThreadLocal<MCSNode>();
    @SuppressWarnings("unused")
    private volatile MCSNode queue;
    private static final AtomicReferenceFieldUpdater<MCSLock, MCSNode> UPDATER = AtomicReferenceFieldUpdater.newUpdater(MCSLock.class, MCSNode.class, "queue");

    public void lock() {
        MCSNode currentNode = new MCSNode();
        NODE.set(currentNode);
        MCSNode preNode = UPDATER.getAndSet(this, currentNode);
        if (preNode != null) {
            preNode.next = currentNode;
            while (currentNode.isLocked) {

            }
        }
    }

    public void unlock() {
        MCSNode currentNode = NODE.get();
        if (currentNode.next == null) {
            if (UPDATER.compareAndSet(this, currentNode, null)) {

            } else {
                while (currentNode.next == null) {
                }
            }
        } else {
            currentNode.next.isLocked = false;
            currentNode.next = null;
        }
    }
}
