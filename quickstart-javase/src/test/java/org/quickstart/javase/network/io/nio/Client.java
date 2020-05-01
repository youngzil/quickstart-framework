/**
 * 项目名称：quickstart-javase 
 * 文件名：Client.java
 * 版本信息：
 * 日期：2018年5月10日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.network.io.nio;

/**
 * Client
 * 
 * @author：youngzil@163.com
 * @2018年5月10日 下午12:16:05
 * @since 1.0
 */
public class Client {
    private static String DEFAULT_HOST = "127.0.0.1";
    private static int DEFAULT_PORT = 12345;
    private static ClientHandle clientHandle;

    public static void start() {
        start(DEFAULT_HOST, DEFAULT_PORT);
    }

    public static synchronized void start(String ip, int port) {
        if (clientHandle != null)
            clientHandle.stop();
        clientHandle = new ClientHandle(ip, port);
        new Thread(clientHandle, "Server").start();
    }

    // 向服务器发送消息
    public static boolean sendMsg(String msg) throws Exception {
        if (msg.equals("q"))
            return false;
        clientHandle.sendMsg(msg);
        return true;
    }

    public static void main(String[] args) {
        start();
    }
}
