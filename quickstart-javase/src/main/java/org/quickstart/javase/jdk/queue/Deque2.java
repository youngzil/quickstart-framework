/**
 * 项目名称：quickstart-javase 
 * 文件名：Deque2.java
 * 版本信息：
 * 日期：2017年12月8日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Deque2
 * 
 * @author：youngzil@163.com
 * @2017年12月8日 下午6:30:44
 * @since 1.0
 */
public class Deque2<Item> implements Iterable<Item> {
    private int n;
    private Node nil;

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    public Deque2() {
        n = 0;
        nil = new Node();
        nil.next = nil;
        nil.prev = nil;
    }

    public boolean isEmpty() {
        return nil.next == nil;
    }

    public int size() {
        return n;
    }

    public void addFirst(Item item) {
        if (item == null)
            throw new NullPointerException("can't add null element!");
        Node first = new Node();
        first.item = item;
        first.prev = nil;
        first.next = nil.next;
        nil.next.prev = first;
        nil.next = first;

        n++;
    }

    public void addLast(Item item) {
        if (item == null)
            throw new NullPointerException("can't add null element!");
        Node last = new Node();
        last.item = item;
        last.next = nil;
        last.prev = nil.prev;
        nil.prev.next = last;
        nil.prev = last;

        n++;
    }

    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("Can't remove from empty deque");
        Node del = nil.next;
        Item item = del.item;
        del.next.prev = nil;
        nil.next = del.next;
        n--;
        return item;
    }

    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");
        Node del = nil.prev;
        Item item = del.item;
        del.prev.next = nil;
        nil.prev = del.prev;
        n--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = nil.next;

        public boolean hasNext() {
            return current != nil;
        }

        public void remove() {
            throw new UnsupportedOperationException("remove is not supported!");
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Deque<Integer> dq = new Deque<Integer>();
        for (int i = 0; i < 10; i++) {
            int r = StdRandom.uniform(1, 65535);
            StdOut.println("Random add and remove node for " + r);
            for (int j = 0; j < r; j++) {
                if (StdRandom.uniform() > 0.5)
                    dq.addFirst(StdRandom.uniform(0, 10));
                else
                    dq.addLast(StdRandom.uniform(0, 10));
            }
            for (int n : dq)
                StdOut.println(n);

            for (int j = 0; j < r; j++) {
                if (StdRandom.uniform() > 0.5)
                    dq.removeFirst();
                else
                    dq.removeLast();
            }
        }
        StdOut.println("Done!");
    }

}
