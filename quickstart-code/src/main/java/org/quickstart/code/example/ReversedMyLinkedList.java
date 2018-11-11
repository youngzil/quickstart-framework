/**
 * 项目名称：quickstart-code 
 * 文件名：ReversedMyLinkedList.java
 * 版本信息：
 * 日期：2018年1月22日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.code.example;

import org.quickstart.code.example.model.MyLinkedList;
import org.quickstart.code.example.model.Node;

/**
 * ReversedMyLinkedList
 * 
 * @author：youngzil@163.com
 * @2018年1月22日 下午12:50:13
 * @since 1.0
 */
public class ReversedMyLinkedList {

    /*逻辑描述如下：假设原链表有三个节点l,m,n，指向关系为l->m->n。
    当遍历到m节点时，此时有current=m，newHead=l。先用next指针保存n节点，然后将current.next指针指向newHead，
    此时m的next指针变为newHead即为l。接着，将newHead指针设为current，即此时newHead指针变为m。
    最后，将current指针变为先前保存的next指针即为n。
    等到下一次循环的时候，通过current.next = newHead这句将节点n的next指针设为m。
    通过整个过程，三个节点的指向关系就变为了n->m->l。*/

    public static Node reversed_linkedlist() {
        MyLinkedList list = new MyLinkedList();
        Node head = list.init();

        if (head == null || head.next == null) {
            return head;
        }

        // 使用三个节指针
        Node current = head;
        Node newHead = null;
        Node next = null;

        while (current != null) {
            // 先将当前节点的下个节点保存
            next = current.next;

            current.next = newHead; // 将原来的链表断链,将current的下一个结点指向新链表的头结点
            newHead = current; // 将current设为新表头

            current = next; // 将之前保存的next设为下一个节点
        }
        return newHead;
    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        Node head = reversed_linkedlist();
        System.out.println("After reversed, the list is: ");
        list.print(head);
    }
}
