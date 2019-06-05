/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：PrototypeClientTest.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.prototype;

/**
 * PrototypeClientTest
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午5:15:32
 * @since 1.0
 */
public class PrototypeClientTest {

    public static void main(String[] args) {
        ConcretePrototype cp = new ConcretePrototype();
        for (int i = 0; i < 10; i++) {
            ConcretePrototype clonecp = (ConcretePrototype) cp.clone();
            clonecp.show();
        }
    }

}
