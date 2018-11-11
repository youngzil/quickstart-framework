/**
 * 项目名称：quickstart-web 
 * 文件名：TestLinkedList.java
 * 版本信息：
 * 日期：2016年11月11日
 * Copyright asiainfo Corporation 2016
 * 版权所有 *
 */
package com.quickstart.test;

import java.util.LinkedList;
import java.util.List;

/**
 * TestLinkedList
 * 
 * @author：youngzil@163.com
 * @2016年11月11日 下午3:56:40
 * @version 1.0
 */
public class TestLinkedList {

    public static void main(String[] args) {
        List<Long> list = new LinkedList<Long>();
        list.add((long) 1);
        list.add((long) 2);
        list.add((long) 3);
        list.add((long) 4);
        list.add((long) 5);
        list.add((long) 6);

        list.remove(0);

        System.out.println(list.get(0));
    }

}
