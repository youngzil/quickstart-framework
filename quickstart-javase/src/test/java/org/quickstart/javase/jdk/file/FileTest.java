/**
 * 项目名称：quickstart-javase
 * 文件名：FileTest.java
 * 版本信息：
 * 日期：2019年1月3日
 * Copyright yangzl Corporation 2019
 * 版权所有 *
 */
package org.quickstart.javase.jdk.file;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void test() throws IOException {

        File file = new File("/Users/lengfeng/Desktop/topics.txt");
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
        BufferedReader br = new BufferedReader(isr);
        List<String> list = new ArrayList<String>();
        String line = null;
        while (StringUtils.isNotBlank(line = br.readLine())) {
            list.add(line);
        }

        String[] topics = list.toArray(new String[list.size()]);
    }

}
