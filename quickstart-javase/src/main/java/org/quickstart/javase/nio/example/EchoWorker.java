/**
 * 项目名称：quickstart-javase 
 * 文件名：EchoWorker.java
 * 版本信息：
 * 日期：2017年9月20日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.nio.example;

import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;

/**
 * EchoWorker
 * 
 * @author：youngzil@163.com
 * @2017年9月20日 下午10:35:32
 * @since 1.0
 */
public class EchoWorker implements Runnable {
    private List queue = new LinkedList();

    public void processData(NioServer server, SocketChannel socket, byte[] data, int count) {
        byte[] dataCopy = new byte[count];
        System.arraycopy(data, 0, dataCopy, 0, count);
        synchronized (queue) {
            queue.add(new ServerDataEvent(server, socket, dataCopy));
            queue.notify();
        }
    }

    public void run() {
        ServerDataEvent dataEvent;

        while (true) {
            // Wait for data to become available
            synchronized (queue) {
                while (queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                    }
                }
                dataEvent = (ServerDataEvent) queue.remove(0);
            }

            // Return to sender
            dataEvent.server.send(dataEvent.socket, dataEvent.data);
        }
    }
}
