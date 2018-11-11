/**
 * 项目名称：quickstart-javase 
 * 文件名：PredicateTest.java
 * 版本信息：
 * 日期：2018年8月31日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.Test;

/**
 * PredicateTest
 * 
 * @author：youngzil@163.com
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
     // 甚至可以用and()、or()和xor()逻辑函数来合并Predicate，
     // 例如要找到所有以J开始，长度为四个字母的名字，你可以合并两个Predicate并传入
     Predicate<String> startsWithJ = (n) -> n.startsWith("J");
     Predicate<String> fourLetterLong = (n) -> n.length() == 4;
     List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

     languages.stream()
         .filter(startsWithJ.and(fourLetterLong))
         .forEach((n) -> System.out.print("nName, which starts with 'J' and four letter long is : " + n));
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
    
    
    public static void main(String[] args){
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        System.out.println("Languages which starts with J :");
        filter(languages, (str)->str.startsWith("J"));

        System.out.println("Languages which ends with a ");
        filter(languages, (str)->str.endsWith("a"));

        System.out.println("Print all languages :");
        filter(languages, (str)->true);

         System.out.println("Print no language : ");
         filter(languages, (str)->false);

         System.out.println("Print language whose length greater than 4:");
         filter(languages, (str)->str.length() > 4);
      }
    
    public static void filter(List<String> names, Predicate condition) {
        for(String name: names)  {
            if(condition.test(name)) {
                System.out.println(name + " ");
            }
        }
    }
    
 // 更好的办法
    public static void filter2(List names, Predicate condition) {
        names.stream().filter((name) -> (condition.test(name))).forEach((name) -> {
            System.out.println(name + " ");
        });
    }

}
