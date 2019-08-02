package org.quickstart.javase.collection.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-08-02 13:16
 */
public class ListTraverseTest {

  public static void main(String[] args) {

    List<String> items = new ArrayList<>();
    items.add("A");
    items.add("B");
    items.add("C");
    items.add("D");
    items.add("E");

    // 2.1 Normal for-loop to loop a List.
    for (String item : items) {
      System.out.println(item);
    }

    // 2.2 In Java 8, you can loop a List with forEach + lambda expression or method reference.

    // lambda
    // Output : A,B,C,D,E
    items.forEach(item -> System.out.println(item));

    // Output : C
    items.forEach(item -> {
      if ("C".equals(item)) {
        System.out.println(item);
      }
    });

    // method reference
    // Output : A,B,C,D,E
    items.forEach(System.out::println);

    // Stream and filter
    // Output : B
    items.stream().filter(s -> s.contains("B")).forEach(System.out::println);

  }
}
