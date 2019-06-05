/**
 * 项目名称：quickstart-javase 
 * 文件名：InOutStreamReaderWriterDemo.java
 * 版本信息：
 * 日期：2018年5月10日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.bio.example;

/**
 * InOutStreamReaderWriterDemo 
 *  
 * @author：youngzil@163.com
 * @2018年5月10日 下午7:22:17 
 * @since 1.0
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/* 
 * 输入转换流InputStreamReader，在读取数据的时候，还可以对数据进行重新编码 
 * 输出转换流OutputStreamWriter，在输出数据的时候，也可以对数据进行重新编码 
 */
public class InOutStreamReaderWriterDemo {
    /* 
     * UTF-8方式读取键盘输入 
     */
    public void demo() {
        /* 
         * 键盘输入System.in为字节流，建立转换流并以UTF-8编码方式读取键盘输入 
         * 屏幕输出System.out为字节流（PrintWriter打印流封装了OutputStream），建立输出转换流，并以UTF-8方式输出 
         */
        InputStreamReader isReader = null;
        OutputStreamWriter osWriter = null;
        try {
            isReader = new InputStreamReader(System.in, "UTF-8");
            osWriter = new OutputStreamWriter(System.out, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        /* 
         * 建立缓冲流装饰转换流提高效率 
         */
        BufferedReader br = new BufferedReader(isReader);
        BufferedWriter bw = new BufferedWriter(osWriter);
        String hasRead = null;
        try {
            while ((hasRead = br.readLine()) != null) {
                if (hasRead.equals("exit"))
                    break;
                bw.write(hasRead);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (isReader != null) {
                try {
                    isReader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }
}
