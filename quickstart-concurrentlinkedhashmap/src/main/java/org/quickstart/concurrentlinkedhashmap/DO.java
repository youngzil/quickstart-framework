/**
 * 项目名称：quickstart-concurrentlinkedhashmap 
 * 文件名：DO.java
 * 版本信息：
 * 日期：2018年5月21日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.concurrentlinkedhashmap;

/**
 * DO
 * 
 * @author：yangzl@asiainfo.com
 * @2018年5月21日 下午11:40:09
 * @since 1.0
 */
public class DO {
    private int param;

    public DO(int i) {
        this.param = i;
    }

    /**
     * Getter method for property <tt>param</tt>.
     * 
     * @return property value of param
     */
    public int getParam() {
        return param;
    }

    /**
     * Setter method for property <tt>param</tt>.
     * 
     * @param param value to be assigned to property param
     */
    public void setParam(int param) {
        this.param = param;
    }
}
