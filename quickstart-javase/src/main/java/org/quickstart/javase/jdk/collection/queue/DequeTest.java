/**
 * 项目名称：quickstart-javase 
 * 文件名：DequeTest.java
 * 版本信息：
 * 日期：2017年12月8日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.collection.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * http://blog.csdn.net/top_code/article/details/8650729 http://shenwang.blog.ustc.edu.cn/java-%E5%8F%8C%E5%90%91%E9%98%9F%E5%88%97-arraydeque/ DequeTest 双向队列就是一个队列，但是你可以在任何一端添加或移除元素，
 * LinkedList无法实现这样的接口，但可以使用组合来创建一个Deque类，
 * 
 * @author：youngzil@163.com
 * @2017年12月8日 下午4:48:30
 * @since 1.0
 */
public class DequeTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Deque<Integer> mDeque = new ArrayDeque<Integer>();

        for (int i = 0; i < 5; i++) {
            mDeque.offer(i);
        }

        System.out.println(mDeque.peek());

        System.out.println("***********集合方式遍历**********");

        // 集合方式遍历，元素不会被移除
        for (Integer x : mDeque) {
            System.out.println(x);
        }

        System.out.println("**********遍历队列*************");

        // 队列方式遍历，元素逐个被移除
        while (mDeque.peek() != null) {
            System.out.println(mDeque.poll());
        }

        System.out.println("***********进栈操作************");

        mDeque.push(10);
        mDeque.push(15);
        mDeque.push(24);
        print(mDeque);

        System.out.println("*********出栈操作*************");

        System.out.println(mDeque.pop());

        mDeque.clear();
        System.out.println("size=" + mDeque.size());

        // addLast(e) 向队尾插入元素，失败则抛出异常
        // offerLast(e) 向队尾插入元素，失败则返回false
        // removeFirst() 获取并删除队首元素，失败则抛出异常
        // pollFirst() 获取并删除队首元素，失败则返回null
        // getFirst() 获取但不删除队首元素，失败则抛出异常
        // peekFirst() 获取但不删除队首元素，失败则返回null
        // addFirst(e) 向栈顶插入元素，失败则抛出异常
        // offerFirst(e) 向栈顶插入元素，失败则返回false
        // removeFirst() 获取并删除栈顶元素，失败则抛出异常
        // pollFirst() 获取并删除栈顶元素，失败则返回null
        // peekFirst() 获取但不删除栈顶元素，失败则抛出异常
        // peekFirst() 获取但不删除栈顶元素，失败则返回null

        // addFirst(E e)的作用是在Deque的首端插入元素，也就是在head的前面插入元素，在空间足够且下标没有越界的情况下，只需要将elements[–head] = e即可。
        // addLast(E e)的作用是在Deque的尾端插入元素，也就是在tail的位置插入元素，由于tail总是指向下一个可以插入的空位，因此只需要elements[tail] = e;即可。插入完成后再检查空间，如果空间已经用光，则调用doubleCapacity()进行扩容。
        // pollFirst()的作用是删除并返回Deque首端元素，也即是head位置处的元素。如果容器不空，只需要直接返回elements[head]即可，当然还需要处理下标的问题。由于ArrayDeque中不允许放入null，当elements[head] == null时，意味着容器为空。
        // pollLast()的作用是删除并返回Deque尾端元素，也即是tail位置前面的那个元素。
        // peekFirst()的作用是返回但不删除Deque首端元素，也即是head位置处的元素，直接返回elements[head]即可。
        // peekLast()的作用是返回但不删除Deque尾端元素，也即是tail位置前面的那个元素。

        mDeque.addFirst(1);
        mDeque.addLast(4);

    }

    public static void print(Deque<Integer> queue) {
        // 集合方式遍历，元素不会被移除
        for (Integer x : queue) {
            System.out.println(x);
        }
    }

    /* Queue Method    Equivalent Deque Method 说明
    add(e)  addLast(e)  向队尾插入元素，失败则抛出异常
    offer(e)    offerLast(e)    向队尾插入元素，失败则返回false
    remove()    removeFirst()   获取并删除队首元素，失败则抛出异常
    poll()  pollFirst() 获取并删除队首元素，失败则返回null
    element()   getFirst()  获取但不删除队首元素，失败则抛出异常
    peek()  peekFirst() 获取但不删除队首元素，失败则返回null*/

}
