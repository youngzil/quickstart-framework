/**
 * 项目名称：quickstart-javase 
 * 文件名：QueueBeHavior.java
 * 版本信息：
 * 日期：2017年12月8日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.collection.queue;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * QueueBeHavior 下面涉及Queue实现的大部分操作的基本示例 可以看到除了优先队列，Queue将精确地按照元素被置于Queue中的顺序产生它们
 * 
 * @author：youngzil@163.com
 * @2017年12月8日 下午5:45:06
 * @since 1.0
 */
public class QueueBeHavior {

    private static int count = 10;

    static <T> void test(Queue<T> queue, Generator<T> gen) {
        for (int i = 0; i < count; i++) {
            // T temp=gen.next();
            // System.out.println(temp);
            queue.offer(gen.next());
        }

        while (queue.peek() != null)
            System.out.print(queue.remove() + "  ");
        System.out.println();
    }

    static class Gen implements Generator<String> {
        String[] s = "one tow three four five six seven eight nine ten".split(" ");
        int i;

        public String next() {
            return s[i++];
        }
    }

    public static void main(String[] args) {
        test(new LinkedList<String>(), new Gen());
        test(new PriorityQueue<String>(), new Gen());
        test(new ArrayBlockingQueue<String>(count), new Gen());
        test(new ConcurrentLinkedQueue<String>(), new Gen());
        test(new LinkedBlockingQueue<String>(), new Gen());
        test(new PriorityBlockingQueue<String>(), new Gen());
    }

}
