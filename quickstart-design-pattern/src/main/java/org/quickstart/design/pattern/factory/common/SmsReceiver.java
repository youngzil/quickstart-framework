/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：SmsReceiver.java
 * 版本信息：
 * 日期：2018年1月25日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.factory.common;

/**
 * SmsReceiver
 * 
 * @author：youngzil@163.com
 * @2018年1月25日 下午2:28:18
 * @since 1.0
 */
public class SmsReceiver implements Receiver {

    /* (non-Javadoc)
     * @see org.quickstart.design.pattern.factory.common.Receiver#receive()
     */
    @Override
    public void receive() {
        System.out.println("this is sms Receiver");

    }

}
