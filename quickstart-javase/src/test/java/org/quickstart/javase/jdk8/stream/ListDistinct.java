package org.quickstart.javase.jdk8.stream;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

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
    List<ServiceGroup> distinctClass2 = serviceGroupList.stream().collect(Collectors.collectingAndThen(
        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getId() + "-" + o.getApiVersion()))), ArrayList::new));

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

  @AllArgsConstructor
  @Data
  public class Person {
    private Long id;
    private String name;

    // 重写Person对象的equals()方法和hashCode()方法:

    @Override
    public boolean equals(Object o) {
      if (this == o)
        return true;
      if (o == null || getClass() != o.getClass())
        return false;

      Person person = (Person) o;

      if (!id.equals(person.id))
        return false;
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
