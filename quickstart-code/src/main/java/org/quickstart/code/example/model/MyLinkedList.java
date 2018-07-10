/**
 * 项目名称：quickstart-code 
 * 文件名：MyLinkedList.java
 * 版本信息：
 * 日期：2018年1月22日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.code.example.model;

/**
 * MyLinkedList
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月22日 下午12:48:35
 * @since 1.0
 */
public class MyLinkedList {

    public Node head;
    public Node current;

    public void add(int data) {

        // 如果头结点为空,为头结点
        if (head == null) {
            head = new Node(data);
            current = head;
        } else {
            current.next = new Node(data);
            current = current.next;
        }
    }

    // 打印链表
    public void print(Node node) {
        if (node == null) {
            return;
        }

        current = node;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
    }

    // 初始化链表,并且返回表头
    public Node init() {
        for (int i = 0; i < 10; i++) {
            this.add(i);
        }
        return head;
    }

    // 求链表长度
    public int get_length(Node head) {
        if (head == null) {
            return -1;
        }

        int length = 0;
        current = head;
        while (current != null) {
            length++;
            current = current.next;
        }

        return length;
    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        Node head = list.init();
        list.print(head);

        int length = list.get_length(head);
        System.out.println();
        System.out.println("The length of list is: " + length);
    }

}
