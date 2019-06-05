/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：SendMailFactory.java
 * 版本信息：
 * 日期：2018年1月25日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.factory.method;

import org.quickstart.design.pattern.factory.common.MailSender;
import org.quickstart.design.pattern.factory.common.Sender;

/**
 * SendMailFactory
 * 
 * @author：youngzil@163.com
 * @2018年1月25日 下午12:24:09
 * @since 1.0
 */
public class SendMailFactory implements Provider {

    @Override
    public Sender produce() {
        return new MailSender();
    }
}
