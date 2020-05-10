/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Observer2.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.observer.example;

/**
 * Observer2
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午11:59:29
 * @since 1.0
 */
public class Observer2 implements Observer {

    @Override
    public void update() {
        System.out.println("observer2 has received!");
    }

}
