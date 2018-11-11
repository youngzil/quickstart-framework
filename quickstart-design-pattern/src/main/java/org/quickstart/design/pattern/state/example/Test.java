/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Test.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.state.example;

/**
 * Test
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午10:58:52
 * @since 1.0
 */
public class Test {

    public static void main(String[] args) {

        State state = new State();
        Context context = new Context(state);

        // 设置第一种状态
        state.setValue("state1");
        context.method();

        // 设置第二种状态
        state.setValue("state2");
        context.method();
    }
}
