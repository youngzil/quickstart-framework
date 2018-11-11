/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Facade.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.facade;

/**
 * Facade
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午8:37:02
 * @since 1.0
 */
public class Facade {
    // 示意方法，满足客户端需要的功能
    public void test() {
        ModuleA a = new ModuleA();
        a.testA();
        ModuleB b = new ModuleB();
        b.testB();
        ModuleC c = new ModuleC();
        c.testC();
    }
}
