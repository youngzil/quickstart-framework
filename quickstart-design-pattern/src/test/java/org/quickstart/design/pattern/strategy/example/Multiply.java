/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Multiply.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.strategy.example;

/**
 * Multiply
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午11:43:40
 * @since 1.0
 */
public class Multiply extends AbstractCalculator implements ICalculator {

    @Override
    public int calculate(String exp) {
        int arrayInt[] = split(exp, "\\*");
        return arrayInt[0] * arrayInt[1];
    }
}
