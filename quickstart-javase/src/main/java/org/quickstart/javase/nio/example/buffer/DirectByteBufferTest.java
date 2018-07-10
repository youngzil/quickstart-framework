/**
 * 项目名称：quickstart-javase 
 * 文件名：DirectByteBufferTest.java
 * 版本信息：
 * 日期：2018年4月28日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.nio.example.buffer;

import java.nio.ByteBuffer;

import org.junit.Test;

/**
 * DirectByteBufferTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年4月28日 上午10:46:16
 * @since 1.0
 */
public class DirectByteBufferTest {

    // 1、HeapByteBuffer:ByteBuffer.allocate(cap);
    // 2、DirectByteBuffer:ByteBuffer.allocateDirect(cap);
    // 3、MappedByteBuffer ：FileChannel的map方法，channel.map(FileChannel.MapMode.READ_WRITE, 0, length);

    @Test
    public void test1() {
        int count = 100000;
        int cap = 1024 * 1024;
        testDirectBuf(count, cap);
        testNonDirectBuf(count, cap);

    }

    private void testDirectBuf(int count, int cap) {
        long st;
        long ed;
        ByteBuffer byteBuf = null;
        st = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {
            byteBuf = allocDirectByteBuffer(cap);

        }
        ed = System.currentTimeMillis();
        System.out.println("alloc directByteBuffer for " + count + " times spends " + (ed - st) + "ms");

        st = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {
            processBuf(byteBuf);
        }
        ed = System.currentTimeMillis();
        System.out.println("directByteBuffer process " + count + " times spends " + (ed - st) + "ms");
    }

    private ByteBuffer testNonDirectBuf(int count, int cap) {
        long st = System.currentTimeMillis();
        ByteBuffer byteBuf = null;
        for (int i = 0; i < count; i++) {
            byteBuf = allocNonDirectByteBuffer(cap);
        }
        long ed = System.currentTimeMillis();
        System.out.println("alloc nonDirectByteBuffer for " + count + " times spends " + (ed - st) + "ms");
        st = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            processBuf(byteBuf);

        }
        ed = System.currentTimeMillis();
        System.out.println("nonDirectByteBuffer process " + count + " times spends " + (ed - st) + "ms");
        return byteBuf;
    }

    private ByteBuffer allocNonDirectByteBuffer(int cap) {
        ByteBuffer byteBuf = ByteBuffer.allocate(cap);
        return byteBuf;
    }

    private ByteBuffer allocDirectByteBuffer(int cap) {
        ByteBuffer directBuf = ByteBuffer.allocateDirect(cap);
        return directBuf;
    }

    private void processBuf(ByteBuffer buf) {
        byte[] bytes = "assfasf".getBytes();
        buf.put(bytes);
        for (int i = 0; i < bytes.length; i++) {
            byte b = buf.get(i);
            byte[] bytes2 = new byte[] {b};
            // System.out.print(new String(bytes2));
        }
        // System.out.println();
        // System.out.println(buf.capacity());
    }

    public static void main(String[] args) {

        /*ByteBuffer buffer=ByteBuffer.allocateDirect(1024);
        Field cleanerField = buffer.getClass().getDeclaredField("cleaner");
        cleanerField.setAccessible(true);
        Cleaner cleaner = (Cleaner) cleanerField.get(buffer);
        cleaner.clean();*/
    }

}
