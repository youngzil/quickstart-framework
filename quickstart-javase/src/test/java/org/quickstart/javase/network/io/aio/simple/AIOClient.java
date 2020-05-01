/**
 * 项目名称：quickstart-javase 
 * 文件名：AIOClient.java
 * 版本信息：
 * 日期：2018年5月10日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.network.io.aio.simple;

/**
 * AIOClient 
 *  
 * @author：youngzil@163.com
 * @2018年5月10日 下午2:45:35 
 * @since 1.0
 */
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AIOClient {

    public static void main(String[] args) throws IOException {

        final AsynchronousSocketChannel client = AsynchronousSocketChannel.open();

        InetSocketAddress serverAddress = new InetSocketAddress("127.0.0.1", 8001);

        CompletionHandler<Void, ? super Object> handler = new CompletionHandler<Void, Object>() {

            @Override
            public void completed(Void result, Object attachment) {
                client.write(ByteBuffer.wrap("Hello".getBytes()), null, new CompletionHandler<Integer, Object>() {

                    @Override
                    public void completed(Integer result, Object attachment) {
                        final ByteBuffer buffer = ByteBuffer.allocate(1024);
                        client.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {

                            @Override
                            public void completed(Integer result, ByteBuffer attachment) {
                                buffer.flip();
                                System.out.println(new String(buffer.array()));
                                try {
                                    client.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void failed(Throwable exc, ByteBuffer attachment) {}

                        });
                    }

                    @Override
                    public void failed(Throwable exc, Object attachment) {}

                });
            }

            @Override
            public void failed(Throwable exc, Object attachment) {}

        };

        client.connect(serverAddress, null, handler);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
