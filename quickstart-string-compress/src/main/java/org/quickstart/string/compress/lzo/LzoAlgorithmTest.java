/**
 * 项目名称：quickstart-string-compress 
 * 文件名：LzoAlgorithmTest.java
 * 版本信息：
 * 日期：2018年5月22日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.string.compress.lzo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.anarres.lzo.LzoAlgorithm;
import org.anarres.lzo.LzoCompressor;
import org.anarres.lzo.LzoDecompressor;
import org.anarres.lzo.LzoInputStream;
import org.anarres.lzo.LzoLibrary;
import org.anarres.lzo.LzoOutputStream;

/**
 * LzoAlgorithmTest
 * 
 * @author：youngzil@163.com
 * @2018年5月22日 下午5:41:28
 * @since 1.0
 */
public class LzoAlgorithmTest {

    public static void main(String[] args) throws UnsupportedEncodingException, IOException {

        LzoAlgorithm algorithm = LzoAlgorithm.LZO1X;
        // 压缩
        OutputStream out = new FileOutputStream(new File("D:\\log\\123.lzo"));
        LzoCompressor compressor = LzoLibrary.getInstance().newCompressor(algorithm, null);
        LzoOutputStream stream = new LzoOutputStream(out, compressor, 256);
        stream.write("我是中国人".getBytes("UTF-8"));
        stream.close();

        // 解压
        InputStream in = new FileInputStream(new File("D:\\log\\123.lzo"));
        LzoDecompressor decompressor = LzoLibrary.getInstance().newDecompressor(algorithm, null);
        LzoInputStream inStream = new LzoInputStream(in, decompressor);
        OutputStream outputStream = new FileOutputStream(new File("D:\\log\\data.txt"));
        int read = 0;
        byte[] bytes = new byte[1024];

        while ((read = inStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
        }
        outputStream.close();
        inStream.close();
    }

}
