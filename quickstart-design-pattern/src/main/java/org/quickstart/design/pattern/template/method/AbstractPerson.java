/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：AbstractPerson.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.template.method;

/**
 * AbstractPerson
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月26日 下午11:46:23
 * @since 1.0
 */
public abstract class AbstractPerson {
    // 抽象类定义整个流程骨架
    public void prepareGotoSchool() {
        dressUp();
        eatBreakfast();
        takeThings();
    }

    // 以下是不同子类根据自身特性完成的具体步骤
    protected abstract void dressUp();

    protected abstract void eatBreakfast();

    protected abstract void takeThings();
}
