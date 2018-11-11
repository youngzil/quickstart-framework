/**
 * 项目名称：quickstart-disruptor 
 * 文件名：RingBuffer2.java
 * 版本信息：
 * 日期：2018年9月25日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.disruptor.ring.buffer;

import java.util.ArrayList;
import java.util.List;

/**
 * RingBuffer2 
 *  
 * @author：youngzil@163.com
 * @2018年9月25日 下午8:42:25 
 * @since 1.0
 */

/**
 * 环形缓冲区<br/>
 * 一. 写数据:<br/>
 *     1. push: 当数据已写满时返回false，否则可以正常写入返回true<br/>
 *     2. pushNoCaseFull: 不管缓冲区是否已写满或当前位置数据是否已读取过，都会写入，不关心读/写指针位置<br/>
 * 二. 读数据:<br/>
 *     1. pull: 当缓冲区已读空或还未写入过数据时，返回null<br/>
 *            另外一个重载方法可以对指定位置进行读取，此方法不会影响读指针位置<br/>
 *     2. pullNoCaseEmpty: 不管当前位置的数据有没有读过，即可以重复读，不关心读/写指针位置,不会影响读指针位置<br/>
 *            另外一个重载方法可以对指定位置进行读取，此方法不会影响读指针位置<br/>
 *     3. pullBack: 反相读取数据，返回数据情况与pull相同<br/>
 *     4. pullBackNoCaseEmpty: 反相读取数据，返回数据情况与pullNoCaseEmpty相同<br/>
 * 三. 是否已写满:<br/>
 *     isFull: 如果写之前关心是否已满，调用此方法<br/>
 * 四. 是否已读空:<br/>
 *     isEmpty: 读数据之前关心是否已读空，调用此方法<br/>
 */
public class RingBuffer2<T> {
    private final static byte INVALID = ~0;// 无效数据
    private final static byte WRITED = 0;// 数据写过
    private final static byte READED = 1;// 数据读过
 
    private int mCapacity = 0;
    private List<Node> mDataBuf = null;
    private int mReader = 0;
    private int mWriter = 0;
 
 
    private class Node {
       public T object = null;
       public byte flag = INVALID;
 
       public Node(T object, byte flag) {
           this.object = object;
           this.flag = flag;
       }
    }
 
 
    public RingBuffer2(int capacity) {
       mCapacity = capacity;
       mDataBuf = new ArrayList<Node>(capacity);
    }
 
    /**
     * 当数据已写满时返回false，否则可以正常写入返回true<br/>
     * @param object 被压入的数据
     * @return true: 写入成功, false:缓冲区已满
     */
    public synchronized boolean push(T object) {
       int size = mDataBuf.size();
 
       if (mWriter < mCapacity) {
       }
       else if (mWriter >= mCapacity) {// 写到尾部
           mWriter = 0;
       }
 
       if (mCapacity > size) {// 无效数据(未写过数据)
           Node node = new Node(object, WRITED);
           mDataBuf.add(mWriter++, node);
       }
       else {
           try {
              Node oldNode = mDataBuf.get(mWriter);
              if (null != oldNode && WRITED == oldNode.flag) {// 写已满
                  return false;
              }
 
              Node node = new Node(object, WRITED);
              mDataBuf.set(mWriter++, node);
           } catch (Exception e) {
           }
       }
 
       return true;
    }
 
    /**
     * 不管缓冲区是否已写满或当前位置数据是否已读取过，都会写入，不关心读/写指针位置，也不影响读写指针<br/>
     * @param object 被压入的数据
     */
    public synchronized void pushNoCaseFull(T object) {
       int size = mDataBuf.size();
 
       if (mWriter < mCapacity) {
       }
       else if (mWriter >= mCapacity) {// 写到尾部
 
           mWriter = 0;
       }
 
       Node node = new Node(object, WRITED);
       if (mCapacity > size)// 无效数据(未写过数据)
           mDataBuf.add(mWriter++, node);
       else
           mDataBuf.set(mWriter++, node);
    }
 
    /**
     * 当缓冲区已读空或还未写入过数据时，返回null<br/>
     * @return 被拉取到的数据, 如果已读空或数据无效返回null
     */
    public synchronized T pull() {
       if (mReader < mCapacity) {
           if (mReader < 0) {
              mReader = 0;
           }
       }
       else if (mReader >= mCapacity) {// 写到尾部
           mReader = 0;
       }
 
       try {
           Node node = mDataBuf.get(mReader);
           if (null != node && WRITED == node.flag) {
              node.flag = READED;
              mDataBuf.set(mReader, node);
 
              mReader++;
              return node.object;
           }
           else {// 已读空
           }
       } catch (Exception e) {// No data
       }
 
       return null;
    }
 
