/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：MailFactory.java
 * 版本信息：
 * 日期：2018年1月25日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.factory.abstracts;

import org.quickstart.design.pattern.factory.common.MailReceiver;
import org.quickstart.design.pattern.factory.common.MailSender;
import org.quickstart.design.pattern.factory.common.Receiver;
import org.quickstart.design.pattern.factory.common.Sender;

/**
 * MailFactory
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月25日 下午2:31:29
 * @since 1.0
 */
public class MailFactory implements Provider {

    /* (non-Javadoc)
     * @see org.quickstart.design.pattern.factory.abstracts.Provider#produceSender()
     */
    @Override
    public Sender produceSender() {
        return new MailSender();
    }

    /* (non-Javadoc)
     * @see org.quickstart.design.pattern.factory.abstracts.Provider#produceReceiver()
     */
    @Override
    public Receiver produceReceiver() {
        return new MailReceiver();
    }

}
