/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：ClientTest.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.command;

/**
 * ClientTest
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午10:20:19
 * @since 1.0
 */
// 测试类Client
public class ClientTest {
    public static void main(String[] args) {
        // 命令接收者Receiver
        Tv myTv = new Tv();
        // 开机命令ConcreteCommond
        CommandOn on = new CommandOn(myTv);
        // 关机命令ConcreteCommond
        CommandOff off = new CommandOff(myTv);
        // 频道切换命令ConcreteCommond
        CommandChange channel = new CommandChange(myTv, 2);
        // 命令控制对象Invoker
        Control control = new Control(on, off, channel);

        // 开机
        control.turnOn();
        // 切换频道
        control.changeChannel();
        // 关机
        control.turnOff();
    }
}
