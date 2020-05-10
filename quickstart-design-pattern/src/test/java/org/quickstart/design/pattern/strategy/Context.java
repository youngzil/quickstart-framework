/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Context.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.strategy;

/**
 * Context
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午11:40:01
 * @since 1.0
 */
public class Context {
    private Strategy strategy;

    // 构造函数，要你使用哪个妙计
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void operate() {
        this.strategy.operate();
    }
}
