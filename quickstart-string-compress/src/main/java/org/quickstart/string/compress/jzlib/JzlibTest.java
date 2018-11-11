/**
 * 项目名称：quickstart-string-compress 
 * 文件名：JzlibTest.java
 * 版本信息：
 * 日期：2018年5月22日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.string.compress.jzlib;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.jcraft.jzlib.DeflaterOutputStream;
import com.jcraft.jzlib.InflaterInputStream;

/**
 * JzlibTest
 * 
 * @author：youngzil@163.com
 * @2018年5月22日 下午4:56:58
 * @since 1.0
 */
public class JzlibTest {

    public static void main(String[] args) throws IOException {
        // 预选解压缩类库使用介绍-- JZLIB
        // 压缩

        String s = "这是一个用于测试的字符串";
        System.out.println("str=" + s.getBytes().length);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DeflaterOutputStream dout = new DeflaterOutputStream(out);
        dout.write(s.getBytes());
        dout.close(); // --需要先关闭
        byte[] compressed = out.toByteArray(); // --返回压缩后的字符串的字节数组
        System.out.println("compressed=" + compressed.length);

        // 解压
        ByteArrayInputStream in = new ByteArrayInputStream(compressed);
        InflaterInputStream input = new InflaterInputStream(in);
        byte[] buffer = new byte[1024];

        int offset = -1;

        ByteArrayOutputStream out2 = new ByteArrayOutputStream();
        while ((offset = input.read(buffer)) != -1) {
            out2.write(buffer, 0, offset);
        }
        out.close(); // --需要先关闭
        byte[] uncompressed = out.toByteArray(); // --返回解压缩后的字符串的字节数组
        System.out.println("uncompressed=" + new String(uncompressed));
    }

}
