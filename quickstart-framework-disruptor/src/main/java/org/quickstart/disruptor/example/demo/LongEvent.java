/**
 * 项目名称：quickstart-disruptor 
 * 文件名：LongEvent.java
 * 版本信息：
 * 日期：2017年9月1日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.disruptor.example.demo;

/**
 * LongEvent
 * 
 * @author：yangzl@asiainfo.com
 * @2017年9月1日 下午4:46:44
 * @since 1.0
 */
public class LongEvent {
    private long value;

    public void set(long value) {
        this.value = value;
    }

    public long get() {
        return this.value;
    }
}
