package org.quickstart.javase.jdk8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/17 22:59
 * @version v1.0
 */
public class StreamCreateTest {

    List<Student> list = Arrays.asList(new Student(1, 18, "阿龙", GenderColumn.BOY.getCode()),
        new Student(2, 17, "小花", GenderColumn.GIRL.getCode()),
        new Student(3, 17, "阿浪", GenderColumn.LADYBOY.getCode()));

    @Test
    public void testCreate2() {

        //        方式一：通过一个集合创建Stream
        Stream<Student> studentStream = list.stream();
        Stream<Student> studentStream2 = list.parallelStream();

        //        方式二：通过一个数组创建Stream
        int[] arr = new int[] {1, 2, 3, 4, 5, 6, 7};
        IntStream intStream = Arrays.stream(arr);

        //        方式三：通过Stream.of
        Stream<Integer> intStream2 = Stream.of(1, 2, 3, 4, 5, 6, 7);
        Stream<String> strStream = Stream.of("1", "2", "3", "4");

    }

    @Test
    public void testCreate() {

        //        方式四：创建一个无限流

        //        通过Java 8 函数式接口Supplier<T>利用无限流生成3个UUID
        Stream<UUID> generateStream = Stream.generate(UUID::randomUUID);
        Set<UUID> uuids = generateStream.limit(3).collect(Collectors.toSet());

        //        通过Java 8 函数式接口UnaryOperator<T>利用无限流输出3个连续数字
        Stream<Integer> iterateStream = Stream.iterate(0, x -> x + 1);
        iterateStream.skip(1).limit(3).forEach(System.out::println);

        //产生随机数
        Stream.generate(Math::random).limit(3);

    }

}
