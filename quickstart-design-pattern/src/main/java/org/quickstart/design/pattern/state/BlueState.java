/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：BlueState.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.state;

import java.awt.Color;

/**
 * BlueState
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午10:38:11
 * @since 1.0
 */
public class BlueState extends State {

    public void handlepush(Context c) {
        System.out.println("变成绿色");
        c.setState(new GreenState());
    }

    public void handlepull(Context c) {
        System.out.println("变成红色");
        c.setState(new RedState());
    }

    public Color getcolor() {
        return (Color.blue);
    }
}
