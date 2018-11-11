/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Invoker.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.command.example;

/**
 * Invoker
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午10:24:48
 * @since 1.0
 */
public class Invoker {

    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void action() {
        command.exe();
    }
}
