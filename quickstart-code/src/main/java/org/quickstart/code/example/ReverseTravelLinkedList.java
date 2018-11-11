/**
 * 项目名称：quickstart-code 
 * 文件名：ReverseTravelLinkedList.java
 * 版本信息：
 * 日期：2018年1月22日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.code.example;

import java.util.Stack;

import org.quickstart.code.example.model.MyLinkedList;
import org.quickstart.code.example.model.Node;

/**
 * ReverseTravelLinkedList
 * 
 * @author：youngzil@163.com
 * @2018年1月22日 下午12:45:33
 * @since 1.0
 */
public class ReverseTravelLinkedList {

    // 递归
    // 递归的好处是代码量少，简介明了。但是所有递归都有的毛病就是当递归次数太多的时候，会导致方法调用的层级很深，最终导致内存不够栈溢出。所以如果从代码的稳定性考虑的话，非递归的方式显然要更安全一些。
    public static void rev_tra_recur(Node head) {
        if (head == null) {
            return;
        }
        rev_tra_recur(head.next);
        System.out.print(head.data + " ");
    }

    // 非递归,用栈模拟
    // 对于这种倒序的要求，我们很自然想到”后进先出”，很自然就想到用栈这种数据结构去模拟整个倒序遍历的过程
    public static void rev_tra_no_recur(Node head) {
        if (head == null) {
            return;
        }

        Stack<Node> stack = new Stack<Node>();
        Node current = head;

        while (current != null) {
            stack.push(current);
            current = current.next;
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop().data + " ");
        }

    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        Node head = list.init();

        rev_tra_recur(head);
        System.out.println();
        rev_tra_no_recur(head);
    }

}
