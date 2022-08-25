package org.quickstart.javase.jdk8.stream;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-08-08 19:35
 */
public class ListDistinct {

    @Test
    public void testJavaString() {

        List<String> stringList = new ArrayList<>();

        // 第一种方法
        Set<String> set = new LinkedHashSet<>();
        set.addAll(stringList);

        stringList.clear();

        stringList.addAll(set);

        // 第二种方法
        List<String> unique = stringList.stream().distinct().collect(Collectors.toList());

    }

    @Test
    public void testJavaObject() {
        List<ServiceGroup> serviceGroupList = new ArrayList<>();

        ServiceGroup sg = new ServiceGroup();
        sg.setId("123");
        sg.setApiCode("test123");
        serviceGroupList.add(sg);

        ServiceGroup sg2 = new ServiceGroup();
        sg2.setId("124");
        sg2.setApiCode("test124");
        serviceGroupList.add(sg2);

        ServiceGroup sg3 = new ServiceGroup();
        sg3.setId("123");
        sg3.setApiCode("test12333333");
        serviceGroupList.add(sg3);

        ServiceGroup sg4 = new ServiceGroup();
        sg4.setId("124");
        sg4.setApiCode("test124444444");
        serviceGroupList.add(sg4);

        // 根据id去重，如果id是long可以使用comparingLong(Person::getId)
        List<ServiceGroup> distinctServiceGroup =
            serviceGroupList.stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparing(ServiceGroup::getId))), ArrayList::new));

        // 多个字段去重
        List<ServiceGroup> distinctClass2 = serviceGroupList.stream().collect(
            Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getId() + "-" + o.getApiVersion()))),
                ArrayList::new));

    }

    @Test
    public void testJavaObject2() {

        Person p1 = new Person(1l, "jack");
        Person p2 = new Person(3l, "jack chou");
        Person p3 = new Person(2l, "tom");
        Person p4 = new Person(4l, "hanson");
        Person p5 = new Person(5l, "胶布虫");

        List<Person> persons = Arrays.asList(p1, p2, p3, p4, p5, p5, p1, p2, p2);

        // 第一种方法
        Set<Person> personSet = new TreeSet<>((o1, o2) -> o1.getId().compareTo(o2.getId()));
        personSet.addAll(persons);

    /* public static List<Person> removeDupliById(List<Person> persons) {
      Set<Person> personSet = new TreeSet<>((o1, o2) -> o1.getId().compareTo(o2.getId()));
      personSet.addAll(persons);
    
      return new ArrayList<>(personSet);
    }*/

        // 第二种方法

        // 根据id去重
        List<Person> unique =
            persons.stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingLong(Person::getId))), ArrayList::new));

        // 第三种，重写distinctByKey方法
        // remove duplicate
        persons.stream().filter(distinctByKey(p -> p.getId())).forEach(p -> System.out.println(p));

    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    @Test
    public void testJavaObject1() {

        // 1、重写Person对象的equals()方法和hashCode()方法:
        // 2、使用contains去重，List 的contains()方法底层实现使用对象的equals方法去比较的，其实重写equals()就好，但重写了equals最好将hashCode也重写了。

        Person p1 = new Person(1l, "jack");
        Person p2 = new Person(3l, "jack chou");
        Person p3 = new Person(2l, "tom");
        Person p4 = new Person(4l, "hanson");
        Person p5 = new Person(5l, "胶布虫");

        List<Person> persons = Arrays.asList(p1, p2, p3, p4, p5, p5, p1, p2, p2);

        List<Person> personList = new ArrayList<>();
        // 去重
        persons.stream().forEach(p -> {
            if (!personList.contains(p)) {
                personList.add(p);
            }
        });
        System.out.println(personList);

    }

    @Test
    public void testCollectionGroupBy() {
        // Set同理也可进行转换
        //创建集合
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        //使用stream流进行分组
        Map<Boolean, List<Integer>> preAvg = list.stream()
            .collect(Collectors.groupingBy(x -> x < 2 || x > 4));
        List<Integer> tr = preAvg.get(true);
        List<Integer> fa = preAvg.get(false);
        System.out.println("true" + tr);
        System.out.println("false" + fa);
        //使用filter过滤
        List<Integer> collect = list.stream().filter(x -> x < 2 || x > 4).collect(Collectors.toList());
        List<Integer> collect2 = list.stream().filter(x -> x >= 2 && x <= 4).collect(Collectors.toList());
        //输出满足过滤条件的集合数据
        System.out.println("collect " + collect);
        System.out.println("collect2 " + collect2);

        Map< Boolean, Set<Integer> > map =
            list.stream().collect( Collectors.partitioningBy( y -> y % 2 == 0,
                Collectors.toSet() ) );

        Set<Integer> odds = map.get(Boolean.TRUE);
        Set<Integer> evens = map.get(Boolean.FALSE);
        System.out.println("true" + odds);
        System.out.println("false" + evens);

        // 使用Guava的partition方法
        List<List<Integer>> lists = Lists.partition(list, 3);
        lists.forEach(System.out::println);

    }

    @Test
    public void testMergeListMapByKey() {
        List<Map<String, String>> list = new ArrayList<>();
        List<Map<String, String>> list1 = new ArrayList<>();
        List<Map<String, String>> list2 = new ArrayList<>();


        Map<String, String> map1 = new HashMap<>();
        map1.put("clusterId", "test1");
        map1.put("brokerNum", "1");

        Map<String, String> map2 = new HashMap<>();
        map2.put("clusterId", "test2");
        map2.put("brokerNum", "2");


        Map<String, String> map3 = new HashMap<>();
        map3.put("clusterId", "test3");
        map3.put("brokerNum", "3");

        list.add(map1);
        list.add(map2);
        list.add(map3);

        Map<String, String> map4 = new HashMap<>();
        map4.put("clusterId", "test1");
        map4.put("topicNum", "4");

        Map<String, String> map5 = new HashMap<>();
        map5.put("clusterId", "test2");
        map5.put("topicNum", "5");

        Map<String, String> map22 = new HashMap<>();
        map22.put("clusterId", "test3");
        map22.put("topicNum", "2");

        Map<String, String> map6 = new HashMap<>();
        map6.put("clusterId", "test4");
        map6.put("topicNum", "4");

        list1.add(map4);
        list.add(map5);
        list.add(map22);
        list.add(map6);

        map1 = new HashMap<>();
        map1.put("clusterId", "test1");
        map1.put("topicPartitionNum", "12");

        map2 = new HashMap<>();
        map2.put("clusterId", "test2");
        map2.put("topicPartitionNum", "22");


        map3 = new HashMap<>();
        map3.put("clusterId", "test3");
        map3.put("topicPartitionNum", "32");

        list2.add(map1);
        list2.add(map2);
        list2.add(map3);

        // Map dd = Stream.of(list, list1).flatMap(Collection::stream).collect(Collectors.groupingBy(map->map.get("clusterId")));
        List<Map<String, String>> dd =   Stream.of(list, list1,list2).flatMap(Collection::stream).collect(Collectors.groupingBy(map->map.get("clusterId"))).values().stream().map(lists->
            lists.stream().flatMap(m -> m.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a))

        ).collect(Collectors.toList());

        System.out.println(dd);

    }


    @Data
    @AllArgsConstructor
    public class Data1 {
        private int id;
        private String name;
        private int amount;
    }

    @Data
    @AllArgsConstructor
    public class Data2 {
        private int id;
        private String name;
        private String type;
    }

    @Data
    @AllArgsConstructor
    public class OutputData {
        private int id;
        private String name;
        private String type;
        private int amount;
    }


    @Test
    public void intersectByKeyTest(){
        List<Data2> listOfData2 = new ArrayList<Data2>();

        listOfData2.add(new Data2(10501, "JOE"  , "Type1"));
        listOfData2.add(new Data2(10603, "SAL"  , "Type5"));
        listOfData2.add(new Data2(40514, "PETER", "Type4"));
        listOfData2.add(new Data2(59562, "JIM"  , "Type2"));
        listOfData2.add(new Data2(29415, "BOB"  , "Type1"));
        listOfData2.add(new Data2(61812, "JOE"  , "Type9"));
        listOfData2.add(new Data2(98432, "JOE"  , "Type7"));
        listOfData2.add(new Data2(62556, "JEFF" , "Type1"));
        listOfData2.add(new Data2(10599, "TOM"  , "Type4"));


        List<Data1> listOfData1 = new ArrayList<Data1>();

        listOfData1.add(new Data1(10501, "JOE"    ,3000000));
        listOfData1.add(new Data1(10603, "SAL"    ,6225000));
        listOfData1.add(new Data1(40514, "PETER"  ,2005000));
        listOfData1.add(new Data1(59562, "JIM"    ,3000000));
        listOfData1.add(new Data1(29415, "BOB"    ,3000000));

        List<OutputData> result = listOfData1.stream()
            .flatMap(x -> listOfData2.stream()
                .filter(y -> x.getId() == y.getId())
                .map(y -> new OutputData(y.getId(), x.getName(), y.getType(), x.getAmount())))
            .collect(Collectors.toList());
        System.out.println(result);

        /*difference by key*/
        List<Data1> data1IntersectResult = listOfData1.stream().filter(data1 -> listOfData2.stream().map(Data2::getId).collect(Collectors.toList()).contains(data1.getId())).collect(Collectors.toList());
        System.out.println(data1IntersectResult);
    }

    @AllArgsConstructor
    @Data
    public class Person {
        private Long id;
        private String name;

        // 重写Person对象的equals()方法和hashCode()方法:

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Person person = (Person)o;

            if (!id.equals(person.id)) {
                return false;
            }
            return name.equals(person.name);

        }

        @Override
        public int hashCode() {
            int result = id.hashCode();
            result = 31 * result + name.hashCode();
            return result;
        }

    }

}
