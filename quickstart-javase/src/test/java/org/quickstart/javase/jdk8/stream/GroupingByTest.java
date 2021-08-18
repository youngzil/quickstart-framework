package org.quickstart.javase.jdk8.stream;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-26 21:53
 */
public class GroupingByTest {

  @Test
  public void testGroupAndCount() {

    // 3 apple, 2 banana, others 1
    List<String> items = Arrays.asList("apple", "apple", "banana", "apple", "orange", "banana", "papaya");
    Map<String, Long> result = items.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    System.out.println(result);

  }

  @Test
  public void testGroupAndCountAndSorted() {

    // 3 apple, 2 banana, others 1
    List<String> items = Arrays.asList("apple", "apple", "banana", "apple", "orange", "banana", "papaya");
    Map<String, Long> result = items.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    Map<String, Long> finalMap = new LinkedHashMap<>();
    // Sort a map and add to finalMap
    result.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed())
        .forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));
    System.out.println(finalMap);

  }

  @Test
  public void testGroupAndCount2() {
    // 3 apple, 2 banana, others 1
    List<Item> items = Arrays.asList(//
        new Item("apple", 10, new BigDecimal("9.99")), //
        new Item("banana", 20, new BigDecimal("19.99")), //
        new Item("orang", 10, new BigDecimal("29.99")), //
        new Item("watermelon", 10, new BigDecimal("29.99")), //
        new Item("papaya", 20, new BigDecimal("9.99")), //
        new Item("apple", 10, new BigDecimal("9.99")), //
        new Item("banana", 10, new BigDecimal("19.99")), //
        new Item("apple", 20, new BigDecimal("9.99")));
    Map<String, Long> counting = items.stream().collect(Collectors.groupingBy(Item::getName, Collectors.counting()));
    System.out.println(counting);
    Map<String, Integer> sum = items.stream().collect(Collectors.groupingBy(Item::getName, Collectors.summingInt(Item::getQty)));
    System.out.println(sum);

  }

  @Test
  public void testGroupAndCount3() {
    // 3 apple, 2 banana, others 1
    List<Item> items = Arrays.asList(//
        new Item("apple", 10, new BigDecimal("9.99")), //
        new Item("banana", 20, new BigDecimal("19.99")), //
        new Item("orang", 10, new BigDecimal("29.99")), //
        new Item("watermelon", 10, new BigDecimal("29.99")), //
        new Item("papaya", 20, new BigDecimal("9.99")), //
        new Item("apple2", 10, new BigDecimal("9.99")), //
        new Item("banana2", 10, new BigDecimal("19.99")), //
        new Item("apple3", 20, new BigDecimal("9.99")));

    // group by price
    Map<BigDecimal, List<Item>> groupByPriceMap = items.stream().collect(Collectors.groupingBy(Item::getPrice));
    System.out.println(groupByPriceMap);
    // group by price, uses 'mapping' to convert List<Item> to Set<String>
    Map<BigDecimal, Set<String>> result =
        items.stream().collect(Collectors.groupingBy(Item::getPrice, Collectors.mapping(Item::getName, Collectors.toSet())));
    System.out.println(result);

  }

  @Test
  public void testGroupBy() {

    List<Buss> bussList = new ArrayList<>();
    bussList.add(new Buss("a", 10, 0.3));
    bussList.add(new Buss("b", 3, 0.8));
    bussList.add(new Buss("c", 5, 2.0));
    bussList.add(new Buss("b", 30, 3.2));
    bussList.add(new Buss("c", 20, 0.1));

    List<Buss> st = new ArrayList<>();
    bussList.stream().collect(Collectors.groupingBy(Buss::getName)) // 分组(Name can't be null)
        .forEach((k, v) -> {
          Optional<Buss> sum = v.stream().reduce((v1, v2) -> { // 合并
            v1.setCount(v1.getCount() + v2.getCount());
            v1.setValue(v1.getValue() + v2.getValue());
            return v1;
          });
          st.add(sum.orElse(new Buss("other", 0, 0.0)));

        });
    System.out.println(st);

  }

  class Buss {

    private String name;
    private int count;
    private double value;

    public Buss(String name, int count, double value) {
      this.name = name;
      this.count = count;
      this.value = value;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getCount() {
      return count;
    }

    public void setCount(int count) {
      this.count = count;
    }

    public double getValue() {
      return value;
    }

    public void setValue(double value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return "Buss{" + "name='" + name + '\'' + ", count=" + count + ", value=" + value + '}';
    }
  }

  @Data
  @AllArgsConstructor
  public class Item {
    private String name;
    private int qty;
    private BigDecimal price;
    // constructors, getter/setters

  }

}
