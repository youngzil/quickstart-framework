/**
 * 项目名称：quickstart-web 
 * 文件名：MyRequestObject.java
 * 版本信息：
 * 日期：2016年12月29日
 * Copyright asiainfo Corporation 2016
 * 版权所有 *
 */
package com.quickstart.test.socket.nio;

/**
 * MyRequestObject 
 *  
 * @author：youngzil@163.com
 * @2016年12月29日 下午3:58:58 
 * @version 1.0
 */
import java.io.Serializable;

public class MyRequestObject implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String value;

    private byte[] bytes;

    public MyRequestObject(String name, String value) {
        this.name = name;
        this.value = value;
        this.bytes = new byte[1024];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Request [name: " + name + ", value: " + value + ", bytes: " + bytes.length + "]");
        return sb.toString();
    }
}
