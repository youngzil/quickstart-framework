/**
 * 项目名称：quickstart-javase 
 * 文件名：IteratorExample.java
 * 版本信息：
 * 日期：2018年2月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * IteratorExample
 * 
 * @author：youngzil@163.com
 * @2018年2月26日 下午4:10:20
 * @since 1.0
 */
public class IteratorExample {

    public static void main(String[] args) {
        List<String> all = new ArrayList<String>();// 建立一个实例化的数组集合接口的实例化对象
        all.add("Hello");
        all.add("_");
        all.add("World!!");
        Iterator<String> iterator = all.iterator();// 建立属于集合对象的实例化的迭代器
        while (iterator.hasNext()) {
            String str = iterator.next();// 通过迭代器来读取当前集合当中的数据元素
            if ("_".equals(str)) {
                all.remove(str);// 使用集合当中的remove方法对当前迭代器当中的数据元素值进行删除操作(注:此操作将会破坏
                // 整个迭代器结构)使得迭代器在接下来将不会起作用
            } else {
                System.out.println(str + " ");
            }
        }
        System.out.println("\n删除\"_\"之后的集合当中的数据为:" + all);
    }

}
