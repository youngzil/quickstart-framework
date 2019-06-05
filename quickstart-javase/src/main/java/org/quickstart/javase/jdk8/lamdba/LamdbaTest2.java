/**
 * 项目名称：quickstart-javase 
 * 文件名：LamdbaTest.java
 * 版本信息：
 * 日期：2017年8月7日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk8.lamdba;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * LamdbaTest
 * 
 * @author：youngzil@163.com
 * @2017年8月7日 下午9:45:55
 * @version 2.0
 */
public class LamdbaTest2 {

    public static void main(String[] args) {

        /*Static method reference:
            String::valueOf
            Non-static method reference:
            Object::toString
            Capturing method reference:
            x::toString
            Constructor reference:
            ArrayList::new*/

        // 使用lambda表达式方式
        // 使用方法引用方式
        // 静态方法
        // 实例方法
        // 构造函数

        List<String> list = new ArrayList<>();
        for (Object n : list) {
            System.out.println(n);
        }
        list.forEach(n -> System.out.println(n));
        list.forEach(System.out::println);

        Converter<String, Integer> a = (from) -> Integer.valueOf(from);
        // Java 8 允许你使用 :: 关键字来传递方法或者构造函数引用
        Converter<String, Integer> converter = Integer::valueOf;// 这个是静态方式导入
        Integer m = a.convert("123");
        System.out.println(m);
        test test = mmm -> mmm + "aaaa";// mmm代表你要传入的参数
        String testResult = test.aa("ssssssddddd");// aa代表你要传入的方法
        System.out.println(testResult);

    }

    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);
    }

    interface test {
        String aa(String mmm);
    }
    // 使用函数式接口时，接口的定义只能有一个，@FunctionalInterface注解可有可无

}
