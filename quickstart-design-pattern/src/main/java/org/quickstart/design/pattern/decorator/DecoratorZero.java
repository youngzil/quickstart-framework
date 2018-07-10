/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：DecoratorZero.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.decorator;

/**
 * DecoratorZero
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月26日 下午7:57:09
 * @since 1.0
 */
// 下面定义三种装饰，这是第一个，第二个第三个功能依次细化，即装饰者的功能越来越多
public class DecoratorZero extends Decorator {

    public DecoratorZero(Human human) {
        super(human);
    }

    public void goHome() {
        System.out.println("进房子。。");
    }

    public void findMap() {
        System.out.println("书房找找Map。。");
    }

    @Override
    public void wearClothes() {
        // TODO Auto-generated method stub
        super.wearClothes();
        goHome();
    }

    @Override
    public void walkToWhere() {
        // TODO Auto-generated method stub
        super.walkToWhere();
        findMap();
    }

}
