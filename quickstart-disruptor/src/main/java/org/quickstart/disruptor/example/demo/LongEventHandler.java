/**
 * 项目名称：quickstart-disruptor 
 * 文件名：LongEventHandler.java
 * 版本信息：
 * 日期：2017年9月1日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.disruptor.example.demo;

import com.lmax.disruptor.EventHandler;

/**
 * LongEventHandler 消息者事件处理器，打印输出到控制台
 * 
 * @author：yangzl@asiainfo.com
 * @2017年9月1日 下午4:48:04
 * @since 1.0
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
        System.out.println("consumer:" + Thread.currentThread().getName() + " Event: value=" + event.get() + ",sequence=" + sequence + ",endOfBatch=" + endOfBatch);
    }
}
