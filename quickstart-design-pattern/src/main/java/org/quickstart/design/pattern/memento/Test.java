/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Test.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.memento;

/**
 * Test
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午10:28:58
 * @since 1.0
 */
public class Test {

    public static void main(String[] args) {

        // 创建原始类
        Original origi = new Original("egg");

        // 创建备忘录
        Storage storage = new Storage(origi.createMemento());

        // 修改原始类的状态
        System.out.println("初始化状态为：" + origi.getValue());
        origi.setValue("niu");
        System.out.println("修改后的状态为：" + origi.getValue());

        // 回复原始类的状态
        origi.restoreMemento(storage.getMemento());
        System.out.println("恢复后的状态为：" + origi.getValue());
    }
}
