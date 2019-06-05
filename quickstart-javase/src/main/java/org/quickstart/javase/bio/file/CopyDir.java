/**
 * 项目名称：quickstart-javase 
 * 文件名：CopyDir.java
 * 版本信息：
 * 日期：2018年5月10日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.bio.file;

import java.io.File;
import java.io.FilePermission;

/**
 * CopyDir
 * 
 * FIle类的简单操作：剪切文件夹（及该文件夹下面的所有目录和文件夹）。
 * 
 * @author：youngzil@163.com
 * @2018年5月10日 下午5:38:23
 * @since 1.0
 */
public class CopyDir {
    /* 
     * 1. 先在目的中新建源一级目录  
     * 2. listFiles源目录，如果是文件则renameTo，如果是目录就递归 
     */
    public static void copyDir(File sourceDir, File destDir) {
        if (!sourceDir.exists() || !sourceDir.isDirectory())
            return;

        File newDestDir = new File(destDir.getAbsolutePath(), sourceDir.getName());
        newDestDir.mkdir();

        File[] files = sourceDir.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                copyDir(files[i], newDestDir);
            } else {
                files[i].renameTo(new File(newDestDir.getAbsolutePath(), files[i].getName()));
            }
        }
        sourceDir.delete();
    }

    public static void main(String[] args) {
        File sourceDir = new File("d:" + File.separator + "xx");
        File destDir = new File("d:" + File.separator + "demo" + File.separator + "java");
        copyDir(sourceDir, destDir);
    }
}
