/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Source.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.proxy.example;

/**
 * Source
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午8:23:57
 * @since 1.0
 */
public class Source implements Sourceable {

    @Override
    public void method() {
        System.out.println("the original method!");
    }
}
