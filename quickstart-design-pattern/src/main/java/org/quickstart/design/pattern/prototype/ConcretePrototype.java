/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：ConcretePrototype.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.prototype;

/**
 * ConcretePrototype
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午5:14:20
 * @since 1.0
 */
public class ConcretePrototype extends Prototype {
    public void show() {
        System.out.println("原型模式实现类=" + toString());
    }
}
