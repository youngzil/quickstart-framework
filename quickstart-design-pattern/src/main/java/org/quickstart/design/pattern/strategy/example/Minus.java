/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Minus.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.strategy.example;

/**
 * Minus
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月26日 下午11:43:19
 * @since 1.0
 */
public class Minus extends AbstractCalculator implements ICalculator {

    @Override
    public int calculate(String exp) {
        int arrayInt[] = split(exp, "-");
        return arrayInt[0] - arrayInt[1];
    }

}
