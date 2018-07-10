/**
 * 项目名称：quickstart-javase 
 * 文件名：MXHello.java
 * 版本信息：
 * 日期：2018年6月11日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jmx.example;

/**
 * MXHello
 * 
 * @author：yangzl@asiainfo.com
 * @2018年6月11日 下午9:56:08
 * @since 1.0
 */
public class MXHello implements MXHelloMXBean {
    private final String name;
    private final int age;
    private String email;
    private Book book;

    public MXHello(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String sayHello() {
        return "Hello, I'm " + name + ".";
    }

    @Override
    public Book getBook() {
        return this.book;
    }

    @Override
    public void addBook(Book book) {
        this.book = book;
    }
}
