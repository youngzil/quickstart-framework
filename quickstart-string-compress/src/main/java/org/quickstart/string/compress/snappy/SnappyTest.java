/**
 * 项目名称：quickstart-string-compress 
 * 文件名：SnappyTest.java
 * 版本信息：
 * 日期：2018年5月22日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.string.compress.snappy;

import java.io.IOException;

import org.xerial.snappy.Snappy;

/**
 * SnappyTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年5月22日 下午4:30:12
 * @since 1.0
 */
public class SnappyTest {

    public static void main(String[] args) throws IOException {
        // 预选解压缩类库使用介绍--Snappy
        // 压缩

        String s = "这是一个用于测试的字符串";
        System.out.println("str=" + s.getBytes().length);

        byte[] compressed = Snappy.compress(s.getBytes()); // --返回压缩后的字符串的字节数组
        System.out.println("compressed=" + compressed.length);

        // 解压
        byte[] uncompressed = Snappy.uncompress(compressed); // --返回解压缩后的字符串的字节数组
        System.out.println("uncompressed=" + new String(uncompressed));

    }

}
