/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：AbstractCalculator.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.strategy.example;

/**
 * AbstractCalculator
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午11:42:43
 * @since 1.0
 */
public abstract class AbstractCalculator {

    public int[] split(String exp, String opt) {
        String array[] = exp.split(opt);
        int arrayInt[] = new int[2];
        arrayInt[0] = Integer.parseInt(array[0]);
        arrayInt[1] = Integer.parseInt(array[1]);
        return arrayInt;
    }
}
