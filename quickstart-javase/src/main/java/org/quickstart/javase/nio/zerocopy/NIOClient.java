/**
 * 项目名称：quickstart-javase 
 * 文件名：NIOClient.java
 * 版本信息：
 * 日期：2017年9月9日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.nio.zerocopy;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * NIOClient
 * 
 * @author：yangzl@asiainfo.com
 * @2017年9月9日 下午9:58:16
 * @since 1.0
 */
public class NIOClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        SocketChannel socketChannel = SocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(1234);
        socketChannel.connect(address);
        RandomAccessFile file = new RandomAccessFile(NIOClient.class.getClassLoader().getResource("test.txt").getFile(), "rw");
        FileChannel channel = file.getChannel();
        channel.transferTo(0, channel.size(), socketChannel);
        channel.close();
        file.close();
        socketChannel.close();
    }
}
