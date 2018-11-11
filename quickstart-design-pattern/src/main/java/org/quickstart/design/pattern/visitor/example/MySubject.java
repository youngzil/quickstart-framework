/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：MySubject.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.visitor.example;

/**
 * MySubject
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午11:18:55
 * @since 1.0
 */
public class MySubject implements Subject {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getSubject() {
        return "love";
    }
}
