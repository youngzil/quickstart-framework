/**
 * 项目名称：quickstart-javase 
 * 文件名：Book.java
 * 版本信息：
 * 日期：2018年6月11日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jmx.example;

/**
 * Book
 * 
 * @author：youngzil@163.com
 * @2018年6月11日 下午9:50:43
 * @since 1.0
 */
public class Book {
    public String name;
    public double price;
    
    public Book() {
    }
    
    public Book(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
