/**
 * 项目名称：quickstart-javase 
 * 文件名：Cache.java
 * 版本信息：
 * 日期：2017年8月1日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.cache;

/**
 * Cache
 * 
 * @author：youngzil@163.com
 * @2017年8月1日 下午10:16:35
 * @version 2.0
 */
public class Cache {
    private String key;// 缓存ID
    private Object value;// 缓存数据
    private long timeOut;// 更新时间
    private boolean expired; // 是否终止

    public Cache() {
        super();
    }

    public Cache(String key, Object value, long timeOut, boolean expired) {
        this.key = key;
        this.value = value;
        this.timeOut = timeOut;
        this.expired = expired;
    }

    public String getKey() {
        return key;
    }

    public long getTimeOut() {
        return timeOut;
    }

    public Object getValue() {
        return value;
    }

    public void setKey(String string) {
        key = string;
    }

    public void setTimeOut(long l) {
        timeOut = l;
    }

    public void setValue(Object object) {
        value = object;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean b) {
        expired = b;
    }
}
