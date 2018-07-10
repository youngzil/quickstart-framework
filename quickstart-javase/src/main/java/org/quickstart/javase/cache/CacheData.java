/**
 * 项目名称：quickstart-javase 
 * 文件名：CacheData.java
 * 版本信息：
 * 日期：2017年8月1日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.cache;

/**
 * CacheData
 * 
 * @author：yangzl@asiainfo.com
 * @2017年8月1日 下午10:44:18
 * @version 2.0
 */
public class CacheData {

    Object data = null;
    Long time = System.currentTimeMillis();
    int count = 0;

    CacheData(Object data) {
        this.data = data;
        time = System.currentTimeMillis();
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void addCount() {
        this.count++;
    }

}
