/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：AdapterTest.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.adapter.clazz;

/**
 * AdapterTest
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午7:46:36
 * @since 1.0
 */
public class AdapterTest {

    // 测试类public class Client {
    public static void main(String[] args) {
        // 使用普通功能类
        Target concreteTarget = new ConcreteTarget();
        concreteTarget.request();

        // 使用特殊功能类，即适配类
        Target adapter = new Adapter();
        adapter.request();
    }
}
