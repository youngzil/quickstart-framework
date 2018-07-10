/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：FlyweightPattern.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.flyweight;

/**
 * FlyweightPattern
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月26日 下午11:21:11
 * @since 1.0
 */
public class FlyweightPattern {

    FlyweightFactory factory = new FlyweightFactory();
    Flyweight fly1;
    Flyweight fly2;
    Flyweight fly3;
    Flyweight fly4;
    Flyweight fly5;
    Flyweight fly6;

    /** */
    /** Creates a new instance of FlyweightPattern */
    public FlyweightPattern() {
        fly1 = factory.getFlyWeight("Google");
        fly2 = factory.getFlyWeight("Qutr");
        fly3 = factory.getFlyWeight("Google");
        fly4 = factory.getFlyWeight("Google");
        fly5 = factory.getFlyWeight("Google");
        fly6 = factory.getFlyWeight("Google");
    }

    public void showFlyweight() {
        fly1.operation();
        fly2.operation();
        fly3.operation();
        fly4.operation();
        fly5.operation();
        fly6.operation();
        int objSize = factory.getFlyweightSize();
        System.out.println("objSize = " + objSize);
    }

}
