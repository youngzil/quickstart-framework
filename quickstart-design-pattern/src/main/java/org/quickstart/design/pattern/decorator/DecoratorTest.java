/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：DecoratorTest.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.decorator;

/**
 * DecoratorTest
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午8:01:17
 * @since 1.0
 */
// 测试类，看一下你就会发现，跟java的I/O操作有多么相似
public class DecoratorTest {
    public static void main(String[] args) {
        Human person = new Person();
        Decorator decorator = new DecoratorTwo(new DecoratorFirst(new DecoratorZero(person)));
        decorator.wearClothes();
        decorator.walkToWhere();
    }
}
