/**
 * 项目名称：quickstart-javase 
 * 文件名：Resource.java
 * 版本信息：
 * 日期：2017年7月26日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.example.ThreadDeadLock;

/**
 * Resource 资源类，用于代表线程竞争的数据资源
 * 
 * @author：yangzl@asiainfo.com
 * @2017年7月26日 下午7:42:15
 * @version 2.0
 */
public class Resource {

    private int value;// 资源的属性

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
