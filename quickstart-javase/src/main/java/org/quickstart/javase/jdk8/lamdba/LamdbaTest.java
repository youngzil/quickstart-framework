/**
 * 项目名称：quickstart-javase 
 * 文件名：LamdbaTest.java
 * 版本信息：
 * 日期：2017年8月7日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk8.lamdba;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.quickstart.javase.jdk8.lamdba.LamdbaTest2.Converter;

/**
 * LamdbaTest
 * 
 * @author：yangzl@asiainfo.com
 * @2017年8月7日 下午9:45:55
 * @version 2.0
 */
public class LamdbaTest {

    public static void main(String[] args) {

        List<Integer> numbers = new ArrayList<>();
        numbers.add(123);
        numbers.add(124);
        numbers.add(125);
        numbers.add(126);

        // jdk1.7 before
        // no.1
        for (Integer number : numbers) {
            System.out.println(number);
        }

        // no.2
        for (int index = 0, len = numbers.size(); index < len; index++) {
            System.out.println(numbers.get(index));
        }

        // jdk1.8 after amdba实现

        // no.1
        System.out.println("no1-----");
        numbers.forEach((Integer integer) -> {
            System.out.println(integer);
        });

        // no.2
        System.out.println("no2-----");
        numbers.forEach(integer -> {
            System.out.println(integer);
        });

        // no.3
        System.out.println("no3-----");
        numbers.forEach(integer -> System.out.println(integer));

        // no.4
        System.out.println("no4-----");
        numbers.forEach(System.out::println);

        // no.5
        System.out.println("no5-----");
        numbers.forEach(new LamdbaTest().new MyConsumer());

    }

    class MyConsumer implements Consumer<Integer> {

        @Override
        public void accept(Integer integer) {
            System.out.println(integer);
        }
    }

}
