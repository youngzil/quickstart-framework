/**
 * 项目名称：msgtest-common
 * 文件名：TestMap.java
 * 版本信息：
 * 日期：2018年3月12日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk.collection.map;

import org.apache.tools.ant.taskdefs.Java;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * TestMap
 *
 * @author：youngzil@163.com
 * @2018年3月12日 下午3:50:20
 * @since 1.0
 */
public class TestMap {

    @Test
    public void testKeySort() {
        // JAVA 中的TreeMap支持根据Key进行排序，默认为升序排序，可以传入比较器进行自定义排序

        // TreeMap默认是升序的，如果我们需要改变排序方式，则需要使用比较器：Comparator。
        // Comparator可以对集合对象或者数组进行排序的比较器接口，实现该接口的public compare(T o1,To2)方法即可实现排序

        // 按照Key进行排序

        // lambda表达式写法
        // Map<String,String> map1 = new TreeMap<>((o1,o2)->o2.compareTo(o1));

        Map<String, String> map = new TreeMap<String, String>(new Comparator<String>() {
            public int compare(String obj1, String obj2) {
                // 降序排序
                return obj2.compareTo(obj1); //用正负表示大小值
            }
        });
        map.put("b", "ccccc");
        map.put("d", "aaaaa");
        map.put("c", "bbbbb");
        map.put("a", "ddddd");

        Set<String> keySet = map.keySet();
        Iterator<String> iter = keySet.iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            System.out.println(key + ":" + map.get(key));
        }

    }

    @Test
    public void testValueSort() {

        // [Java Map 按key排序和按Value排序](https://www.jianshu.com/p/6563a9691c74)

        // 按照Value进行排序
        //     1.使用List进行排序
        Map<String, String> map1 = new HashMap<>();
        map1.put("3", "7");
        map1.put("2", "5");
        map1.put("1", "6");
        List<Map.Entry<String, String>> list1 = new ArrayList<>(map1.entrySet());
        list1.sort(Comparator.comparing(Map.Entry::getValue));
        Map<String, String> map2 = new LinkedHashMap<>();
        for (Map.Entry<String, String> entry : list1) {
            map2.put(entry.getKey(), entry.getValue());
        }
        map2.forEach((o1, o2) -> System.out.println(o1 + ":" + o2));

        Map<String, String> map = new TreeMap<String, String>();
        map.put("a", "ddddd");
        map.put("c", "bbbbb");
        map.put("d", "aaaaa");
        map.put("b", "ccccc");

        //这里将map.entrySet()转换成list
        List<Map.Entry<String, String>> list = new ArrayList<>(map.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {

            //升序排序
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }

        });

        for (Map.Entry<String, String> mapping : list) {
            System.out.println(mapping.getKey() + ":" + mapping.getValue());
        }
    }

    @Test
    public void testValueSort2() {
        // 按照Value进行排序
        // 2.使用JAVA 8 Stream进行排序
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Tommy");
        map.put(3, "Jerry");
        map.put(2, "Catty");
        // 正序
        map.values().stream().sorted().collect(Collectors.toList()).forEach(System.out::println);
        // 逆序
        map.values().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()).forEach(System.out::println);

    }

    public static void main(String[] args) {

        Map<String, String> mapString = new HashMap<>();
        mapString.put("1", "2");
        mapString.put("3", "4");
        mapString.put("5", "6");

        Map<String, String> mapStringCopy = new HashMap<>(mapString);
        mapStringCopy.put("3", "8");

        System.out.println(mapString);
        System.out.println(mapStringCopy);

        // 重写list中的对象的hashcode

        Map<List, String> map = new HashMap<List, String>();

        List<Integer> list1 = new ArrayList<Integer>();
        List<Integer> list2 = new ArrayList<Integer>();
        List<Integer> list3 = new ArrayList<Integer>();

        Integer i1 = new Integer(11);
        Integer i2 = new Integer(12);

        list1.add(i1);
        list1.add(i2);
        list2.add(i2);
        list2.add(i1);
        list3.add(i1);
        list3.add(i2);

        list3.hashCode();

        map.put(list1, "aaaddddd");

        map.hashCode();

        System.out.println(map.get(list2));
        System.out.println(map.get(list3));
    }

}
