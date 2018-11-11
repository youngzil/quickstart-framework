/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：ConcreteTarget.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.adapter.clazz;

/**
 * ConcreteTarget
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午7:45:05
 * @since 1.0
 */
// 具体目标类，只提供普通功能
public class ConcreteTarget implements Target {
    public void request() {
        System.out.println("普通类 具有 普通功能...");
    }
}
