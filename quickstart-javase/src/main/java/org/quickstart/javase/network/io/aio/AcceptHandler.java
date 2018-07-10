/**
 * 项目名称：quickstart-javase 
 * 文件名：AcceptHandler.java
 * 版本信息：
 * 日期：2018年5月10日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.network.io.aio;

/**
 * AcceptHandler 
 *  
 * @author：yangzl@asiainfo.com
 * @2018年5月10日 下午12:33:41 
 * @since 1.0
 */
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

//作为handler接收客户端连接  
public class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncServerHandler> {
    @Override
    public void completed(AsynchronousSocketChannel channel, AsyncServerHandler serverHandler) {
        // 继续接受其他客户端的请求
        Server.clientCount++;
        System.out.println("连接的客户端数：" + Server.clientCount);
        serverHandler.channel.accept(serverHandler, this);
        // 创建新的Buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 异步读 第三个参数为接收消息回调的业务Handler
        channel.read(buffer, buffer, new ServerReadHandler(channel));
    }

    @Override
    public void failed(Throwable exc, AsyncServerHandler serverHandler) {
        exc.printStackTrace();
        serverHandler.latch.countDown();
    }
}
