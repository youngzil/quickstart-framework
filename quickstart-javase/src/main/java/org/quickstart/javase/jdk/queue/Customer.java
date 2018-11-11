/**
 * 项目名称：quickstart-javase 
 * 文件名：Customer.java
 * 版本信息：
 * 日期：2017年12月8日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.queue;

/**
 * Customer
 * 
 * @author：youngzil@163.com
 * @2017年12月8日 下午4:51:50
 * @since 1.0
 */
public class Customer {

    private int id;
    private String name;

    public Customer(int i, String n) {
        this.id = i;
        this.name = n;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
