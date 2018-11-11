/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：ProxyClass.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.proxy;

/**
 * ProxyClass
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午8:19:02
 * @since 1.0
 */
public class ProxyClass implements BuyCar {

    private People people;

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    @Override
    public void buyMyCar() {
        // TODO Auto-generated method stub

        if (people.getVip() == "vip") {
            people.buyMyCar();
            return;
        }
        if (people.getCash() >= 50000) {
            System.out.println(people.getUsername() + "买了新车，交易结束！");
        } else {
            System.out.println(people.getUsername() + "钱不够，不能买车，继续比赛！");
        }
    }
}
