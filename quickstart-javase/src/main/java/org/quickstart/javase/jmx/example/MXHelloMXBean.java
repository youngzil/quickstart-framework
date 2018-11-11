/**
 * 项目名称：quickstart-javase 
 * 文件名：HelloMXBean.java
 * 版本信息：
 * 日期：2018年6月11日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jmx.example;

/**
 * HelloMXBean 
 *  
 * @author：youngzil@163.com
 * @2018年6月11日 下午9:51:19 
 * @since 1.0
 */
public interface MXHelloMXBean {  
    public int getAge();  
    public String getName();  
    public String getEmail();  
    public void setEmail(String email);  
    public String sayHello();  
    public Book getBook();  
    public void addBook(Book book);  
}  
