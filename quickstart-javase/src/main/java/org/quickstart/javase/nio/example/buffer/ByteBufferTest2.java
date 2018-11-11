/**
 * 项目名称：quickstart-javase 
 * 文件名：ByteBufferTest.java
 * 版本信息：
 * 日期：2018年4月28日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.nio.example.buffer;

import java.nio.ByteBuffer;

/**
 * ByteBufferTest
 * 
 * @author：youngzil@163.com
 * @2018年4月28日 上午7:48:04
 * @since 1.0
 */
public class ByteBufferTest2 {

    public static void main(String[] args) {
        ByteBuffer buf = ByteBuffer.allocate(8);
        ByteBuffer duplicate = buf.duplicate();

    }
}
