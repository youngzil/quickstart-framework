/**
 * 项目名称：quickstart-javase 
 * 文件名：DatagramChannelTest.java
 * 版本信息：
 * 日期：2018年4月28日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.io.nio.example.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * DatagramChannelTest
 * 
 * @author：youngzil@163.com
 * @2018年4月28日 上午7:48:48
 * @since 1.0
 */
public class DatagramChannelTest {

    public static void main(String[] args) throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        channel.socket().bind(new InetSocketAddress(9999));

        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        channel.receive(buf);

        String newData = "New String to write to file..." + System.currentTimeMillis();
        buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(newData.getBytes());
        buf.flip();
        int bytesSent = channel.send(buf, new InetSocketAddress("jenkov.com", 80));

        channel.connect(new InetSocketAddress("jenkov.com", 80));
        int bytesRead = channel.read(buf);
        int bytesWritten = channel.write(buf);
    }

}
