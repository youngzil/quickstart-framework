/**
 * 项目名称：quickstart-javase 
 * 文件名：ServerSocketChannelTest.java
 * 版本信息：
 * 日期：2018年4月28日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.io.nio.example.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * ServerSocketChannelTest
 * 
 * @author：youngzil@163.com
 * @2018年4月28日 上午7:49:23
 * @since 1.0
 */
public class ServerSocketChannelTest {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();

            // do something with socketChannel...
        }

        /* 
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);
        while(true){
            SocketChannel socketChannel =
                    serverSocketChannel.accept();
        
            if(socketChannel != null){
                //do something with socketChannel...
            }
        }*/

        // serverSocketChannel.close();
    }

}
