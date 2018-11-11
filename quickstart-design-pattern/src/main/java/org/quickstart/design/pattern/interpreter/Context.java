/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Context.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.interpreter;

/**
 * Context
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 下午1:39:58
 * @since 1.0
 */
public class Context {

    private int num1;
    private int num2;

    public Context(int num1, int num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

}
