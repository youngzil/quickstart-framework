/**
 * 项目名称：quickstart-javase 
 * 文件名：ConcurrentMapTest.java
 * 版本信息：
 * 日期：2017年7月27日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.juc.collection;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * ConcurrentMapTest
 * 
 * @author：youngzil@163.com
 * @2017年7月27日 下午10:39:06
 * @version 2.0
 */
public class ConcurrentMapTest {

    public static void main(String[] args) {

        ConcurrentMap<String, String> concurrentMap = new ConcurrentHashMap<String, String>();
        concurrentMap.put("key", "value");
        String value = concurrentMap.get("key");
        System.out.println(value);

        String previous = concurrentMap.putIfAbsent("key", "value2");
        System.out.println("previous=" + previous);
        System.out.println("concurrentMap.get(\"key\")=" + concurrentMap.get("key"));

        ConcurrentNavigableMap<String, String> map = new ConcurrentSkipListMap<String, String>();
        map.put("1", "one");
        map.put("2", "two");
        map.put("3", "three");

        // headMap(T toKey) 方法返回一个包含了小于给定 toKey 的 key 的子 map。
        // <2
        ConcurrentNavigableMap headMap = map.headMap("2");
        System.out.println(headMap);

        // tailMap(T fromKey) 方法返回一个包含了不小于给定 fromKey 的 key 的子 map。
        // >=2
        ConcurrentNavigableMap tailMap = map.tailMap("2");
        System.out.println(tailMap);

        // subMap() 方法返回原始 map 中，键介于 from(包含) 和 to (不包含) 之间的子 map。示例如下：
        // >=2 & <3
        ConcurrentNavigableMap subMap = map.subMap("2", "3");
        System.out.println(subMap);

    }

}
