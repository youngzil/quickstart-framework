/**
 * 项目名称：quickstart-javase 
 * 文件名：FileDemo.java
 * 版本信息：
 * 日期：2018年5月10日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.io.bio.file;

import java.io.File;
import java.io.IOException;

/**
 * FileDemo
 * 
 * @author：youngzil@163.com
 * @2018年5月10日 下午5:35:26
 * @since 1.0
 */
public class FileDemo {

    public static void main(String[] args) {
        /* 
         * 创建file 
         * File.separator表示路径的分隔符，windows和linux上不一样，为了程序有更好的移植行，建议使用 
         */
        File file = new File("d:" + File.separator + "xx.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        /* 
         * 创建目录 
         */
        File fileDir = new File("D:" + File.separator + "xx.txt");
        if (!file.exists()) {
            fileDir.mkdir();
        }
        /* 
         * 创建多级目录 
         * mkdir：如果多级路径下有文件夹不存在，则创建失败； 
         * mkdirs：无论路径下的多级文件夹是否都存在，均创建； 
         */
        File fileDirs = new File("d:" + File.separator + "xx" + File.separator + "xxx" + File.separator + "xxxx");
        if (!fileDirs.exists()) {
            fileDirs.mkdirs();
        }

        /* 
         * 删除文件 
         * 删除目录的时候，如果目录不为空，则删除失败 
         */
        if (file.exists())
            file.delete();
        if (fileDir.exists())
            fileDir.delete();
        if (fileDirs.exists()) {
            System.out.println(fileDirs.delete());
        }
        /* 
         * 这句语句实际上并没有在硬盘上创建文件 
         */
        File files = new File("D:" + File.separator + "bb");
    }
}
