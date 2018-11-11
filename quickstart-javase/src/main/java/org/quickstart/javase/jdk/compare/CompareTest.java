/**
 * 项目名称：quickstart-javase 
 * 文件名：CompareTest.java
 * 版本信息：
 * 日期：2018年4月23日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk.compare;

import java.util.ArrayList;
import java.util.List;

/**
 * CompareTest
 * 
 * @author：youngzil@163.com
 * @2018年4月23日 上午8:18:08
 * @since 1.0
 */
public class CompareTest {

    public static List<Employee> genList() {
        Employee e1 = new Employee("aaa", 100);
        Employee e2 = new Employee("bbb", 150);
        Employee e3 = new Employee("ccc", 80);
        List<Employee> list = new ArrayList();

        list.add(e1);
        list.add(e2);
        list.add(e3);

        return list;
    }

    public static void t1() {
        List<Employee> list = genList();
        // Collections.sort(list); 两种方式都可以，此种方式源码中就是调用的list.sort(null)
        list.sort(null);
        System.out.println(list);
    }

    public static void main(String[] args) {
        t1();
    }

}
