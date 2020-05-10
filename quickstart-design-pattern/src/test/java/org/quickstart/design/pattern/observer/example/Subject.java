/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Subject.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.observer.example;

/**
 * Subject
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午11:59:56
 * @since 1.0
 */
public interface Subject {

    /*增加观察者*/
    public void add(Observer observer);

    /*删除观察者*/
    public void del(Observer observer);

    /*通知所有的观察者*/
    public void notifyObservers();

    /*自身的操作*/
    public void operation();
}
