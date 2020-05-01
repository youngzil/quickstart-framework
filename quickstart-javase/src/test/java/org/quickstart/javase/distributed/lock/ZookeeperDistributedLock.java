/**
 * 项目名称：quickstart-javase 
 * 文件名：ZookeeperDistributedLock.java
 * 版本信息：
 * 日期：2018年6月13日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.distributed.lock;

import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import com.google.common.base.Joiner;

/*ZookeeperDistributedLock的构造函数创建zkclient，并且注册了监听事件，然后调用connectedSignal.await()挂起当前线程。当zkclient链接到服务器后，会给监听器发送SyncConnected事件，监听器判断当前链接已经建立了，则调用 connectedSignal.countDown();激活当前线程，然后创建root节点。
获取锁的方法lock，内部首先创建/root/lockName的顺序临时节点，然后获取/root下所有的孩子节点，并对子节点进行排序，然后判断自己是不是最小的编号，如果是直接返回true标示获取锁成功。否者看比自己小一个号的节点是否存在，存在则注册该节点的事件，然后挂起当前线程，等待比自己小一个数的节点释放锁后发送节点删除事件，事件里面激活当前线程。
释放锁的方法unlock比较简单，就是简单的删除获取锁时候创建的节点。*/

/**
 * ZookeeperDistributedLock
 * 
 * @author：youngzil@163.com
 * @2018年6月13日 上午9:46:53
 * @since 1.0
 */
public class ZookeeperDistributedLock {
    public final static Joiner j = Joiner.on("|").useForNull("");
    // zk客户端
    private ZooKeeper zk;
    // zk是一个目录结构，root为最外层目录
    private String root = "/locks";
    // 锁的名称
    private String lockName;
    // 当前线程创建的序列node
    private ThreadLocal<String> nodeId = new ThreadLocal<>();
    // 用来同步等待zkclient链接到了服务端
    private CountDownLatch connectedSignal = new CountDownLatch(1);
    private final static int sessionTimeout = 3000;
    private final static byte[] data = new byte[0];

    public ZookeeperDistributedLock(String config, String lockName) {
        this.lockName = lockName;
        try {
            zk = new ZooKeeper(config, sessionTimeout, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    // 建立连接
                    if (event.getState() == KeeperState.SyncConnected) {
                        connectedSignal.countDown();
                    }
                }
            });
            connectedSignal.await();
            Stat stat = zk.exists(root, false);
            if (null == stat) {
                // 创建根节点
                zk.create(root, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    class LockWatcher implements Watcher {
        private CountDownLatch latch = null;

        public LockWatcher(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void process(WatchedEvent event) {
            if (event.getType() == Event.EventType.NodeDeleted)
                latch.countDown();
        }
    }

    public void lock() {
        try {

            // 创建临时子节点
            String myNode = zk.create(root + "/" + lockName, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

            System.out.println(j.join(Thread.currentThread().getName() + myNode, "created"));

            // 取出所有子节点
            List<String> subNodes = zk.getChildren(root, false);

            TreeSet<String> sortedNodes = new TreeSet<>();

            for (String node : subNodes) {

                sortedNodes.add(root + "/" + node);

            }

            String smallNode = sortedNodes.first();

            String preNode = sortedNodes.lower(myNode);

            if (myNode.equals(smallNode)) {

                // 如果是最小的节点,则表示取得锁

                System.out.println(j.join(Thread.currentThread().getName(), myNode, "get lock"));

                this.nodeId.set(myNode);

                return;

            }

            CountDownLatch latch = new

            CountDownLatch(1);

            Stat stat = zk.exists(preNode, new

            LockWatcher(latch));
            // 同时注册监听。

            // 判断比自己小一个数的节点是否存在,如果不存在则无需等待锁,同时注册监听

            if (stat != null) {

                System.out.println(j.join(Thread.currentThread().getName(), myNode, " waiting for " + root + "/" + preNode + " released lock"));

                latch.await();
                // 等待，这里应该一直等待其他线程释放锁

                nodeId.set(myNode);

                latch = null;

            }

        } catch (Exception e) {

            throw new RuntimeException(e);

        }

    }

    public void unlock() {
        try {

            System.out.println(j.join(Thread.currentThread().getName(), nodeId.get(), "unlock "));

            if (null != nodeId) {

                zk.delete(nodeId.get(), -1);

            }

            nodeId.remove();

        } catch (InterruptedException e) {

            e.printStackTrace();

        } catch (KeeperException e) {

            e.printStackTrace();

        }

    }

}
