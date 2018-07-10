/**
 * 项目名称：quickstart-javase 
 * 文件名：NumberTypeConvert.java
 * 版本信息：
 * 日期：2017年8月11日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.utils.base;

import java.nio.ByteBuffer;

/**
 * NumberTypeConvert
 * 
 * @author：yangzl@asiainfo.com
 * @2017年8月11日 下午12:28:16
 * @since 1.0
 */
public class NumberTypeConvert {
    private static ByteBuffer buffer = ByteBuffer.allocate(8);

    public static void main(String[] args) {

        // 测试 int 转 byte
        int int0 = 234;
        byte byte0 = intToByte(int0);
        System.out.println("byte0=" + byte0);// byte0=-22
        // 测试 byte 转 int
        int int1 = byteToInt(byte0);
        System.out.println("int1=" + int1);// int1=234

        // 测试 int 转 byte 数组
        int int2 = 1417;
        byte[] bytesInt = intToByteArray(int2);
        System.out.println("bytesInt=" + bytesInt);// bytesInt=[B@de6ced
        // 测试 byte 数组转 int
        int int3 = byteArrayToInt(bytesInt);
        System.out.println("int3=" + int3);// int3=1417

        // 测试 long 转 byte 数组
        long long1 = 2223;
        byte[] bytesLong = longToBytes(long1);
        System.out.println("bytes=" + bytesLong);// bytes=[B@c17164
        // 测试 byte 数组 转 long
        long long2 = bytesToLong(bytesLong);
        System.out.println("long2=" + long2);// long2=2223
    }

    // byte 与 int 的相互转换
    public static byte intToByte(int x) {
        return (byte) x;
    }

    public static int byteToInt(byte b) {
        // Java 总是把 byte 当做有符处理；我们可以通过将其和 0xFF 进行二进制与得到它的无符值
        return b & 0xFF;
    }

    // byte 数组与 int 的相互转换
    public static int byteArrayToInt(byte[] b) {
        return b[3] & 0xFF | (b[2] & 0xFF) << 8 | (b[1] & 0xFF) << 16 | (b[0] & 0xFF) << 24;
    }

    public static byte[] intToByteArray(int a) {
        return new byte[] {(byte) ((a >> 24) & 0xFF), (byte) ((a >> 16) & 0xFF), (byte) ((a >> 8) & 0xFF), (byte) (a & 0xFF)};
    }

    // byte 数组与 long 的相互转换
    public static byte[] longToBytes(long x) {
        buffer.putLong(0, x);
        return buffer.array();
    }

    public static long bytesToLong(byte[] bytes) {
        buffer.put(bytes, 0, bytes.length);
        buffer.flip();// need flip
        return buffer.getLong();
    }

}
