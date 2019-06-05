/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：RedState.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.state;

import java.awt.Color;

/**
 * RedState
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午10:40:28
 * @since 1.0
 */
public class RedState extends State {

    public void handlepush(Context c) {
        System.out.println("变成蓝色");
        c.setState(new BlueState());
    }

    public void handlepull(Context c) {
        System.out.println("变成黑色");
        c.setState(new BlackState());
    }

    public Color getcolor() {
        return (Color.red);
    }
}
