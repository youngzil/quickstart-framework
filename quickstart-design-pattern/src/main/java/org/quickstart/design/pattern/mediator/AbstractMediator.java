/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：AbstractMediator.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.mediator;

/**
 * AbstractMediator
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 下午1:31:30
 * @since 1.0
 */
public abstract class AbstractMediator {
    protected AbstractColleague A;
    protected AbstractColleague B;

    public AbstractMediator(AbstractColleague a, AbstractColleague b) {
        A = a;
        B = b;
    }

    public abstract void AaffectB();

    public abstract void BaffectA();

}
