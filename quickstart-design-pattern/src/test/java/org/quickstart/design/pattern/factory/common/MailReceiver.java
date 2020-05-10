/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：MailReceiver.java
 * 版本信息：
 * 日期：2018年1月25日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.factory.common;

/**
 * MailReceiver
 * 
 * @author：youngzil@163.com
 * @2018年1月25日 下午2:28:08
 * @since 1.0
 */
public class MailReceiver implements Receiver {

    /* (non-Javadoc)
     * @see org.quickstart.design.pattern.factory.common.Receiver#receive()
     */
    @Override
    public void receive() {
        System.out.println("this is mail Receiver");

    }

}
