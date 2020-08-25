/**
 * 项目名称：quickstart-javase 
 * 文件名：ByteFileExample.java
 * 版本信息：
 * 日期：2018年5月10日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.io.bio.example;

/**
 * ByteFileExample 
 * 
 * 字节文件流使用：FileInputStream/FileOutputStream 
 *  
 * @author：youngzil@163.com
 * @2018年5月10日 下午6:14:51 
 * @since 1.0
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteFileExample {
    /* 
     * 传统的拷贝代码，需要显示的关闭流资源 
     * 图片文件的拷贝（二进制文件的拷贝，例如图片、mp3、视频等） 
     */
    public static void copyPicture(File resourcePic, File destPic) {
        if (!resourcePic.exists() || !destPic.exists())
            return;
        if ((!resourcePic.isFile() || !destPic.isFile()))
            return;

        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(resourcePic);
            fos = new FileOutputStream(destPic);
            byte[] bArray = new byte[fis.available() / 4];
            int hasRead = -1;
            /* 
             * while循环为拷贝代码 
             */
            while ((hasRead = fis.read(bArray)) != -1) {
                fos.write(bArray, 0, hasRead);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            /* 
             * 关闭流 
             */
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    /* 
     * java7异常新特性：自动关闭流资源 
     * 形式：try(I/O流) 
     *          {try块代码} 
     *      catch 
     *          {异常处理} 
     */
    public void copyBinaryFile(File sourceFile, File destFile) {
        if (!sourceFile.exists() || !destFile.exists())
            return;
        if ((!sourceFile.isFile() || !destFile.isFile()))
            return;

        try (FileInputStream fis = new FileInputStream(sourceFile); FileOutputStream fos = new FileOutputStream(destFile);) {
            byte[] bArray = new byte[fis.available() / 4];
            int hasRead = -1;
            /* 
             * while循环为拷贝代码 
             */
            while ((hasRead = fis.read(bArray)) != -1) {
                fos.write(bArray, 0, hasRead);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
