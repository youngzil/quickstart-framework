/**
 * 项目名称：quickstart-disruptor 
 * 文件名：LongEventProducer.java
 * 版本信息：
 * 日期：2017年9月1日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.disruptor.demo;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;

/**
 * LongEventProducer 生产者，生产longEvent事件
 * 
 * @author：youngzil@163.com
 * @2017年9月1日 下午4:47:31
 * @since 1.0
 */
public class LongEventProducer {
    
//    RingBuffer是消息存储结构，为环形存储结构，每个单元存储一条消息。类似于队列。当ringbuffer中数据填满后，环就会阻塞，等待消费者消费掉数据。当所有消费者消费掉环中一个数据，新的消息才可以加入环中。每个环插入数据后，都会分配下一个位置的编号，即sequence 。
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
