package org.quickstart.javase.jdk8.stream;

import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import one.util.streamex.EntryStream;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2020/5/8 17:52
 */

public class DemoListToMap {
    List<Student> list = Arrays.asList(new Student(1, 18, "阿龙", GenderColumn.BOY.getCode()), new Student(2, 17, "小花", GenderColumn.GIRL.getCode()),
        new Student(3, 17, "阿浪", GenderColumn.LADYBOY.getCode()));
    
    
    @Test
    public void testMapToMap(){
        Map<String, String> x = null;
        Map<String, Integer> y =
            x.entrySet().stream()
                .collect(Collectors.toMap(
                    e -> e.getKey(),
                    e -> Integer.parseInt(e.getValue())
                ));

        Map<String, Column> original = new HashMap<>();
        original.put("foo", new Column());
        original.put("bar", new Column());

        Map<String, Column> copy = original.entrySet()
            .stream()
            .collect(Collectors.toMap(Map.Entry::getKey,
                e -> new Column(e.getValue())));

        System.out.println(original);
        System.out.println(copy);
    }
    static class Column {
        public Column() {}
        public Column(Column c) {}
    }

    /**
     * [详解Java 8 中使用Stream将List转为Map](https://blog.csdn.net/lspj201007186/article/details/91039052)
     * <p>
     * [Java 8中Collection转为Map的方法](https://www.cnblogs.com/hiver/p/9156147.html)
     */
    @Test
    public void listToMap() {
        //        1、指定key-value，value是对象中的某个属性值。
        Map<Integer, String> userMap1 = list.stream().collect(Collectors.toMap(Student::getId, Student::getName));
    }

    @Test
    public void listToMap2() {
        //        2、指定key-value，value是对象本身，User->User 是一个返回本身的lambda表达式

        Map<Integer, Student> userMap2 = list.stream().collect(Collectors.toMap(Student::getId, Student -> Student));
    }

    @Test
    public void listToMap3() {

        //        3、指定key-value，value是对象本身，Function.identity()是简洁写法，也是返回对象本身

        Map<Integer, Student> userMap3 = list.stream().collect(Collectors.toMap(Student::getId, Function.identity()));

    }

    @Test
    public void listToMap4() {

        //        4、指定key-value，value是对象本身，Function.identity()是简洁写法，也是返回对象本身，key 冲突的解决办法，这里选择第二个key覆盖第一个key。

        Map<Integer, Student> userMap4 = list.stream().collect(Collectors.toMap(Student::getId, Function.identity(), (key1, key2) -> key2));

    }

    @Test
    public void listToMapByObjectValue() {
        // value 为对象 student -> student jdk1.8返回当前对象
        Map<Integer, Student> map = list.stream().collect(Collectors.toMap(Student::getId, student -> student));
        // 遍历打印结果
        map.forEach((key, value) -> {
            System.out.println("key: " + key + "    value: " + value);
        });
    }

    @Test
    public void listToMapByNameValue() {
        // value 为对象中的属性
        Map<Integer, String> map = list.stream().collect(Collectors.toMap(Student::getId, Student::getName));
        map.forEach((key, value) -> {
            System.out.println("key: " + key + "    value: " + value);
        });
    }

    //    要注意的是map的key必须唯一，所以有可能出现不唯一的时候，就会报错
    @Test
    public void listToMapByAgeKey() {
        // value 为对象中的属性
        Map<Integer, String> map = list.stream().collect(Collectors.toMap(Student::getAge, Student::getName));
    }

    //    因为age有相同就会报错
    //    此时stream包下Collectors.toMap方法有一个重载方法的参数，这个参数可以传一个合并的函数解决冲突
    @Test
    public void listToMapByAgeKey2() {
        // value 为对象中的属性
        Map<Integer, String> map = list.stream().collect(Collectors.toMap(Student::getAge, Student::getName, (prevKey, postKey) -> postKey));
        map.forEach((key, value) -> {
            System.out.println("key: " + key + "    value: " + value);
        });
    }

    @Test
    public void testMapMerge() {

        // [Java8合并两个Map中元素的正确姿势](https://blog.csdn.net/w605283073/article/details/82987157)

        Map<String, Integer> map1 = ImmutableMap.of("a", 2, "b", 3);
        Map<String, Integer> map2 = ImmutableMap.of("a", 3, "c", 4);
        Map<String, Integer> map3 = new HashMap<>(map1);

        // 1. Map.merge()

        // Java8为 java.util.Map接口新增了merge()函数。
        // merge()  函数的作用是: 如果给定的key之前没设置value 或者value为null, 则将给定的value关联到这个key上.
        // 否则，通过给定的remaping函数计算的结果来替换其value。如果remapping函数的计算结果为null，将解除此结果。
        map2.forEach((key, value) -> map3.merge(key, value, (v1, v2) -> v1 - v2));
        System.out.println(map3);

        //map2合并到map3(map1)中
        map2.forEach((key, value) -> map3.merge(key, value, Integer::sum));
        // (a, b) -> Stream.concat(a.stream(), b.stream()).collect(Collectors.toSet())


        // 2. Stream.concat()

        Map<String, Integer> result = Stream.concat(map1.entrySet().stream(), map2.entrySet().stream())//
            .collect(Collectors.toMap(//
                Map.Entry::getKey,//
                Map.Entry::getValue,//
                (value1, value2) -> value1 - value2));
        // (value1, value2) -> Stream.concat(value1.stream(), value2.stream()).collect(Collectors.toSet())));

        System.out.println(result);

        // 3. Stream.of()

        Map<String, Integer> result2 = Stream.of(map1, map2)//
            .flatMap(map -> map.entrySet().stream())//
            .collect(Collectors.toMap(//
                Map.Entry::getKey,//
                Map.Entry::getValue,//
                (v1, v2) -> v1 - v2));
        System.out.println(result2);

        // 4. Simple Streaming

        Map<String, Integer> result3 = map2.entrySet().stream()//
            .collect(Collectors.toMap(//
                Map.Entry::getKey,//
                Map.Entry::getValue,//
                (v1, v2) -> v1 - v2,//
                () -> new HashMap<>(map1)));
        System.out.println(result3);

        // 5. StreamEx : Stream API 的增强库

        Map<String, Integer> result4 = EntryStream.of(map1)//
            .append(EntryStream.of(map2))//
            .toMap((e1, e2) -> e1);//注意 (e1, e2) -> e1 表达式来处理重复key的问题，如果没有该表达式依然会报IllegalStateException异常。




    }

    @Data
    @AllArgsConstructor
    public class Student {
        private int id;
        private int age;
        private String name;
        private int gender;
    }

}

