/**
 * 项目名称：quickstart-code 
 * 文件名：Node.java
 * 版本信息：
 * 日期：2018年1月22日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.code.example.model;

/**
 * Node
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月22日 下午12:48:15
 * @since 1.0
 */
public class Node<T> {

    public T data;
    public Node next;

    public Node(T data) {
        this.data = data;
    }
}
