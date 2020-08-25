/**
 * 项目名称：quickstart-javase 
 * 文件名：DeepFirstTraversalFileDir.java
 * 版本信息：
 * 日期：2018年5月10日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.io.bio.file;

import java.io.File;

/**
 * DeepFirstTraversalFileDir
 * 
 * File类的简单操作：深度优先遍历文件夹。
 * 
 * @author：youngzil@163.com
 * @2018年5月10日 下午5:36:21
 * @since 1.0
 */
public class DeepFirstTraversalFileDir {
    public static void dft(File dir) {
        if (!dir.exists() || !dir.isDirectory())
            return;

        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                System.out.println(files[i] + "----------------");
                dft(files[i]);
            } else {
                System.out.println(files[i]);
            }
        }
    }

    public static void main(String[] args) {
        File dir = new File("d:" + File.separator + "demo");
        dft(dir);
    }
}
