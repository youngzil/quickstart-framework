/**
 * 项目名称：quickstart-javase 
 * 文件名：Book.java
 * 版本信息：
 * 日期：2017年12月21日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.xml.sax;

/**
 * Book
 * 
 * @author：youngzil@163.com
 * @2017年12月21日 下午8:50:39
 * @since 1.0
 */
public class Book {
    private String title;
    private String author;
    private int year;
    private double price;
    private String langguage;

    public Book() {
        // TODO Auto-generated constructor stub
    }

    public String getLangguage() {
        return langguage;
    }

    public void setLangguage(String langguage) {
        this.langguage = langguage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "title=" + title + ", author=" + author + ", year=" + year + ", price=" + price + ", langguage=" + langguage;
    }
}
