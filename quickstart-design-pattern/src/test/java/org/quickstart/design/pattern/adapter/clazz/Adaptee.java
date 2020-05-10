/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Adaptee.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.adapter.clazz;

/**
 * Adaptee
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午7:44:01
 * @since 1.0
 */
// 已存在的、具有特殊功能、但不符合我们既有的标准接口的类
public class Adaptee {

    volatile String string = "dd";

    public void specificRequest() {

        System.out.println("被适配类具有 特殊功能...");
    }
}
