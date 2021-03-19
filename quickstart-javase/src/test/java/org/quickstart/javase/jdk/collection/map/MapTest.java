/**
 * 项目名称：quickstart-javase
 * 文件名：MapTest.java
 * 版本信息：
 * 日期：2018年5月10日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk.collection.map;

import org.quickstart.javase.jdk.collection.Day;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * MapTest
 *
 * @author：youngzil@163.com
 * @2018年5月10日 下午7:58:53
 * @since 1.0
 */
public class MapTest {

    public static void main(String[] args) {
        Day day1 = new Day(1, 2, 3);
        Day day2 = new Day(2, 3, 4);
        Map<String, Day> map = new HashMap<String, Day>();
        //成对放入key-value对
        Day dd = map.put("第一个", day1);
        Day dd2 = map.put("第二个", day2);

        map.computeIfAbsent("第san", key -> {
            System.out.println("key=" + key);
            return new Day(3, 3, 3);
        });

        //判断是否包含指定的key
        System.out.println(map.containsKey("第一个"));
        //判断是否包含指定的value
        System.out.println(map.containsValue(day1));
        //循环遍历
        //1.获得Map中所有key组成的set集合
        Set<String> keySet = map.keySet();
        //2.使用foreach进行遍历
        for (String key : keySet) {
            //根据key获得指定的value
            System.out.println(map.get(key));
        }
        //根据key来移除key-value对
        map.remove("第一个");
        System.out.println(map);
    }

}
