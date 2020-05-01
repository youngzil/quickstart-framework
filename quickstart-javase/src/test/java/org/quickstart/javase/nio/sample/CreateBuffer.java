package org.quickstart.javase.nio.sample;
// $Id$

import java.nio.ByteBuffer;

public class CreateBuffer {
    static public void main(String args[]) throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.put((byte) 'a');
        buffer.put((byte) 'b');
        buffer.put((byte) 'c');

        buffer.flip();


        buffer.clear();
        buffer.compact();
        buffer.mark();//保存当前的position到mark，mark默认是-1
        buffer.reset();//恢复position到之前的mark，如果mark=-1，说明没有执行过mark()，会报错InvalidMarkException

        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
    }
}
