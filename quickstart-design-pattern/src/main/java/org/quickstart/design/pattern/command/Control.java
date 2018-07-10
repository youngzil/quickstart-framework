/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Control.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.command;

/**
 * Control
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月27日 上午10:19:43
 * @since 1.0
 */
// 可以看作是遥控器Invoker
public class Control {
    private Command onCommand, offCommand, changeChannel;

    public Control(Command on, Command off, Command channel) {
        onCommand = on;
        offCommand = off;
        changeChannel = channel;
    }

    public void turnOn() {
        onCommand.execute();
    }

    public void turnOff() {
        offCommand.execute();
    }

    public void changeChannel() {
        changeChannel.execute();
    }
}
