/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Decorator.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.decorator;

/**
 * Decorator
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午7:55:55
 * @since 1.0
 */
// 定义装饰者
public abstract class Decorator implements Human {
    private Human human;

    public Decorator(Human human) {
        this.human = human;
    }

    public void wearClothes() {
        human.wearClothes();
    }

    public void walkToWhere() {
        human.walkToWhere();
    }
}
