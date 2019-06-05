/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Provider.java
 * 版本信息：
 * 日期：2018年1月25日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.factory.abstracts;

import org.quickstart.design.pattern.factory.common.Receiver;
import org.quickstart.design.pattern.factory.common.Sender;

/**
 * Provider
 * 
 * @author：youngzil@163.com
 * @2018年1月25日 下午12:23:31
 * @since 1.0
 */
public interface Provider {

    public Sender produceSender();

    public Receiver produceReceiver();

}
