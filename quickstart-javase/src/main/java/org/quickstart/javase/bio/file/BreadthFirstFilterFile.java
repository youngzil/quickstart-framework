/**
 * 项目名称：quickstart-javase 
 * 文件名：BreadthFirstFilterFile.java
 * 版本信息：
 * 日期：2018年5月10日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.bio.file;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

/**
 * BreadthFirstFilterFile
 * 
 * File类的简单操作：广度优先过滤文件夹。
 * 
 * @author：yangzl@asiainfo.com
 * @2018年5月10日 下午5:36:48
 * @since 1.0
 */
public class BreadthFirstFilterFile {
    public static void bfff(File dir) {
        if (!dir.exists() || !dir.isDirectory())
            return;

        String[] fileNames = dir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                // TODO Auto-generated method stub
                return name.endsWith(".java");
            }
        });
        File[] files = dir.listFiles();
        ArrayList<File> subDirsList = new ArrayList<>();

        System.out.println(dir + "包含了文件：");
        for (String i : fileNames)
            System.out.println(i);

        if (files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory())
                    subDirsList.add(files[i]);
            }
            if (subDirsList.size() > 0) {
                for (File i : subDirsList)
                    bfff(i);
            }
        }

    }

    public static void main(String[] args) {
        File dir = new File("d:" + File.separator + "xx");
        bfff(dir);
    }
}
