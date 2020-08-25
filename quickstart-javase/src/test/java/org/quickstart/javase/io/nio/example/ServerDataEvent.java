/**
 * 项目名称：quickstart-javase 
 * 文件名：ServerDataEvent.java
 * 版本信息：
 * 日期：2017年9月20日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.io.nio.example;

import java.nio.channels.SocketChannel;

/**
 * ServerDataEvent
 * 
 * @author：youngzil@163.com
 * @2017年9月20日 下午10:36:03
 * @since 1.0
 */
public class ServerDataEvent {
    public NioServer server;
    public SocketChannel socket;
    public byte[] data;

    public ServerDataEvent(NioServer server, SocketChannel socket, byte[] data) {
        this.server = server;
        this.socket = socket;
        this.data = data;
    }
}
