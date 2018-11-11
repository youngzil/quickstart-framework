/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：ProxyTest.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.proxy.example;

/**
 * ProxyTest
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午8:24:50
 * @since 1.0
 */
public class ProxyTest {

    public static void main(String[] args) {
        Sourceable source = new Proxy();
        source.method();
    }

}
