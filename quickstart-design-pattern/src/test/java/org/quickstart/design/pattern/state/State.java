/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：State.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.state;

import java.awt.Color;

/**
 * State
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午10:37:43
 * @since 1.0
 */
public abstract class State {
    public abstract void handlepush(Context c);

    public abstract void handlepull(Context c);

    public abstract Color getcolor();
}
