/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：CommandOn.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.command;

/**
 * CommandOn
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月27日 上午10:18:04
 * @since 1.0
 */
// 开机命令ConcreteCommand
public class CommandOn implements Command {
    private Tv myTv;

    public CommandOn(Tv tv) {
        myTv = tv;
    }

    public void execute() {
        myTv.turnOn();
    }
}
