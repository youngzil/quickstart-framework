/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：DecoratorFirst.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.decorator;

/**
 * DecoratorFirst
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午7:58:56
 * @since 1.0
 */

public class DecoratorFirst extends Decorator {

    public DecoratorFirst(Human human) {
        super(human);
    }

    public void goClothespress() {
        System.out.println("去衣柜找找看。。");
    }

    public void findPlaceOnMap() {
        System.out.println("在Map上找找。。");
    }

    @Override
    public void wearClothes() {
        // TODO Auto-generated method stub
        super.wearClothes();
        goClothespress();
    }

    @Override
    public void walkToWhere() {
        // TODO Auto-generated method stub
        super.walkToWhere();
        findPlaceOnMap();
    }
}
