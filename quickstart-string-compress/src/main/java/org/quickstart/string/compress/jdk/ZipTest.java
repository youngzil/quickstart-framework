/**
 * 项目名称：quickstart-string-compress 
 * 文件名：CompressTest.java
 * 版本信息：
 * 日期：2018年5月22日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.string.compress.jdk;

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

}
