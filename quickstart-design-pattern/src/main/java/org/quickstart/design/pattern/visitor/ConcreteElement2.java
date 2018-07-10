/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：ConcreteElement2.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.visitor;

/**
 * ConcreteElement2
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月27日 上午11:08:15
 * @since 1.0
 */
public class ConcreteElement2 extends Element {
    public void doSomething() {
        System.out.println("这是元素2");
    }

    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}
