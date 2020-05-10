/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Visitor.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.visitor;

/**
 * Visitor
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午11:09:11
 * @since 1.0
 */
public class Visitor implements IVisitor {

    public void visit(ConcreteElement1 el1) {
        el1.doSomething();
    }

    public void visit(ConcreteElement2 el2) {
        el2.doSomething();
    }
}
