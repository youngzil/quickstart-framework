/**
 * 项目名称：quickstart-javase 
 * 文件名：SocketChannelTest.java
 * 版本信息：
 * 日期：2018年4月28日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.nio.example.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * SocketChannelTest
 * 
 * @author：youngzil@163.com
 * @2018年4月28日 上午7:49:11
 * @since 1.0
 */
public class SocketChannelTest {

    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel = SocketChannel.open();
        // socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("http://jenkov.com", 80));

        // while(! socketChannel.finishConnect() ){
        // //wait, or do something else...
        // }

        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = socketChannel.read(buf);

        String newData = "New String to write to file..." + System.currentTimeMillis();
        buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(newData.getBytes());

        buf.flip();

        while (buf.hasRemaining()) {
            socketChannel.write(buf);
        }

        socketChannel.close();

    }

}
