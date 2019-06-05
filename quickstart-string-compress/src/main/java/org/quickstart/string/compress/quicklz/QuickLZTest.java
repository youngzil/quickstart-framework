/**
 * 项目名称：quickstart-string-compress 
 * 文件名：QuickLZTest.java
 * 版本信息：
 * 日期：2018年5月22日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.string.compress.quicklz;

/**
 * QuickLZTest
 * 
 * @author：youngzil@163.com
 * @2018年5月22日 下午1:11:16
 * @since 1.0
 */
public class QuickLZTest {

    public static void main(String[] args) {
        // 预选解压缩类库使用介绍--QuickLZ
        // 压缩
        String s = "这是一个用于测试的字符串";

        // --Level 1
        byte[] compressed = QuickLZ.compress(s.getBytes(), 1); // --返回压缩后的字符串的字节数组
        System.out.println("level1=" + compressed.length);

        // --Level3
        byte[] compressed3 = QuickLZ.compress(s.getBytes(), 3); // --返回压缩后的字符串的字节数组
        System.out.println("level3=" + compressed3.length);
        
        
        // 解压
        byte[] uncompressed = QuickLZ.decompress(compressed); // --返回解压缩后的字符串的字节数组
        byte[] uncompressed3 = QuickLZ.decompress(compressed3); // --返回解压缩后的字符串的字节数组
        
        
        System.out.println("level1=" + new String(uncompressed));
        System.out.println("level3=" + new String(uncompressed3));
        
    }

}
