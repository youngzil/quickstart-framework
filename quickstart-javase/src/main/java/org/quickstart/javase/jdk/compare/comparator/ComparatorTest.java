/**
 * 项目名称：quickstart-javase 
 * 文件名：ComparatorTest.java
 * 版本信息：
 * 日期：2018年4月23日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk.compare.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.quickstart.javase.jdk.compare.Employee;

/**
 * ComparatorTest
 * 
 * @author：youngzil@163.com
 * @2018年4月23日 上午8:20:02
 * @since 1.0
 */
public class ComparatorTest {

    public static void main(String[] args) {
        test1();
        test2();
    }

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

    public static void test1() {
        List<Employee> list = genList();
        Collections.sort(list, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getSalary() - o2.getSalary();
            }
        });
        System.out.println(list);
    }

    public static void test2() {
        List<Employee> list = genList();
        Comparator<Employee> comparator = (Employee e1, Employee e2) -> e1.getSalary() - e2.getSalary();
        list.sort(comparator);
        System.out.println(list);
    }

}
