/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：AbstractSubject.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.observer.example;

import java.util.Enumeration;
import java.util.Vector;

/**
 * AbstractSubject
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午12:00:17
 * @since 1.0
 */
public abstract class AbstractSubject implements Subject {

    private Vector<Observer> vector = new Vector<Observer>();

    @Override
    public void add(Observer observer) {
        vector.add(observer);
    }

    @Override
    public void del(Observer observer) {
        vector.remove(observer);
    }

    @Override
    public void notifyObservers() {
        Enumeration<Observer> enumo = vector.elements();
        while (enumo.hasMoreElements()) {
            enumo.nextElement().update();
        }
    }
}
