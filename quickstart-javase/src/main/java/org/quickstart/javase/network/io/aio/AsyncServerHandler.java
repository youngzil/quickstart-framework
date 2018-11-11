/**
 * 项目名称：quickstart-javase 
 * 文件名：AsyncServerHandler.java
 * 版本信息：
 * 日期：2018年5月10日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.network.io.aio;

/**
 * AsyncServerHandler 
 *  
 * @author：youngzil@163.com
 * @2018年5月10日 下午12:33:11 
 * @since 1.0
 */
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

public class AsyncServerHandler implements Runnable {
    public CountDownLatch latch;
    public AsynchronousServerSocketChannel channel;

    public AsyncServerHandler(int port) {
        try {
            // 创建服务端通道
            channel = AsynchronousServerSocketChannel.open();
            // 绑定端口
            channel.bind(new InetSocketAddress(port));
            System.out.println("服务器已启动，端口号：" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        // CountDownLatch初始化
        // 它的作用：在完成一组正在执行的操作之前，允许当前的现场一直阻塞
        // 此处，让现场在此阻塞，防止服务端执行完成后退出
        // 也可以使用while(true)+sleep
        // 生成环境就不需要担心这个问题，以为服务端是不会退出的
        latch = new CountDownLatch(1);
        // 用于接收客户端的连接
        channel.accept(this, new AcceptHandler());
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
