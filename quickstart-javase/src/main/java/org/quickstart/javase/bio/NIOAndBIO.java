/**
 * 项目名称：quickstart-javase 
 * 文件名：NIOAndBIO.java
 * 版本信息：
 * 日期：2018年5月10日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.bio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIOAndBIO
 * 
 * @author：youngzil@163.com
 * @2018年5月10日 下午5:31:06
 * @since 1.0
 */
public class NIOAndBIO {

    public static void readIO() throws Exception {
        File file = new File("E:/迅雷下载/美人鱼.HD.720p.国语中字.rmvb");
        File file2 = new File("E:/迅雷下载/美人鱼.HD.720p.国语中字io.rmvb");
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(file2);
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = fis.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }
        fis.close();
        fos.close();
    }

    public static void readNIO() throws Exception {
        File file = new File("E:/迅雷下载/美人鱼.HD.720p.国语中字.rmvb");
        File file3 = new File("E:/迅雷下载/美人鱼.HD.720p.国语中字nio.rmvb");
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(file3);
        FileChannel channel = fis.getChannel();
        FileChannel outchannel = fos.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (channel.read(buffer) != -1) {
            buffer.flip();
            outchannel.write(buffer);
            buffer.clear();
        }
        channel.close();
        fis.close();
        fos.close();
    }

    public static void main(String[] args) throws Exception {

        long nioStart = System.currentTimeMillis();
        readNIO();
        System.out.println("nio-time: " + ((System.currentTimeMillis() - nioStart)));
        long ioStart = System.currentTimeMillis();
        readIO();
        System.out.println("io-time: " + ((System.currentTimeMillis() - ioStart)));
    }
}
