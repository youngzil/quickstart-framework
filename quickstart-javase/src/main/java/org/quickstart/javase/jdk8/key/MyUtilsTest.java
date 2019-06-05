/**
 * 项目名称：quickstart-javase 
 * 文件名：MyUtilsTest.java
 * 版本信息：
 * 日期：2017年8月7日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk8.key;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * MyUtilsTest
 * 
 * @author：youngzil@163.com
 * @2017年8月7日 下午10:22:13
 * @version 2.0
 */
public class MyUtilsTest {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, new Comparator<String>() {// java以前老版本的写法
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });
        for (String name : names) {
            System.out.println(name);
        }

        args = new String[] {"microsoft", "apple", "linux", "oracle"};
        Arrays.sort(args, Utils::compareByLength);

        for (String s : args) {

            System.out.println(s);

        }

    }
}
