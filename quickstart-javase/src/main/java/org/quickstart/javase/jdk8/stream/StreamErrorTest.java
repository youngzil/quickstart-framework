package org.quickstart.javase.jdk8.stream;

import org.jooq.lambda.Unchecked;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toCollection;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2020/4/23 22:43
 */
public class StreamErrorTest {

    @Test
    public void test1() throws IOException {

//        意外重用流,流已被操作或关闭,因此在使用流时要小心。只能做一次

        IntStream stream = IntStream.of(1, 2);
        stream.forEach(System.out::println);

        // That was fun! Let's do it again!
        stream.forEach(System.out::println);

    }

    @Test
    public void test2() throws IOException {
//        意外创建“无限”流,您无需注意即可轻松创建无限流。
//        如果您将流设计为无限，则流的全部要点是它们可以是无限的事实。唯一的问题是，您可能不需要这样做。因此，请确保始终设置适当的限制：
        // Will run indefinitely
        IntStream.iterate(0, i -> i + 1)
            .forEach(System.out::println);

        // That's better
        IntStream.iterate(0, i -> i + 1)
            .limit(10)
            .forEach(System.out::println);
    }

    @Test
    public void test3() throws IOException {

//        意外地创建“微妙”的无限流

//        我们产生交替的0和1
//        那么我们只保留不同的值，即单个0和单个1
//            那么我们将流的大小限制为10
//        然后我们消耗它
//        好吧……该distinct()操作不知道提供给该iterate()方法的函数只会产生两个不同的值。它可能会期望更多。因此它将永远消耗流中的新值，并且limit(10)将永远无法实现。不幸的是，您的应用程序停顿了。

        IntStream.iterate(0, i -> ( i + 1 ) % 2)
            .distinct()
            .limit(10)
            .forEach(System.out::println);

    }

    @Test
    public void test4() throws IOException {

//        意外地创建“微妙”的并行无限流
//        现在，我们已经看到，这种情况将永远发生。但至少在以前，您仅消耗计算机上的一个CPU。现在，您可能会消耗其中的四个，可能会意外地无限消耗流，从而几乎占据整个系统。

        IntStream.iterate(0, i -> ( i + 1 ) % 2)
            .parallel()
            .distinct()
            .limit(10)
            .forEach(System.out::println);
    }


    @Test
    public void test5() throws IOException {

//        混合操作顺序
        IntStream.iterate(0, i -> ( i + 1 ) % 2)
            .limit(10)
            .distinct()
            .forEach(System.out::println);
    }

    @Test
    public void test6() throws IOException {

//        再次混合操作顺序
        IntStream.iterate(0, i -> i + 1)
            .limit(10) // LIMIT
            .skip(5)   // OFFSET
            .forEach(System.out::println);
//        之后不会继续9，因为limit()现在首先应用产生（0 1 2 3 4 5 6 7 8 9）。skip()之后应用，将流减少到（5 6 7 8 9）。不是您可能想要的。
//        提防的LIMIT .. OFFSET与"OFFSET .. LIMIT"陷阱！
    }

    @Test
    public void test7() throws IOException {

//        使用过滤器遍历文件系统

        Files.walk(Paths.get("."))
            .filter(p -> !p.toFile().getName().startsWith("."))
            .forEach(System.out::println);

        Files.walk(Paths.get("."))
            .filter(p -> !p.toString().contains(File.separator + "."))
            .forEach(System.out::println);

    }


    @Test
    public void test8() throws IOException {

//        修改流的后备集合
        // Of course, we create this list using streams:
        List<Integer> list =
            IntStream.range(0, 10)
                .boxed()
                .collect(toCollection(ArrayList::new));

//        假设我们要在使用每个元素时将其删除：
        list.stream()
            // remove(Object), not remove(int)!
            .peek(list::remove)
            .forEach(System.out::println);
//        检查当我们告诉流产生sorted()结果时会发生什么：
        list.stream()
            .sorted()
            .peek(list::remove)
            .forEach(System.out::println);

        list.stream()
            .sorted()
            .parallel()
            .peek(list::remove)
            .forEach(System.out::println);
    }

    @Test
    public void test9() throws IOException {

//        忘记实际消耗流
//        您可能会认为它将打印（1 2 3 4 5），然后引发异常。但这是不正确的。它什么也不会做。流只是坐在那里，从来没有被消耗过。
        IntStream.range(1, 5)
            .peek(System.out::println)
            .peek(i -> {
                if (i == 5)
                    throw new RuntimeException("bang");
            });


    }

    @Test
    public void test10() throws IOException {

//        并行流死锁
        Object[] locks = { new Object(), new Object() };

        IntStream
            .range(1, 5)
            .parallel()
            .peek(Unchecked.intConsumer(i -> {
                synchronized (locks[i % locks.length]) {
                    Thread.sleep(100);

                    synchronized (locks[(i + 1) % locks.length]) {
                        Thread.sleep(50);
                    }
                }
            }))
            .forEach(System.out::println);
    }

    @Test
    public void test11() throws IOException {

    }
}
