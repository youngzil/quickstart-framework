/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：CommandChange.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.command;

/**
 * CommandChange
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午10:19:05
 * @since 1.0
 */
// 频道切换命令ConcreteCommand
public class CommandChange implements Command {
    private Tv myTv;

    private int channel;

    public CommandChange(Tv tv, int channel) {
        myTv = tv;
        this.channel = channel;
    }

    public void execute() {
        myTv.changeChannel(channel);
    }
}
