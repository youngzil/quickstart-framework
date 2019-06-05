/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Minus.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.interpreter;

/**
 * Minus
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 下午1:39:32
 * @since 1.0
 */
public class Minus implements Expression {

    @Override
    public int interpret(Context context) {
        return context.getNum1() - context.getNum2();
    }
}
