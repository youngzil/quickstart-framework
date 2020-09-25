/**
 * 项目名称：quickstart-javase
 * 文件名：Test.java
 * 版本信息：
 * 日期：2017年7月20日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Test
 *
 * https://www.zhihu.com/question/31203609/answer/50992895
 *
 * @author：youngzil@163.com
 * @2017年7月20日 下午10:19:08
 * @version 2.0
 */
public class QuoteTest {

    @Test
    public void testQuote() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        add(list);
        for (Integer j : list) {
            System.err.print(j + ",");
            ;
        }
        System.err.println("");
        System.err.println("*********************");
        String a = "A";
        append(a);
        System.err.println(a);
        int num = 5;
        addNum(num);
        System.err.println(num);
    }

    @Test
    public void testQuote2() {

        //    第一个例子：基本类型
        int num = 20;
        foo(num); // num 没有被改变
        System.out.println("num=" + num);

        //    第二个例子：没有提供改变自身方法的引用类型
        String str = "hello";
        foo(str); // str 也没有被改变
        System.out.println("str=" + str);

        //    第三个例子：提供了改变自身方法的引用类型
        StringBuilder sb = new StringBuilder("iphone");
        foo(sb); // sb 被改变了，变成了"iphone4"。
        System.out.println("sb=" + sb);

        //    第四个例子：提供了改变自身方法的引用类型，但是不使用，而是使用赋值运算符。
        StringBuilder sb2 = new StringBuilder("iphone");
        foo2(sb2); // sb 没有被改变，还是 "iphone"。
        System.out.println("sb2=" + sb2);

    }

    void foo(int value) {
        value = 100;
    }

    void foo(String text) {
        text = "windows";
    }

    void foo(StringBuilder builder) {
        builder.append("4");
    }

    void foo2(StringBuilder builder) {
        builder = new StringBuilder("ipad");
    }

    static void add(List<Integer> list) {
        list.add(100);
    }

    static void append(String str) {
        str += "is a";
    }

    static void addNum(int a) {
        a = a + 10;
    }

}
