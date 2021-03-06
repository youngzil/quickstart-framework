/**
 * 项目名称：quickstart-javase 文件名：MapTraverseTest.java 版本信息： 日期：2018年5月10日 Copyright yangzl Corporation 2018 版权所有 *
 */
package org.quickstart.javase.jdk.collection.map;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * MapTraverseTest
 * 
 * @author：youngzil@163.com
 * @2018年5月10日 下午8:08:02
 * @since 1.0
 */
public class MapTraverseTest {

  public static void main(String[] args) {

    Map<String, Integer> items = new HashMap<>();
    items.put("A", 10);
    items.put("B", 20);
    items.put("C", 30);
    items.put("D", 40);
    items.put("E", 50);
    items.put("F", 60);

    // 1、Normal way to loop a Map.
    for (Map.Entry<String, Integer> entry : items.entrySet()) {
      System.out.println("Item : " + entry.getKey() + " Count : " + entry.getValue());
    }

    // 1.2 In Java 8, you can loop a Map with forEach + lambda expression.
    items.forEach((k, v) -> System.out.println("Item : " + k + " Count : " + v));

    items.forEach((k, v) -> {
      System.out.println("Item : " + k + " Count : " + v);
      if ("E".equals(k)) {
        System.out.println("Hello E");
      }
    });

    items.forEach((key, value) -> {
      System.out.println("Key : " + key + " Value : " + value);
    });

    items.entrySet().forEach(entry -> {
      System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
    });

    Map<String, String> map = new HashMap<String, String>();
    map.put("1", "value1");
    map.put("2", "value2");
    map.put("3", "value3");

    // 第一步：检查参数是否已经排序
    String[] keys = map.keySet().toArray(new String[0]);
    Arrays.sort(keys);

    // 第一种：普遍使用，二次取值
    System.out.println("通过Map.keySet遍历key和value：");
    for (String key : map.keySet()) {
      System.out.println("key= " + key + " and value= " + map.get(key));
    }

    // 第二种
    System.out.println("通过Map.entrySet使用iterator遍历key和value：");
    Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry<String, String> entry = it.next();
      System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
    }

    // 第三种：推荐，尤其是容量大时
    System.out.println("通过Map.entrySet遍历key和value");
    for (Map.Entry<String, String> entry : map.entrySet()) {
      System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
    }

    // 第四种
    System.out.println("通过Map.values()遍历所有的value，但不能遍历key");
    for (String v : map.values()) {
      System.out.println("value= " + v);
    }
  }

}
