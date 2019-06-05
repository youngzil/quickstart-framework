/**
 * 项目名称：msgframe-common 
 * 文件名：TestMap.java
 * 版本信息：
 * 日期：2018年3月12日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.collection.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TestMap
 * 
 * @author：youngzil@163.com
 * @2018年3月12日 下午3:50:20
 * @since 1.0
 */
public class TestMap {

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
