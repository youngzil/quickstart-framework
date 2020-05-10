/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Plus.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.template.method.example;

/**
 * Plus
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午11:57:03
 * @since 1.0
 */
public class Plus extends AbstractCalculator {

    @Override
    public int calculate(int num1, int num2) {
        return num1 + num2;
    }
}
