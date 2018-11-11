/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：DecoratorTwo.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.decorator;

/**
 * DecoratorTwo
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午7:59:40
 * @since 1.0
 */
public class DecoratorTwo extends Decorator {

    public DecoratorTwo(Human human) {
        super(human);
    }

    public void findClothes() {
        System.out.println("找到一件D&G。。");
    }

    public void findTheTarget() {
        System.out.println("在Map上找到神秘花园和城堡。。");
    }

    @Override
    public void wearClothes() {
        // TODO Auto-generated method stub
        super.wearClothes();
        findClothes();
    }

    @Override
    public void walkToWhere() {
        // TODO Auto-generated method stub
        super.walkToWhere();
        findTheTarget();
    }
}
