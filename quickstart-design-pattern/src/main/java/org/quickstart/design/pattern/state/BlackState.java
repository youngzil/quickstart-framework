/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：BlackState.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.state;

import java.awt.Color;

/**
 * BlackState
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午10:39:50
 * @since 1.0
 */
public class BlackState extends State {

    public void handlepush(Context c) {
        System.out.println("变成红色");
        c.setState(new RedState());
    }

    public void handlepull(Context c) {
        System.out.println("变成红色");
        c.setState(new RedState());
    }

    public Color getcolor() {
        return (Color.black);
    }
}
