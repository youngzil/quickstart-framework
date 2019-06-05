/**
 * 项目名称：quickstart-javase 
 * 文件名：IteratorExample.java
 * 版本信息：
 * 日期：2018年5月10日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * IteratorExample
 * 
 * @author：youngzil@163.com
 * @2018年5月10日 下午7:56:46
 * @since 1.0
 */
public class IteratorExample {

    public static void main(String[] args) {
        // 创建集合，添加元素
        Collection<Day> days = new ArrayList<Day>();
        for (int i = 0; i < 10; i++) {
            Day day = new Day(i, i * 60, i * 3600);
            days.add(day);
        }
        // 获取days集合的迭代器
        Iterator<Day> iterator = days.iterator();
        while (iterator.hasNext()) {// 判断是否有下一个元素
            Day next = iterator.next();// 取出该元素
            // 逐个遍历，取得元素后进行后续操作
            // .....
        }

        List<String> list = Arrays.asList("java语言", "C语言", "C++语言");
        Iterator<String> iterator2 = list.iterator();
        while (iterator2.hasNext()) {
            String next = iterator2.next();// 集合元素的值传给了迭代变量，仅仅传递了对象引用。保存的仅仅是指向对象内存空间的地址
            next = "修改后的";
            System.out.println(next);

        }
        System.out.println(list);

    }

}
