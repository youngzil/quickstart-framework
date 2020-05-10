/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：FactoryTest.java
 * 版本信息：
 * 日期：2018年1月25日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.factory.method;

import org.quickstart.design.pattern.factory.common.Sender;

/**
 * FactoryTest
 * 
 * @author：youngzil@163.com
 * @2018年1月25日 下午12:25:55
 * @since 1.0
 */
public class FactoryTest {

    public static void main(String[] args) {
        Provider provider = new SendMailFactory();
        Sender sender = provider.produce();
        sender.send();
    }

}
