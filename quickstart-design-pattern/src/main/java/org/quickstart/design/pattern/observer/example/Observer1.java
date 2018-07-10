/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Observer1.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.observer.example;

/**
 * Observer1
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月26日 下午11:59:20
 * @since 1.0
 */
public class Observer1 implements Observer {

    @Override
    public void update() {
        System.out.println("observer1 has received!");
    }
}
