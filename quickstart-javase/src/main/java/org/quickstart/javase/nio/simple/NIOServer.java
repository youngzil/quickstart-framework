/**
 * 项目名称：quickstart-javase 
 * 文件名：NIOServer.java
 * 版本信息：
 * 日期：2017年9月9日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.nio.simple;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * NIOServer
 * 
 * @author：youngzil@163.com
 * @2017年9月9日 下午10:05:15
 * @since 1.0
 */
public class NIOServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(NIOServer.class);

    public static void main(String[] args) throws IOException {

        System.exit(-1);

        Selector selector = Selector.open();// 1、打开selector
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();// 2、打开channel,并设置属性、监听
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(1234));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);// 3、把channel注册到selector，并绑定监听事件
        while (selector.select() > 0) {// 4-1、阻塞获取就绪事件
            Set<SelectionKey> keys = selector.selectedKeys();// 4-2、存在就绪事件，获取就绪的Key列表
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();// 4-3、循环Key列表，获取Key，（并从循环中删除【可以不删】，重要的是从注册中删除，后面的keys.remove(key);）
                iterator.remove();
                if (key.isAcceptable()) {// 5、处理Key事件：从Key中获取channel，从channel中read数据，重新注册回selector，等待下次事件到来
                    ServerSocketChannel acceptServerSocketChannel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = acceptServerSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    LOGGER.info("Accept request from {}", socketChannel.getRemoteAddress());
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int count = socketChannel.read(buffer);
                    if (count <= 0) {
                        socketChannel.close();
                        key.cancel();
                        LOGGER.info("Received invalide data, close the connection");
                        continue;
                    }
                    LOGGER.info("Received message {}", new String(buffer.array()));
                }
                keys.remove(key);
            }
        }
    }
}
