/**
 * 项目名称：quickstart-javase 
 * 文件名：CharFileExample.java
 * 版本信息：
 * 日期：2018年5月10日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.bio.example;

/**
 * CharFileExample 
 *  
 * @author：youngzil@163.com
 * @2018年5月10日 下午6:17:02 
 * @since 1.0
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/* 
 * 自负文件流使用：FileReader/FileWriter 
 */
public class CharFileExample {
    public void copyFile(File sourceFile, File destFile) {
        if (!sourceFile.exists() || !destFile.exists())
            return;
        if ((!sourceFile.isFile() || !destFile.isFile()))
            return;

        /* 
         * java7新特性：自动关闭资源 
         * 前提：该类实现了Closeable或AutoClosable接口 
         */
        try (FileReader reader = new FileReader(sourceFile); FileWriter writer = new FileWriter(destFile);) {
            /* 
             * 这里new char数组的时候，可以采用File类的length()方法获取源文件的大小 
             * 不过该方法的返回值是long类型，不是int类型，因此为了通用性和程序的健全 
             * 建议：不采用File类的length方法 
             */
            char[] cArray = new char[2048];
            int hasRead = -1;
            while ((hasRead = reader.read(cArray)) != -1) {
                writer.write(cArray, 0, hasRead);
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
