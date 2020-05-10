/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：CommandOff.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.command;

/**
 * CommandOff
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午10:18:34
 * @since 1.0
 */
// 关机命令ConcreteCommand
public class CommandOff implements Command {
    private Tv myTv;

    public CommandOff(Tv tv) {
        myTv = tv;
    }

    public void execute() {
        myTv.turnOff();
    }
}
