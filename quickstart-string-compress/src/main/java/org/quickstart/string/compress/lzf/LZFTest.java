/**
 * 项目名称：quickstart-string-compress 
 * 文件名：Test.java
 * 版本信息：
 * 日期：2018年5月22日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.string.compress.lzf;

import com.ning.compress.lzf.LZFDecoder;
import com.ning.compress.lzf.LZFEncoder;
import com.ning.compress.lzf.LZFException;

/**
 * Test
 * 
 * @author：yangzl@asiainfo.com
 * @2018年5月22日 下午1:49:25
 * @since 1.0
 */
public class LZFTest {

    public static void main(String[] args) throws LZFException {
        // 预选解压缩类库使用介绍-- LZF
        // 压缩
        String s = "这是一个用于测试的字符串";
        System.out.println("str=" + s.getBytes().length);

        byte[] compressed = LZFEncoder.encode(s.getBytes()); // --返回压缩后的字符串的字节数组
        System.out.println("compressed=" + compressed.length);
        
        // 解压
        byte[] uncompressed = LZFDecoder.decode(compressed); // --返回解压缩后的字符串的字节数组
        System.out.println("uncompressed=" + new String(uncompressed));
    }

}
