/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：AbstractColleague.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.mediator;

/**
 * AbstractColleague
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 下午1:30:28
 * @since 1.0
 */
public abstract class AbstractColleague {
    protected int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    // 注意这里的参数不再是同事类，而是一个中介者
    public abstract void setNumber(int number, AbstractMediator am);
}
