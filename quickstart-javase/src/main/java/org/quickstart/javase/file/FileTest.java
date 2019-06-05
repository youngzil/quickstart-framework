/**
 * 项目名称：quickstart-javase 
 * 文件名：FileTest.java
 * 版本信息：
 * 日期：2019年1月3日
 * Copyright yangzl Corporation 2019
 * 版权所有 *
 */
package org.quickstart.javase.file;

import java.io.File;
import java.io.IOException;

/**
 * FileTest
 * 
 * @author：youngzil@163.com
 * @2019年1月3日 下午5:52:31
 * @since 1.0
 */
public class FileTest {

    public static void main(String[] args) throws IOException {
        File file = new File(".\\test.txt");
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getCanonicalPath());
    }

}
