/**
 * 项目名称：quickstart-example 
 * 文件名：ListReverse.java
 * 版本信息：
 * 日期：2017年8月16日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.example.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ListReverse
 * 
 * @author：youngzil@163.com
 * @2017年8月16日 下午6:10:42
 * @since 1.0
 */
public class ListReverse {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        ArrayList<String> rc = new ArrayList<String>(list);
        list.clear();
        System.out.println("after clear");
        rc.forEach(s -> {
            System.out.println(s);
        });

        System.out.println("------------");
        list.forEach(s -> {
            System.out.println(s);
        });

        Collections.reverse(list);

        System.out.println("after reverse");

        list.forEach(s -> {
            System.out.println(s);
        });

    }

}
