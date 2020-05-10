/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：ClientTest.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.proxy;

/**
 * ClientTest
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午8:21:21
 * @since 1.0
 */
public class ClientTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        People people_1 = new People();
        people_1.setCash(60000);
        people_1.setUsername("jeck");

        People people_2 = new People();
        people_2.setCash(40000);
        people_2.setUsername("rose");

        People people_3 = new People();
        people_3.setCash(0);
        people_3.setUsername("tom");
        people_3.setVip("vip");

        ProxyClass proxy_buy = new ProxyClass();
        proxy_buy.setPeople(people_1);
        proxy_buy.buyMyCar();

        proxy_buy.setPeople(people_2);
        proxy_buy.buyMyCar();

        proxy_buy.setPeople(people_3);
        proxy_buy.buyMyCar();

    }

}
