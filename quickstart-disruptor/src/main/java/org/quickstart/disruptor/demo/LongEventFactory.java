/**
 * 项目名称：quickstart-disruptor 
 * 文件名：LongEventFactory.java
 * 版本信息：
 * 日期：2017年9月1日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.disruptor.demo;

import com.lmax.disruptor.EventFactory;

/**
 * LongEventFactory 事件生产工厂
 * 
 * @author：youngzil@163.com
 * @2017年9月1日 下午4:47:00
 * @since 1.0
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }

}
