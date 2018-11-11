/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Lettuce.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.decorator.demo2;

/**
 * Lettuce
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午8:05:57
 * @since 1.0
 */
public class Lettuce extends Condiment {

    Humburger humburger;

    public Lettuce(Humburger humburger) {
        this.humburger = humburger;
    }

    @Override
    public String getName() {
        return humburger.getName() + " 加生菜";
    }

    @Override
    public double getPrice() {
        return humburger.getPrice() + 1.5;
    }

}
