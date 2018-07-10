/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：SmsFactory.java
 * 版本信息：
 * 日期：2018年1月25日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.factory.abstracts;

import org.quickstart.design.pattern.factory.common.Receiver;
import org.quickstart.design.pattern.factory.common.Sender;
import org.quickstart.design.pattern.factory.common.SmsReceiver;
import org.quickstart.design.pattern.factory.common.SmsSender;

/**
 * SmsFactory
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月25日 下午2:31:13
 * @since 1.0
 */
public class SmsFactory implements Provider {

    /* (non-Javadoc)
     * @see org.quickstart.design.pattern.factory.abstracts.Provider#produceSender()
     */
    @Override
    public Sender produceSender() {
        return new SmsSender();
    }

    /* (non-Javadoc)
     * @see org.quickstart.design.pattern.factory.abstracts.Provider#produceReceiver()
     */
    @Override
    public Receiver produceReceiver() {
        return new SmsReceiver();
    }

}
