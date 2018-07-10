/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：IVisitor.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.visitor;

/**
 * IVisitor
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月27日 上午11:08:53
 * @since 1.0
 */
public interface IVisitor {
    public void visit(ConcreteElement1 el1);

    public void visit(ConcreteElement2 el2);
}
