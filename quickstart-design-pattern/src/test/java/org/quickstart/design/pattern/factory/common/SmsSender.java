/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：SmsSender.java
 * 版本信息：
 * 日期：2018年1月25日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.factory.common;

/**
 * SmsSender
 * 
 * @author：youngzil@163.com
 * @2018年1月25日 下午12:14:26
 * @since 1.0
 */
public class SmsSender implements Sender {

    @Override
    public void send() {
        System.out.println("this is sms sender!");
    }
}
