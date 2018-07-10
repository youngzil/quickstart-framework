/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Context.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.state.example;

/**
 * Context 状态模式的切换类
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月27日 上午10:58:25
 * @since 1.0
 */
public class Context {

    private State state;

    public Context(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void method() {
        if (state.getValue().equals("state1")) {
            state.method1();
        } else if (state.getValue().equals("state2")) {
            state.method2();
        }
    }
}
