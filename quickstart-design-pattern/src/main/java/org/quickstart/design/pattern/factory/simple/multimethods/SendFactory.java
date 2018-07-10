/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：SendFactory.java
 * 版本信息：
 * 日期：2018年1月25日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.factory.simple.multimethods;

import org.quickstart.design.pattern.factory.common.MailSender;
import org.quickstart.design.pattern.factory.common.Sender;
import org.quickstart.design.pattern.factory.common.SmsSender;

/**
 * SendFactory
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月25日 下午12:16:45
 * @since 1.0
 */
public class SendFactory {

    public Sender produceMail() {
        return new MailSender();
    }

    public Sender produceSms() {
        return new SmsSender();
    }

}
