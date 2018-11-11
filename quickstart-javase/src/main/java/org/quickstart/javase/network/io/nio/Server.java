/**
 * 项目名称：quickstart-javase 
 * 文件名：Server.java
 * 版本信息：
 * 日期：2018年5月10日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.network.io.nio;

/**
 * Server
 * 
 * @author：youngzil@163.com
 * @2018年5月10日 下午12:13:34
 * @since 1.0
 */
public class Server {
    private static int DEFAULT_PORT = 12345;
    private static ServerHandle serverHandle;

    public static void start() {
        start(DEFAULT_PORT);
    }

    public static synchronized void start(int port) {
        if (serverHandle != null)
            serverHandle.stop();
        serverHandle = new ServerHandle(port);
        new Thread(serverHandle, "Server").start();
    }

    public static void main(String[] args) {
        start();
    }
}
