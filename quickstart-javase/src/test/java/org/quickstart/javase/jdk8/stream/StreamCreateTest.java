package org.quickstart.javase.jdk8.stream;

import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/17 22:59
 * @version v1.0
 */
public class StreamCreateTest {

    @Test
    public void testCreate() {

//        通过Java 8 函数式接口Supplier<T>利用无限流生成3个UUID
        Stream<UUID> generateStream = Stream.generate(UUID::randomUUID);
        Set<UUID> uuids = generateStream.limit(3).collect(Collectors.toSet());

//        通过Java 8 函数式接口UnaryOperator<T>利用无限流输出3个连续数字
        Stream<Integer> iterateStream = Stream.iterate(0, x -> x + 1);
        iterateStream.skip(1).limit(3).forEach(System.out::println);

    }

}
