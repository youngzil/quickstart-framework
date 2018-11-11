/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Chilli.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.decorator.demo2;

/**
 * Chilli
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午8:07:33
 * @since 1.0
 */
public class Chilli extends Condiment {

    Humburger humburger;

    public Chilli(Humburger humburger) {
        this.humburger = humburger;

    }

    @Override
    public String getName() {
        return humburger.getName() + " 加辣椒";
    }

    @Override
    public double getPrice() {
        return humburger.getPrice(); // 辣椒是免费的哦
    }

}
