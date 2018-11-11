/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Plus.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.interpreter;

/**
 * Plus
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 下午1:39:11
 * @since 1.0
 */
public class Plus implements Expression {

    @Override
    public int interpret(Context context) {
        return context.getNum1() + context.getNum2();
    }
}
