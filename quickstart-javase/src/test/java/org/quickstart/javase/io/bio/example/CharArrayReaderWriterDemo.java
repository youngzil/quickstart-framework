/**
 * 项目名称：quickstart-javase 
 * 文件名：CharArrayReaderWriterDemo.java
 * 版本信息：
 * 日期：2018年5月10日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.io.bio.example;

/**
 * CharArrayReaderWriterDemo 
 *  
 * @author：youngzil@163.com
 * @2018年5月10日 下午7:31:13 
 * @since 1.0
 */
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;

public class CharArrayReaderWriterDemo {
    public static void main(String[] args) {
        CharArrayReader car = new CharArrayReader("welcom to java".toCharArray());
        CharArrayWriter caw = new CharArrayWriter();
        int hasRead = -1;
        try {
            while ((hasRead = car.read()) != -1) {
                caw.write((char) hasRead);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(caw.size());
        System.out.println(caw.toCharArray());
        System.out.println(caw.toString());
    }
}
