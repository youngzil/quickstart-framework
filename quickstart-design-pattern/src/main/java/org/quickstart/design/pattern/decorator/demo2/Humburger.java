/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Humburger.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.decorator.demo2;

/**
 * Humburger
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午8:04:35
 * @since 1.0
 */
public abstract class Humburger {

    protected String name;

    public String getName() {
        return name;
    }

    public abstract double getPrice();

}
