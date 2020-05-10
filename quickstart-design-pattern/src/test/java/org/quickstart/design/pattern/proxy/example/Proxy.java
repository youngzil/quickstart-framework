/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Proxy.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.proxy.example;

/**
 * Proxy
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午8:24:14
 * @since 1.0
 */
public class Proxy implements Sourceable {

    private Source source;

    public Proxy() {
        super();
        this.source = new Source();
    }

    @Override
    public void method() {
        before();
        source.method();
        atfer();
    }

    private void atfer() {
        System.out.println("after proxy!");
    }

    private void before() {
        System.out.println("before proxy!");
    }
}
