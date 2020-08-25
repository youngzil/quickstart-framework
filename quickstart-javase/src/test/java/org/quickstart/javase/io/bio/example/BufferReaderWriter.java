/**
 * 项目名称：quickstart-javase 
 * 文件名：BufferReaderWriter.java
 * 版本信息：
 * 日期：2018年5月10日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.io.bio.example;

/**
 * BufferReaderWriter 
 *  
 * @author：youngzil@163.com
 * @2018年5月10日 下午6:55:12 
 * @since 1.0
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/* 
 * 字符缓冲流：BufferedReader/BufferedWriter 
 */
public class BufferReaderWriter {
    public void copyFile(File sourceFile, File destFile) {
        if (!sourceFile.exists() || !destFile.exists())
            return;
        if ((!sourceFile.isFile() || !destFile.isFile()))
            return;

        try (BufferedReader br = new BufferedReader(new FileReader(sourceFile)); BufferedWriter bw = new BufferedWriter(new FileWriter(destFile));) {
            /* 
             * 字符输入缓冲流特有的readLine方法 
             */
            String hasRead = null;
            while ((hasRead = br.readLine()) != null) {
                bw.write(hasRead);
            }
            bw.flush();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
