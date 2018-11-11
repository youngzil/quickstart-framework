/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：ObserverTest.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.observer.example;

/**
 * ObserverTest
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午12:01:25
 * @since 1.0
 */
public class ObserverTest {

    public static void main(String[] args) {
        Subject sub = new MySubject();
        sub.add(new Observer1());
        sub.add(new Observer2());

        sub.operation();

    }

}
