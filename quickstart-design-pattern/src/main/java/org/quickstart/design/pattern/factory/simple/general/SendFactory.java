/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：SendFactory.java
 * 版本信息：
 * 日期：2018年1月25日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.factory.simple.general;

import org.quickstart.design.pattern.factory.common.MailSender;
import org.quickstart.design.pattern.factory.common.Sender;
import org.quickstart.design.pattern.factory.common.SmsSender;

/**
 * SendFactory
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月25日 下午12:15:02
 * @since 1.0
 */
public class SendFactory {

    public Sender produce(String type) {
        if ("mail".equals(type)) {
            return new MailSender();
        } else if ("sms".equals(type)) {
            return new SmsSender();
        } else {
            System.out.println("请输入正确的类型!");
            return null;
        }
    }
}
