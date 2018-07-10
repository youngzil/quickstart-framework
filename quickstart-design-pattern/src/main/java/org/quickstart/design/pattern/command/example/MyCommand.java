/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：MyCommand.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.command.example;

/**
 * MyCommand
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月27日 上午10:24:11
 * @since 1.0
 */
public class MyCommand implements Command {

    private Receiver receiver;

    public MyCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void exe() {
        receiver.action();
    }
}
