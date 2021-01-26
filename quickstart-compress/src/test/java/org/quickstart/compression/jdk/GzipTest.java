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
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * CompressTest
 * 
 * @author：youngzil@163.com
 * @2018年5月22日 下午12:41:36
 * @since 1.0
 */
public class GzipTest {

    public static void main(String[] args) throws IOException {
        // 预选解压缩类库使用介绍--GZIP
        // 压缩

        String s = "这是一个用于测试的字符串";

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        gzip.write(s.getBytes());
        gzip.close();// 不关闭，Exception in thread "main" java.io.EOFException: Unexpected end of ZLIB input stream
        byte[] compressed = out.toByteArray(); // --返回压缩后的字符串的字节数组

        System.out.println(s.getBytes().length);
        System.out.println(s.getBytes("utf-8"));
        System.out.println(compressed.length);
        System.out.println(compressed);

        // 解压
        int bufferSize = 1024;
        ByteArrayInputStream in = new ByteArrayInputStream(compressed);
        GZIPInputStream gzin = new GZIPInputStream(in);

        byte[] buffer = new byte[bufferSize];
        int offset = 0;
        ByteArrayOutputStream out2 = new ByteArrayOutputStream();

        while ((offset = gzin.read(buffer)) >= 0) {
            out2.write(buffer, 0, offset);
        }

        byte[] uncompressed = out.toByteArray(); // --返回解压缩后的字符串的字节数组

        System.out.println(uncompressed.length);
        System.out.println(new String(uncompressed));

    }

}
