/**
 * 项目名称：quickstart-json 
 * 文件名：TestFastJson.java
 * 版本信息：
 * 日期：2017年12月13日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.fastjson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/**
 * TestFastJson
 * 
 * @author：yangzl@asiainfo.com
 * @2017年12月13日 下午6:00:48
 * @since 1.0
 */
/* 
 * 这里将json的转化和解析都放在一起了，大家可以根据实际需要来转化json字符串和解析json字符串 
 */
public class TestFastJson {

    static class Person {
        private String id;
        private String name;
        private int age;

        public Person() {

        }

        public Person(String id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person [age=" + age + ", id=" + id + ", name=" + name + "]";
        }

    }

    public static void main(String[] args) {
        method1();
        method2();
        method3();
        method4();
    }

    static void method1() {
        System.out.println("javabean转化示例开始----------");
        Person person = new Person("1", "fastjson", 1);

        // 这里将javabean转化成json字符串
        String jsonString = JSON.toJSONString(person);
        System.out.println(jsonString);
        // 这里将json字符串转化成javabean对象,
        person = JSON.parseObject(jsonString, Person.class);
        System.out.println(person.toString());

        System.out.println("javabean转化示例结束----------");
    }

    static void method2() {
        System.out.println("List<javabean>转化示例开始----------");

        Person person1 = new Person("1", "fastjson1", 1);
        Person person2 = new Person("2", "fastjson2", 2);
        List<Person> persons = new ArrayList<Person>();
        persons.add(person1);
        persons.add(person2);
        String jsonString = JSON.toJSONString(persons);
        System.out.println("json字符串:" + jsonString);

        // 解析json字符串
        List<Person> persons2 = JSON.parseArray(jsonString, Person.class);
        // 输出解析后的person对象，也可以通过调试模式查看persons2的结构
        System.out.println("person1对象：" + persons2.get(0).toString());
        System.out.println("person2对象：" + persons2.get(1).toString());

        System.out.println("List<javabean>转化示例结束----------");
    }

    static void method3() {
        System.out.println("List<String>转化示例开始----------");
        List<String> list = new ArrayList<String>();
        list.add("fastjson1");
        list.add("fastjson2");
        list.add("fastjson3");
        String jsonString = JSON.toJSONString(list);
        System.out.println("json字符串:" + jsonString);

        // 解析json字符串
        List<String> list2 = JSON.parseObject(jsonString, new TypeReference<List<String>>() {});
        System.out.println(list2.get(0) + "::" + list2.get(1) + "::" + list2.get(2));
        System.out.println("List<String>转化示例结束----------");

    }

    static void method4() {
        System.out.println(" List<Map<String,Object>>转化示例开始 ----------");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("key1", 1);
        map2.put("key2", 2);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        list.add(map);
        list.add(map2);
        String jsonString = JSON.toJSONString(list);
        System.out.println("json字符串:" + jsonString);
        // 解析json字符串
        List<Map<String, Object>> list2 = JSON.parseObject(jsonString, new TypeReference<List<Map<String, Object>>>() {});

        System.out.println("map的key1值" + list2.get(0).get("key1"));
        System.out.println("map的key2值" + list2.get(0).get("key2"));
        System.out.println("ma2p的key1值" + list2.get(1).get("key1"));
        System.out.println("map2的key2值" + list2.get(1).get("key2"));
    }

}
