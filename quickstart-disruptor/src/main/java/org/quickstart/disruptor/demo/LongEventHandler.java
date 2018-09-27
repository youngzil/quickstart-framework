/**
 * 项目名称：quickstart-disruptor 
 * 文件名：LongEventHandler.java
 * 版本信息：
 * 日期：2017年9月1日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.disruptor.demo;

import com.lmax.disruptor.EventHandler;

/**
 * LongEventHandler 消息者事件处理器，打印输出到控制台
 * 
 * @author：yangzl@asiainfo.com
 * @2017年9月1日 下午4:48:04
 * @since 1.0
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    
//    为消费者消费处理器，这处需要执行速度足够快。否则，会影响ringbuffer后续没空间加入新的数据。因此，不能做业务耗时操作。建议另外开始java 线程池处理消息。
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
        System.out.println("consumer:" + Thread.currentThread().getName() + " Event: value=" + event.get() + ",sequence=" + sequence + ",endOfBatch=" + endOfBatch);
    }
}
