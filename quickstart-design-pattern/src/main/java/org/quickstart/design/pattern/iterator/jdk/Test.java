/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Test.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.iterator.jdk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Test
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月27日 上午9:15:47
 * @since 1.0
 */
public class Test {

    public static void main(String[] args) {

        // 都是黑箱聚集或内禀迭代子
        listIterator();
        mapIterator();

    }

    private static void mapIterator() {

        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "ab");
        map.put(4, "ab");
        map.put(4, "ab");// 和上面相同 ， 会自己筛选
        System.out.println(map.size());

        // 第二种：
        System.out.println("第二种：通过Map.entrySet使用iterator遍历key和value：");
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, String> entry = it.next();
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }
        // 第三种：推荐，尤其是容量大时
        System.out.println("第三种：通过Map.entrySet遍历key和value");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            // Map.entry<Integer,String> 映射项（键-值对） 有几个方法：用上面的名字entry
            // entry.getKey() ;entry.getValue(); entry.setValue();
            // map.entrySet() 返回此映射中包含的映射关系的 Set视图。
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        // 第一种：
        /*
         * Set<Integer> set = map.keySet(); //得到所有key的集合
         * 
         * for (Integer in : set) { String str = map.get(in);
         * System.out.println(in + "     " + str); }
         */
        System.out.println("第一种：通过Map.keySet遍历key和value：");
        for (Integer in : map.keySet()) {
            // map.keySet()返回的是所有key的值
            String str = map.get(in);// 得到每个key多对用value的值
            System.out.println(in + "     " + str);
        }

        // 第四种：
        System.out.println("第四种：通过Map.values()遍历所有的value，但不能遍历key");
        for (String v : map.values()) {
            System.out.println("value= " + v);
        }

    }

    private static void listIterator() {
        List<String> list = new ArrayList<String>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");

        // 方法三：
        // 集合类的通用遍历方式, 从很早的版本就有, 用迭代器迭代
        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        // 方法一：
        // 超级for循环遍历
        for (String attribute : list) {
            System.out.println(attribute);
        }

        // 方法二：
        // 对于ArrayList来说速度比较快, 用for循环, 以size为条件遍历:
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }

}
