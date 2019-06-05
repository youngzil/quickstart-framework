/**
 * 项目名称：quickstart-javase 
 * 文件名：ArraysTest.java
 * 版本信息：
 * 日期：2018年4月9日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk;

import java.util.Arrays;
import java.util.List;

/**
 * ArraysTest
 * 
 * @author：youngzil@163.com
 * @2018年4月9日 上午9:33:41
 * @since 1.0
 */
public class ArraysTest {

    public static void main(String[] args) {

        System.out.println(Arrays.asList());
        System.out.println(Arrays.asList(""));
        // System.out.println(Arrays.asList(null));//NPE

        List<String> list = Arrays.asList();
        List<String> list2 = Arrays.asList("");

        System.out.println(list);
        System.out.println(list2);

        System.out.println(list.contains("ksy"));
        System.out.println(list.isEmpty());
        
        
        final boolean debug = true;
        if(debug) {
            System.out.println("ddd");
        }
        
        final boolean online = false;
        if(online) {
            System.out.println("online");
        }
        
        assert debug == online;

        int a = 4;
        int b = 3;
        assert a == b;
        
        

    }

    public  static void  testVariableParameterMethod(int... a){

        System.out.println(a);

    }

}
