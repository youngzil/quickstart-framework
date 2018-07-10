/**
 * 项目名称：quickstart-disruptor 
 * 文件名：LongEventProducer.java
 * 版本信息：
 * 日期：2017年9月1日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.disruptor.example.demo;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;

/**
 * LongEventProducer 生产者，生产longEvent事件
 * 
 * @author：yangzl@asiainfo.com
 * @2017年9月1日 下午4:47:31
 * @since 1.0
 */
public class LongEventProducer {
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void product(ByteBuffer bb) {
        long sequence = ringBuffer.next(); // Grab the next sequence
        try {
            LongEvent event = ringBuffer.get(sequence); // Get the entry in the Disruptor
                                                        // for the sequence
            event.set(bb.getLong(0)); // Fill with data
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
