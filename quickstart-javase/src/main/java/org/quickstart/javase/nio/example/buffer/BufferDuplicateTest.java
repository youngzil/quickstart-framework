package org.quickstart.javase.nio.example.buffer;// $Id$

import java.nio.ByteBuffer;

public class BufferDuplicateTest {
    static public void main(String args[]) throws Exception {

        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); ++i) {
            buffer.put((byte) i);
        }

        ByteBuffer duplicate = buffer.duplicate();

        buffer.position(0);
        buffer.limit(buffer.capacity());
        while (buffer.remaining() > 0) {
            System.out.println("buffer=" + buffer.get());
        }

        duplicate.flip();
        while (duplicate.remaining() > 0) {
            System.out.println("duplicate=" + duplicate.get());
        }

        System.out.println("----------------修改duplicate后-----------------");

        for (int i = 0; i < duplicate.capacity(); ++i) {
            byte b = duplicate.get(i);
            b *= 11;
            duplicate.put(i, b);
        }

        duplicate.flip();// 切换读模式
        while (duplicate.remaining() > 0) {
            System.out.println("duplicate=" + duplicate.get());
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());
        while (buffer.remaining() > 0) {
            System.out.println(buffer.get());
        }

    }
}
