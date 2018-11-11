/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：ConcreteElement.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.visitor;

/**
 * ConcreteElement
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午11:04:11
 * @since 1.0
 */
public class ConcreteElement1 extends Element {
    public void doSomething() {
        System.out.println("这是元素1");
    }

    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}
