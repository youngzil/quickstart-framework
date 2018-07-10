/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Person.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.decorator;

/**
 * Person
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月26日 下午8:00:39
 * @since 1.0
 */
// 定义被装饰者，被装饰者初始状态有些自己的装饰
public class Person implements Human {

    @Override
    public void wearClothes() {
        // TODO Auto-generated method stub
        System.out.println("穿什么呢。。");
    }

    @Override
    public void walkToWhere() {
        // TODO Auto-generated method stub
        System.out.println("去哪里呢。。");
    }
}
