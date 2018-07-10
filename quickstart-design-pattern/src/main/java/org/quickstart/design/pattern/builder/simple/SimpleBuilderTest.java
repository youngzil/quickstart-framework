/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：SimpleBuilderTest.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.builder.simple;

/**
 * SimpleBuilderTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月26日 下午5:08:21
 * @since 1.0
 */
public class SimpleBuilderTest {

    public static void main(String[] args) {
        ManBuilder builder = new ManBuilder();
        Man man = builder.builderMan();

        System.out.println("head=" + man.getHead());
        System.out.println("body=" + man.getBody());
        System.out.println("foot=" + man.getFoot());
    }

}
