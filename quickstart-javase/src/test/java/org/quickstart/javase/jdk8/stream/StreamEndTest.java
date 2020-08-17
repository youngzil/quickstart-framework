package org.quickstart.javase.jdk8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/17 23:00
 * @version v1.0
 */
public class StreamEndTest {

    @Test
    public void testEnd() {
        // Stream终端操作：forEach()、Match、find()、max、min()、reduce()、collect()、toArray()、count()）

        // （一）遍历:forEach()、forEachOrdered()
        Arrays.asList("1", "2", "3").stream().forEach(System.out::println);    // 1 2 3
        Arrays.asList("1", "2", "3").stream().forEachOrdered(System.out::println);    // 1 2 3
        Arrays.asList("1", "2", "3").parallelStream().forEach(System.out::println);    // 2 3 1
        Arrays.asList("1", "2", "3").parallelStream().forEachOrdered(System.out::println);    // 1 2 3

        // （二）匹配:anyMatch()、noneMatch()、allMatch()
        System.out.println(Stream.of("1", "2", "3").anyMatch(x -> x.equals("2")));    // true
        System.out.println(Stream.of("1", "2", "3").noneMatch(x -> x.equals("4")));    // true
        System.out.println(Stream.of("1", "2", "3").allMatch(x -> x instanceof String));    // true

        //（三）聚合:findAny()、findFirst()、max()、min()
        System.out.println(
            Arrays.asList("1", "2", "3").stream().filter(x -> x instanceof String).findAny().orElse(null));    // 1
        System.out.println(Arrays.asList("1", "2", "3").parallelStream().filter(x -> x instanceof String).findAny()
            .orElse(null));    // 2

        System.out.println(
            Arrays.asList("1", "2", "3").stream().filter(x -> x instanceof String).findFirst().orElse(null));    // 1
        System.out.println(Arrays.asList("1", "2", "3").parallelStream().filter(x -> x instanceof String).findFirst()
            .orElse(null));    // 1

        System.out.println(Arrays.asList("1", "2", "3").stream().max((Comparator.comparing(Function.identity())))
            .orElse(null));    // 3

        System.out.println(Arrays.asList("1", "2", "3").stream().min(String::compareTo).orElse(null));    // 1

        //（四）规约：reduce()、
        System.out.println(Stream.iterate(0, x -> x + 1).limit(10).reduce(Integer::sum).orElse(0));    // 45
        System.out.println(Stream.iterate(0, x -> x + 1).limit(10).reduce(1000, Integer::sum));    // 1045
        System.out.println(Stream.iterate(0, x -> x + 1).limit(10).reduce(1000, Integer::sum, Integer::max));    // 1045

        //（五）收集：collect()、toArray()、count()
        System.out.println(Stream.iterate(0, x -> x + 1).limit(3).collect(Collectors.toList()));    // [0, 1, 2]

        System.out
            .println(Arrays.toString(Stream.iterate(0, x -> x + 1).limit(3).toArray(Integer[]::new)));    // [0, 1, 2]

        System.out.println(Stream.iterate(0, x -> x + 1).limit(3).count());    // 3

    }
}
