/**
 * 项目名称：quickstart-javase 
 * 文件名：ReadHandler.java
 * 版本信息：
 * 日期：2018年5月10日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.network.io.aio;

/**
 * ReadHandler 
 *  
 * @author：youngzil@163.com
 * @2018年5月10日 下午12:34:03 
 * @since 1.0
 */
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

import org.quickstart.javase.network.io.bio.Calculator;

public class ServerReadHandler implements CompletionHandler<Integer, ByteBuffer> {
    // 用于读取半包消息和发送应答
    private AsynchronousSocketChannel channel;

    public ServerReadHandler(AsynchronousSocketChannel channel) {
        this.channel = channel;
    }

    // 读取到消息后的处理
    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        // flip操作
        attachment.flip();
        // 根据
        byte[] message = new byte[attachment.remaining()];
        attachment.get(message);
        try {
            String expression = new String(message, "UTF-8");
            System.out.println("服务器收到消息: " + expression);
            String calrResult = null;
            try {
                calrResult = Calculator.cal(expression).toString();
            } catch (Exception e) {
                calrResult = "服务器计算错误：" + e.getMessage();
            }
            // 向客户端发送消息
            doWrite(calrResult);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    // 发送消息
    private void doWrite(String result) {
        byte[] bytes = result.getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        // 异步写数据 参数与前面的read一样
        channel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer buffer) {
                // 如果没有发送完，就继续发送直到完成
                if (buffer.hasRemaining())
                    channel.write(buffer, buffer, this);
                else {
                    // 创建新的Buffer
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    // 异步读 第三个参数为接收消息回调的业务Handler
                    channel.read(readBuffer, readBuffer, new ServerReadHandler(channel));
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                try {
                    channel.close();
                } catch (IOException e) {
                }
            }
        });
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        try {
            this.channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
