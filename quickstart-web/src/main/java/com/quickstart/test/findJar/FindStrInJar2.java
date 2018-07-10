package com.quickstart.test.findJar;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 寻找指定路径下jar包中含特定字符串的文件
 * 
 * @author 千年独步
 */
public class FindStrInJar2 {

    public String condition; // 查询的条件

    long all_num = 0;

    public ArrayList<String> jarFiles = new ArrayList<String>();

    public FindStrInJar2() {}

    public FindStrInJar2(String condition) {
        this.condition = condition;
    }

    public FindStrInJar2(String condition, String exclude) {
        this.condition = condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Long find(String dir, boolean recurse) {
        searchDir(dir, recurse);
        return this.all_num;
    }

    public List<String> getFilenames() {
        return this.jarFiles;
    }

    protected String getClassName(ZipEntry entry) {
        StringBuffer className = new StringBuffer(entry.getName().replace("/", "."));
        return className.toString();
    }

    @SuppressWarnings({"unused", "rawtypes", "resource"})
    protected void searchDir(String dir, boolean recurse) {
        try {
            File d = new File(dir);
            if (!d.isDirectory()) {
                return;
            }
            File[] files = d.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (recurse && files[i].isDirectory()) {
                    searchDir(files[i].getAbsolutePath(), true);
                } else {
                    String filename = files[i].getAbsolutePath();
                    if (filename.endsWith(".jar") || filename.endsWith(".zip")) {
                        ZipFile zip = new ZipFile(filename);
                        Enumeration entries = zip.entries();
                        while (entries.hasMoreElements()) {
                            ZipEntry entry = (ZipEntry) entries.nextElement();
                            String thisClassName = getClassName(entry);
                            BufferedReader r = new BufferedReader(new InputStreamReader(zip.getInputStream(entry)));
                            String line = r.readLine();
                            int line_num = 1;
                            while (null != line) {
                                if (line.toLowerCase().contains(condition.toLowerCase())) {
                                    // this.jarFiles.add(files[i].getPath() + ",,"+ entry.getName() + ","+ "line number = " + line_num);
                                    // break;
                                    System.out.println(files[i].getPath() + ",," + entry.getName() + "," + "line number = " + line_num);
                                    all_num++;
                                }
                                line = r.readLine();
                                line_num++;
                            }

                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        FindStrInJar2 findInJar = new FindStrInJar2("<bean"); // 要寻找的字符串
        Long num = findInJar.find("D:\rmtest", true);
        System.out.println("共匹配:" + num);
    }

}
