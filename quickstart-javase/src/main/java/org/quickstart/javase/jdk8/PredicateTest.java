/**
 * 项目名称：quickstart-javase 
 * 文件名：PredicateTest.java
 * 版本信息：
 * 日期：2018年8月31日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk8;

import java.util.function.Predicate;

import org.junit.Test;

/**
 * PredicateTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年8月31日 下午5:00:03
 * @since 1.0
 */
public class PredicateTest {

    @Test
    public void test() {
        // 数字类型 判断值是否大于5
        Predicate<Integer> predicate = x -> x > 5;
        System.out.println(predicate.test(10));// true

        // 字符串的非空判断
        Predicate<String> predicateStr = x -> null == x && "".equals(x);
        System.out.println(predicateStr.test(""));// false

        // 默认方法的一些使用:
        // 判断条件的连接 与操作--and
        // 取非操作--negate
        // 或操作--or
        // isEqual--比较
    }

    @Test
    public void test2() throws InterruptedException {
        String name = "";
        String name1 = "12";
        String name2 = "12345";

        System.out.println(validInput(name, inputStr -> !inputStr.isEmpty() && inputStr.length() <= 3));
        System.out.println(validInput(name1, inputStr -> !inputStr.isEmpty() && inputStr.length() <= 3));
        System.out.println(validInput(name2, inputStr -> !inputStr.isEmpty() && inputStr.length() <= 3));

    }

    public static boolean validInput(String name, Predicate<String> function) {
        return function.test(name);
    }

}
