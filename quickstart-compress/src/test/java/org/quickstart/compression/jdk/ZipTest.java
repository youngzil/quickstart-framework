/**
 * 项目名称：quickstart-string-compress
 * 文件名：CompressTest.java
 * 版本信息：
 * 日期：2018年5月22日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.compression.jdk;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * CompressTest
 *
 * @author：youngzil@163.com
 * @2018年5月22日 下午12:41:36
 * @since 1.0
 */
public class ZipTest {

    public static void main(String[] args) throws IOException {
        // 压缩
        String s = "这是一个用于测试的字符串";

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ZipOutputStream zout = new ZipOutputStream(out);
        zout.putNextEntry(new ZipEntry("0"));
        zout.write(s.getBytes());
        zout.closeEntry();

        byte[] compressed = out.toByteArray(); // --返回压缩后的字符串的字节数组

        System.out.println(s.getBytes().length);
        System.out.println(s.getBytes());
        System.out.println(compressed.length);
        System.out.println(compressed);

        // 解压
        ByteArrayInputStream in = new ByteArrayInputStream(compressed);
        ZipInputStream zin = new ZipInputStream(in);
        zin.getNextEntry();
        byte[] buffer = new byte[1024];
        int offset = -1;
        ByteArrayOutputStream out2 = new ByteArrayOutputStream();
        while ((offset = zin.read(buffer)) != -1) {
            out2.write(buffer, 0, offset);
        }

        byte[] uncompressed = out2.toByteArray(); // --返回解压缩后的字符串的字节数组

        System.out.println(uncompressed.length);
        System.out.println(new String(uncompressed));

    }

    // 1.compress(String):对字符串进行ZIP压缩饼返回字节数组
    // 2.decompress(byte[]):将压缩的字节数组还原成字符串
    public static final byte[] compress(String paramString) {
        if (paramString == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = null;
        ZipOutputStream zipOutputStream = null;
        byte[] arrayOfByte;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
            zipOutputStream.putNextEntry(new ZipEntry("0"));
            zipOutputStream.write(paramString.getBytes());
            zipOutputStream.closeEntry();
            arrayOfByte = byteArrayOutputStream.toByteArray();
        } catch (IOException localIOException5) {
            arrayOfByte = null;
        } finally {
            if (zipOutputStream != null) {
                try {
                    zipOutputStream.close();
                } catch (IOException localIOException6) {
                }
            }
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException localIOException7) {
                }
            }
        }
        return arrayOfByte;
    }

    public static final String decompress(byte[] paramArrayOfByte) {
        if (paramArrayOfByte == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        ZipInputStream zipInputStream = null;
        String str;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
            zipInputStream = new ZipInputStream(byteArrayInputStream);
            ZipEntry localZipEntry = zipInputStream.getNextEntry();
            byte[] arrayOfByte = new byte[1024];
            int i = -1;
            while ((i = zipInputStream.read(arrayOfByte)) != -1) {
                byteArrayOutputStream.write(arrayOfByte, 0, i);
            }
            str = byteArrayOutputStream.toString();
        } catch (IOException localIOException7) {
            str = null;
        } finally {
            if (zipInputStream != null) {
                try {
                    zipInputStream.close();
                } catch (IOException localIOException8) {
                }
            }
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (IOException localIOException9) {
                }
            }
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException localIOException10) {
                }
            }
        }
        return str;
    }

}
