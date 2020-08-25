/**
 * 项目名称：quickstart-javase 
 * 文件名：ByteBufferTest.java
 * 版本信息：
 * 日期：2018年4月28日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.io.nio.example.buffer;

import java.nio.ByteBuffer;

/**
 * ByteBufferTest
 * 
 * @author：youngzil@163.com
 * @2018年4月28日 上午7:48:04
 * @since 1.0
 */
public class ByteBufferTest {

    public static void main(String[] args) {
        ByteBuffer buf = ByteBuffer.allocate(48);
        byte a = 'a';
        byte b = 'b';
        byte c = 'c';
        byte d = 'd';

        System.out.println(buf.get());
        System.out.println(buf.get());
        System.out.println("capacity=" + buf.capacity());
        System.out.println("position=" + buf.position());
        System.out.println("limit=" + buf.limit());
        
        buf.put(a);
        buf.put(b);
        buf.put(c);
        buf.put(c);
        buf.put(d);

         buf.putInt(1);//int是32位，占用4个字节

        buf.flip();
        System.out.println("------------read before---------------");
        System.out.println("capacity=" + buf.capacity());
        System.out.println("position=" + buf.position());
        System.out.println("limit=" + buf.limit());

        System.out.println("------------read after---------------");
        System.out.println(buf.get());
        System.out.println(buf.get());
        System.out.println("capacity=" + buf.capacity());
        System.out.println("position=" + buf.position());
        System.out.println("limit=" + buf.limit());

        // buf.clear();//丢弃未读的
        // buf.compact();

        buf.put(a);
        buf.put(b);
        buf.put(c);
        buf.put(c);
        buf.put(d);

        System.out.println("capacity=" + buf.capacity());
        System.out.println("position=" + buf.position());
        System.out.println("limit=" + buf.limit());

        buf.flip();
        System.out.println("------------重新放元素---------------");
        System.out.println(buf.get());
        System.out.println("capacity=" + buf.capacity());
        System.out.println("position=" + buf.position());
        System.out.println("limit=" + buf.limit());

        System.out.println("------------mark后--------------");
        buf.mark();
        System.out.println(buf.get());
        System.out.println(buf.get());
        System.out.println("capacity=" + buf.capacity());
        System.out.println("position=" + buf.position());
        System.out.println("limit=" + buf.limit());

        System.out.println("------------reset后--------------");
        buf.reset();

        System.out.println(buf.get());
        System.out.println(buf.get());
        System.out.println("capacity=" + buf.capacity());
        System.out.println("position=" + buf.position());
        System.out.println("limit=" + buf.limit());

        System.out.println("------------rewind后--------------");
        buf.rewind();
        System.out.println(buf.get());
        System.out.println(buf.get());
        System.out.println("capacity=" + buf.capacity());
        System.out.println("position=" + buf.position());
        System.out.println("limit=" + buf.limit());

    }

}
