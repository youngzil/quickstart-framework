/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Element.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.visitor;

/**
 * Element
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午11:03:32
 * @since 1.0
 */
abstract class Element {
    public abstract void accept(IVisitor visitor);

    public abstract void doSomething();
}