    /**
     * 对指定位置进行读取，此方法不会影响读指针位置<br/>
     * @return 被拉取到的数据, 如果已读空或数据无效返回null
     */
    public synchronized T pull(int location) {
       if (location < mCapacity) {
           if (location < 0) {
              location = 0;
           }
       }
       else if (location >= mCapacity) {// 写到尾部
           location = 0;
       }
 
       try {
           Node node = mDataBuf.get(location);
           if (null != node && WRITED == node.flag) {
//            node.flag = READED;
//            mDataBuf.set(location, node);
 
              return node.object;
           }
           else {// 已读空
           }
       } catch (Exception e) {// No data
       }
 
       return null;
    }
 
    /**
     * 不管当前位置的数据有没有读过，即可以重复读，不关心读/写指针位置，也不影响读写指针<br/>
     * @return 被拉取到的数据, 如果数据无效返回null
     */
    public synchronized T pullNoCaseEmpty() {
       if (mReader < mCapacity) {
           if (mReader < 0) {
              mReader = 0;
           }
       }
       else if (mReader >= mCapacity) {// 读到尾部
           mReader = 0;
       }
 
       try {
           Node node = mDataBuf.get(mReader);
           if (null != node && INVALID != node.flag) {// 只要数据有效
//            node.flag = READED;
//            mDataBuf.set(mReader, node);
 
              mReader++;
              return node.object;
           }
           else {// 数据无效
           }
       } catch (Exception e) {
       }
 
       return null;
    }
 
    /**
     * 对指定位置进行读取，此方法不会影响读指针位置<br/>
     * @return 被拉取到的数据, 如果数据无效返回null
     */
    public synchronized T pullNoCaseEmpty(int location) {
       if (location < mCapacity) {
           if (location < 0) {
              location = 0;
           }
       }
       else if (location >= mCapacity) {// 读到尾部
           location = 0;
       }
 
       try {
           Node node = mDataBuf.get(location);
           if (null != node && INVALID != node.flag) {// 只要数据有效
//            node.flag = READED;
//            mDataBuf.set(location, node);
              return node.object;
           }
           else {// 数据无效
           }
       } catch (Exception e) {
       }
 
       return null;
    }
 
    /**
     * 反相读取数据，当缓冲区已读空或还未写入过数据时，返回null<br/>
     * @return 被拉取到的数据, 如果已读空或数据无效返回null
     */
    public synchronized T pullBack() {
       if (mReader < mCapacity) {
           if (mReader < 0) {
              mReader = mCapacity - 1;
           }
       }
       else if (mReader >= mCapacity) {// 读到尾部
           mReader = 0;
       }
 
       try {
           Node node = mDataBuf.get(mReader);
           if (null != node && WRITED == node.flag) {
              node.flag = READED;
              mDataBuf.set(mReader, node);
 
              mReader--;
              return node.object;
           }
           else {// 已读空
           }
       } catch (Exception e) {// 可能还未写过数据
       }
 
       return null;
    }
 
    /**
     * 反相读取数据，不管当前位置的数据有没有读过，即可以重复读，不关心读/写指针位置，也不影响读写指针<br/>
     * @return 被拉取到的数据, 如果数据无效返回null
     */
    public synchronized T pullBackNoCaseEmpty() {
       if (mReader < mCapacity) {
           if (mReader < 0) {
              mReader = mCapacity - 1;
           }
       }
       else if (mReader >= mCapacity) {// 读到尾部
           mReader = 0;
       }
 
       try {
           Node node = mDataBuf.get(mReader);
           if (null != node && INVALID != node.flag) {
//            node.flag = READED;
//            mDataBuf.set(mReader, node);
 
              mReader--;
              return node.object;
           }
           else {// 无效数据（未写过）
           }
       } catch (Exception e) {// 可能还未写过数据
       }
 
       return null;
    }
 
    /**
     * 缓冲区是否已满(对写操作而言)
     */
    public synchronized boolean isFull() {
       try {
           Node n = mDataBuf.get(mWriter);
           if (null != n && WRITED == n.flag) {
              return true;
           }
       } catch (Exception e) {
       }
       return false;
    }
 
    /**
     * 缓冲区是否为空(对读操作而言)
     */
    public synchronized boolean isEmpty() {
       if (mReader < mCapacity) {
       }
       else if (mReader >= mCapacity) {// 读到尾部
           mReader = 0;
       }
 
       try {
           Node node = mDataBuf.get(mReader);
           if (null != node && WRITED == node.flag) {
           }
           else {
              return true;
           }
       } catch (Exception e) {// 可能未写入过数据
           return true;
       }
 
       return false;
    }
 
    /**
     * 环形缓存容量
     */
    public synchronized int capacity() {
       return mCapacity;
    }
 
    /**
     * 环形缓存size, size与capacity不一定相同，当还未填充满时size<capacity，反之size=capacity
     */
    public synchronized int size() {
       return mDataBuf.size();
    }
}

