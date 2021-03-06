/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：ClientTest.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.iterator.extrinsic;

/**
 * ClientTest
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午9:04:48
 * @since 1.0
 */
public class ClientTest {

    public void operation() {
        Object[] objArray = {"One", "Two", "Three", "Four", "Five", "Six"};
        // 创建聚合对象
        Aggregate agg = new ConcreteAggregate(objArray);
        // 循环输出聚合对象中的值
        Iterator it = agg.createIterator();
        while (!it.isDone()) {
            System.out.println(it.currentItem());
            it.next();
        }
    }

    public static void main(String[] args) {

        ClientTest client = new ClientTest();
        client.operation();
    }

}
