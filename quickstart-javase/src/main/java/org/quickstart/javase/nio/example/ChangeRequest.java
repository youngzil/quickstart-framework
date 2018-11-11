/**
 * 项目名称：quickstart-javase 
 * 文件名：ChangeRequest.java
 * 版本信息：
 * 日期：2017年9月20日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.nio.example;

import java.nio.channels.SocketChannel;

/**
 * ChangeRequest
 * 
 * @author：youngzil@163.com
 * @2017年9月20日 下午10:36:49
 * @since 1.0
 */
public class ChangeRequest {
    public static final int REGISTER = 1;
    public static final int CHANGEOPS = 2;

    public SocketChannel socket;
    public int type;
    public int ops;

    public ChangeRequest(SocketChannel socket, int type, int ops) {
        this.socket = socket;
        this.type = type;
        this.ops = ops;
    }
}
