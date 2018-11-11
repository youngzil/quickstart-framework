/**
 * 项目名称：quickstart-javase 
 * 文件名：ByteBufferUtil.java
 * 版本信息：
 * 日期：2018年4月28日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.nio.example.buffer;

import java.nio.ByteBuffer;

/**
 * ByteBufferUtil
 * 
 * @author：youngzil@163.com
 * @2018年4月28日 下午1:06:12
 * @since 1.0
 */
public class BufferCloneTest {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); ++i) {
            buffer.put((byte) i);
        }

        // ByteBuffer clone = clone(buffer);
        // ByteBuffer clone = cloneByteBuffer(buffer);
        // ByteBuffer clone = deepCopy2(buffer);//不对，内容没有拷贝进去
        ByteBuffer clone = deepCopy(buffer);

        clone.position(0);
        clone.limit(clone.capacity());
        while (clone.remaining() > 0) {
            System.out.println("clone=" + clone.get());
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());
        while (buffer.remaining() > 0) {
            System.out.println("buffer=" + buffer.get());
        }

        System.out.println("----------------修改clone后-----------------");

        for (int i = 0; i < clone.capacity(); ++i) {
            byte b = clone.get(i);
            b *= 11;
            clone.put(i, b);
        }

        clone.flip();// 切换读模式
        while (clone.remaining() > 0) {
            System.out.println("clone=" + clone.get());
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());
        while (buffer.remaining() > 0) {
            System.out.println(buffer.get());
        }
    }

    public static ByteBuffer clone(ByteBuffer original) {
        ByteBuffer clone = ByteBuffer.allocate(original.capacity());
        original.rewind();// copy from the beginning
        clone.put(original);
        original.rewind();
        clone.flip();
        return clone;
    }

    public static ByteBuffer cloneByteBuffer(final ByteBuffer original) {

        // Create clone with same capacity as original.
        final ByteBuffer clone = (original.isDirect()) ? ByteBuffer.allocateDirect(original.capacity()) : ByteBuffer.allocate(original.capacity());

        // Create a read-only copy of the original.
        // This allows reading from the original without modifying it.
        final ByteBuffer readOnlyCopy = original.asReadOnlyBuffer();

        // Flip and read from the original.
        readOnlyCopy.flip();
        clone.put(readOnlyCopy);

        return clone;
    }

    public static ByteBuffer deepCopy2(ByteBuffer source) {

        int sourceP = source.position();
        int sourceL = source.limit();

        // Create clone with same capacity as original.
        final ByteBuffer target = (source.isDirect()) ? ByteBuffer.allocateDirect(source.capacity()) : ByteBuffer.allocate(source.capacity());
        target.put(source);
        target.flip();

        source.position(sourceP);
        source.limit(sourceL);
        return target;
    }

    public static ByteBuffer deepCopy(ByteBuffer orig) {
        int pos = orig.position(), lim = orig.limit();
        try {
            orig.position(0).limit(orig.capacity()); // set range to entire buffer
            ByteBuffer toReturn = deepCopyVisible(orig); // deep copy range
            toReturn.position(pos).limit(lim); // set range to original
            return toReturn;
        } finally // do in finally in case something goes wrong we don't bork the orig
        {
            orig.position(pos).limit(lim); // restore original
        }
    }

    public static ByteBuffer deepCopyVisible(ByteBuffer orig) {
        int pos = orig.position();
        try {
            ByteBuffer toReturn;
            // try to maintain implementation to keep performance
            if (orig.isDirect())
                toReturn = ByteBuffer.allocateDirect(orig.remaining());
            else
                toReturn = ByteBuffer.allocate(orig.remaining());

            toReturn.put(orig);
            toReturn.order(orig.order());

            return (ByteBuffer) toReturn.position(0);
        } finally {
            orig.position(pos);
        }
    }

}
