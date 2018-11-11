/**
 * 项目名称：quickstart-javase 
 * 文件名：BufferInOutStream.java
 * 版本信息：
 * 日期：2018年5月10日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.bio.example;

/**
 * BufferInOutStream 
 *  
 * @author：youngzil@163.com
 * @2018年5月10日 下午6:19:56 
 * @since 1.0
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* 
 * 字节缓冲流拷贝二进制文件提升效率：BufferedInputStream/BufferedOutputStream 
 */
public class BufferInOutStream {
    public void copyBinaryDate(File sourceFile, File destFile) {
        if (!sourceFile.exists() || !destFile.exists())
            return;
        if ((!sourceFile.isFile() || !destFile.isFile()))
            return;

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourceFile)); BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile));) {
            byte[] bArray = new byte[bis.available() / 4];
            int hasRead = -1;
            while ((hasRead = bis.read(bArray)) != -1) {
                bos.write(bArray, 0, hasRead);
            }
            /* 
             * 读取完文件之后，再一次性刷到磁盘 
             * 如果在while循环中flush，相当于读一次就写一次，那么就没有利用到缓冲流的缓冲区 
             * 这样的结果是缓冲流和FileOutputStream一样了 
             */
            bos.flush();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
