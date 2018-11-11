/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：State.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.state.example;

/**
 * State 状态类的核心类
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午10:57:51
 * @since 1.0
 */
public class State {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void method1() {
        System.out.println("execute the first opt!");
    }

    public void method2() {
        System.out.println("execute the second opt!");
    }
}
