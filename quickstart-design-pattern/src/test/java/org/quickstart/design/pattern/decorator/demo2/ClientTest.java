/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：ClientTest.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.decorator.demo2;

/**
 * ClientTest
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午8:08:11
 * @since 1.0
 */
public class ClientTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Humburger humburger = new ChickenBurger();
        System.out.println(humburger.getName() + "  价钱：" + humburger.getPrice());
        Lettuce lettuce = new Lettuce(humburger);
        System.out.println(lettuce.getName() + "  价钱：" + lettuce.getPrice());
        Chilli chilli = new Chilli(humburger);
        System.out.println(chilli.getName() + "  价钱：" + chilli.getPrice());
        Chilli chilli2 = new Chilli(lettuce);
        System.out.println(chilli2.getName() + "  价钱：" + chilli2.getPrice());
    }

}
