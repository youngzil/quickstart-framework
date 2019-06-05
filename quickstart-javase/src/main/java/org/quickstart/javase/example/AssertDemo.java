/**
 * 项目名称：quickstart-javase 
 * 文件名：AssertDemo.java
 * 版本信息：
 * 日期：2017年9月20日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.example;

/**
 * AssertDemo
 * 
 * @author：youngzil@163.com
 * @2017年9月20日 下午12:30:12
 * @since 1.0
 */
public class AssertDemo {
    public static void main(String[] args) {

        String s = null;
        assert s == null ? true : false;
        assert false;
        System.out.println("end");

        test1(+5);
        test2(-3);
    }

    private static void test1(int a) {
        assert a > 0;
        System.out.println(a);
    }

    private static void test2(int a) {
        assert a > 0 : "something goes wrong here, a cannot be less than 0";
        System.out.println(a);
    }
}
