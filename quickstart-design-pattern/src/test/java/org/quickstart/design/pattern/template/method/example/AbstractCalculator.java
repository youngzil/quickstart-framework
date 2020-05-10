/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：AbstractCalculator.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.template.method.example;

/**
 * AbstractCalculator
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午11:56:30
 * @since 1.0
 */
public abstract class AbstractCalculator {

    /*主方法，实现对本类其它方法的调用*/
    public final int calculate(String exp, String opt) {
        int array[] = split(exp, opt);
        return calculate(array[0], array[1]);
    }

    /*被子类重写的方法*/
    abstract public int calculate(int num1, int num2);

    public int[] split(String exp, String opt) {
        String array[] = exp.split(opt);
        int arrayInt[] = new int[2];
        arrayInt[0] = Integer.parseInt(array[0]);
        arrayInt[1] = Integer.parseInt(array[1]);
        return arrayInt;
    }
}
