/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Mediator.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.mediator;

/**
 * Mediator
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 下午1:32:24
 * @since 1.0
 */
public class Mediator extends AbstractMediator {

    public Mediator(AbstractColleague a, AbstractColleague b) {
        super(a, b);
    }

    // 处理A对B的影响
    public void AaffectB() {
        int number = A.getNumber();
        B.setNumber(number * 100);
    }

    // 处理B对A的影响
    public void BaffectA() {
        int number = B.getNumber();
        A.setNumber(number / 100);
    }
}
