/**
 * 项目名称：quickstart-disruptor 
 * 文件名：LongEventProducerWithTranslator.java
 * 版本信息：
 * 日期：2017年9月1日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.disruptor.example.demo;

import java.nio.ByteBuffer;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

/**
 * LongEventProducerWithTranslator 
 *  
 * @author：yangzl@asiainfo.com
 * @2017年9月1日 下午4:48:36 
 * @since 1.0
 */
/**
 * post-3.0 the preferred approach for publishing messages is via the Event Publisher/Event Translator portion of the API. E.g.
 * 
 * @author harry
 * 
 */
public class LongEventProducerWithTranslator {
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    private static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSLATOR = new EventTranslatorOneArg<LongEvent, ByteBuffer>() {
        public void translateTo(LongEvent event, long sequence, ByteBuffer bb) {
            event.set(bb.getLong(0));
        }
    };

    public void product(ByteBuffer bb) {
        ringBuffer.publishEvent(TRANSLATOR, bb);
    }
}
