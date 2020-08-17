package org.quickstart.javase.jdk8.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/17 23:00
 * @version v1.0
 */
public class StreamOperationTest {

    @Test
    public void testOperation() {

        //  Stream中间操作：filter()、distinct()、skip()、limit()、map()、flatMap()、sorted()、peek()）

        Stream.of("1", "2", "3").filter(x -> x.equals("2")).forEach(System.out::println);    // 2

        Stream.of("1", "3", "3").distinct().forEach(System.out::println);    // 1 3

        Stream.of("1", "2", "3").skip(1).forEach(System.out::println);    // 2 3

        Stream.of("1", "2", "3").limit(1).forEach(System.out::println);    // 1

        List<String> strings = new ArrayList<String>() {{
            add("1");
            add("2");
            add("3");
        }};
        strings.stream().map(x -> "map" + x).forEach(System.out::println);    // map1 map2 map3
        strings.stream().forEach(System.out::println);    // 1 2 3

        Stream.of("1,2", "3,4", "5,6").flatMap(x -> Stream.of(x.split(",")))
            .forEach(System.out::println);    // 1 2 3 4 5 6

        Stream.of("3", "1", "2").sorted().forEach(System.out::println);    // 1 2 3

        Stream.of("a", "b", "c").peek(x -> System.out.println(x.toUpperCase())).forEach(System.out::println);    // A a B b C c

    }
}
