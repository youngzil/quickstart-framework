/**
 * 项目名称：quickstart-javase 
 * 文件名：FileChannel.java
 * 版本信息：
 * 日期：2018年4月27日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.io.nio.example.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * FileChannel
 * 
 * @author：youngzil@163.com
 * @2018年4月27日 下午8:28:07
 * @since 1.0
 */
public class FileChannelTest {

    public static void main(String[] args) throws IOException {

        RandomAccessFile aFile = new RandomAccessFile("nio", "rw");
        FileChannel inChannel = aFile.getChannel();

        // create buffer with capacity of 48 bytes
        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf); // read into buffer.
        while (bytesRead != -1) {
            System.out.println("Read " + bytesRead);

            buf.flip(); // make buffer ready for read

            while (buf.hasRemaining()) {
                System.out.print((char) buf.get()); // read 1 byte at a time
            }

            buf.clear(); // make buffer ready for writing
            bytesRead = inChannel.read(buf);
        }
        aFile.close();

    }

}
