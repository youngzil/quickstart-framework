/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Tv.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.command;

/**
 * Tv
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午10:17:26
 * @since 1.0
 */
// 命令接收者Receiver
public class Tv {
    public int currentChannel = 0;

    public void turnOn() {
        System.out.println("The televisino is on.");
    }

    public void turnOff() {
        System.out.println("The television is off.");
    }

    public void changeChannel(int channel) {
        this.currentChannel = channel;
        System.out.println("Now TV channel is " + channel);
    }
}
