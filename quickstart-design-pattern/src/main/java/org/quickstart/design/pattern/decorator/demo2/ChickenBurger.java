/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：ChickenBurger.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.decorator.demo2;

/**
 * ChickenBurger
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午8:04:59
 * @since 1.0
 */
public class ChickenBurger extends Humburger {

    public ChickenBurger() {
        name = "鸡腿堡";
    }

    @Override
    public double getPrice() {
        return 10;
    }

}
