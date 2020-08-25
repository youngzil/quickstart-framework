package org.quickstart.javase.io.nio.example.buffer;// $Id$

import java.nio.ByteBuffer;

public class BufferSliceTest {
    static public void main(String args[]) throws Exception {

        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); ++i) {
            buffer.put((byte) i);
        }

        ByteBuffer duplicate = buffer.duplicate();
        System.out.println();

        buffer.position(3);
        buffer.limit(7);
        ByteBuffer slice = buffer.slice();

        buffer.position(0);
        buffer.limit(buffer.capacity());
        while (buffer.remaining() > 0) {
            System.out.println("buffer=" + buffer.get());
        }

        while (slice.remaining() > 0) {
            System.out.println("slice=" + slice.get());
        }

        System.out.println("----------------修改slice后-----------------");

        for (int i = 0; i < slice.capacity(); ++i) {
            byte b = slice.get(i);
            b *= 11;
            slice.put(i, b);
        }

        slice.flip();// 切换读模式
        while (slice.remaining() > 0) {
            System.out.println("slice=" + slice.get());
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());
        while (buffer.remaining() > 0) {
            System.out.println(buffer.get());
        }

    }
}
