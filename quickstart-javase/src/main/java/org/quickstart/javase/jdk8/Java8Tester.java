/**
 * 项目名称：quickstart-javase 
 * 文件名：Java8Tester.java
 * 版本信息：
 * 日期：2018年8月28日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Java8Tester 
 *  
 * @author：youngzil@163.com
 * @2018年8月28日 下午11:43:02 
 * @since 1.0
 */
public class Java8Tester {
    public static void main(String args[]){
    
       List<String> names1 = new ArrayList<String>();
       names1.add("Google ");
       names1.add("Runoob ");
       names1.add("Taobao ");
       names1.add("Baidu ");
       names1.add("Sina ");
         
       List<String> names2 = new ArrayList<String>();
       names2.add("Google ");
       names2.add("Runoob ");
       names2.add("Taobao ");
       names2.add("Baidu ");
       names2.add("Sina ");
         
       Java8Tester tester = new Java8Tester();
       System.out.println("使用 Java 7 语法: ");
         
       tester.sortUsingJava7(names1);
       System.out.println(names1);
       System.out.println("使用 Java 8 语法: ");
         
       tester.sortUsingJava8(names2);
       System.out.println(names2);
    }
    
    // 使用 java 7 排序
    private void sortUsingJava7(List<String> names){   
       Collections.sort(names, new Comparator<String>() {
          @Override
          public int compare(String s1, String s2) {
             return s1.compareTo(s2);
          }
       });
    }
    
    // 使用 java 8 排序
    private void sortUsingJava8(List<String> names){
       Collections.sort(names, (s1, s2) -> s1.compareTo(s2));
    }
 }
