/**
 * 项目名称：quickstart-javase 
 * 文件名：Deque.java
 * 版本信息：
 * 日期：2017年12月8日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

/**
 * Deque 双向队列的内部实现是一个双向链表，可以分别从头尾插入和删除节点。 通常使用一个first指向头 last指向尾。然后分别维护各种next和prev指针。通常情况要考虑边界条件，即当队列本身为空的时候插入新节点如何维护first和last的指向 删除节点的时候，若队列变为空又应该如何维护first和last指针。非常繁琐而且容易写错，不过使用的空间最少，
 * 
 * @author：youngzil@163.com
 * @2017年12月8日 下午6:28:45
 * @since 1.0
 */
public class Deque<Item> implements Iterable<Item> {
    private int n;
    private Node first;
    private Node last;

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    public Deque() {
        n = 0;
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void addFirst(Item item) {
        if (item == null)
            throw new NullPointerException("can't add null element!");
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.prev = null;
        // it is and empty queue..
        if (oldfirst == null) {
            last = first;
            first.next = null;
        } else {
            first.next = oldfirst;
            oldfirst.prev = first;
        }
        n++;
    }

    public void addLast(Item item) {
        if (item == null)
            throw new NullPointerException("can't add null element!");
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (oldlast == null) {
            first = last;
            last.prev = null;
        } else {
            last.prev = oldlast;
            oldlast.next = last;
        }
        n++;
    }

    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("Can't remove from empty deque");
        Item item = first.item;
        first = first.next;
        n--;
        if (n == 0)
            last = null;
        else
            first.prev = null;
        return item;
    }

    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");
        Item item = last.item;
        last = last.prev;
        n--;
        if (n == 0)
            first = null;
        else
            last.next = null;
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
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
        Deque<String> dq = new Deque<String>();
        dq.addFirst("A");
        dq.addFirst("B");
        dq.addFirst("C");
        dq.addLast("Q");
        dq.addLast("E");
        dq.addLast("D");
        for (String s : dq) {
            StdOut.println(s);
        }
        StdOut.println("Remove first" + dq.removeFirst());
        StdOut.println("Remove last" + dq.removeLast());
        for (String s : dq) {
            StdOut.println(s);
        }
    }
}
