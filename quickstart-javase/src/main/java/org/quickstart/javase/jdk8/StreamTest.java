/**
 * 项目名称：quickstart-javase 
 * 文件名：StreamTest.java
 * 版本信息：
 * 日期：2018年8月31日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.junit.Test;

/**
 * StreamTest
 * 
 * https://www.jianshu.com/p/db089574a6ba
 * 
 * @author：youngzil@163.com
 * @2018年8月31日 下午1:44:30
 * @since 1.0
 */
public class StreamTest {

    // Stream是Java8的重要特性之一。那么生成Stream的方式有哪些？
    // 1， 通过Collections类，
    // 2，通过静态方法，例如IntStream.range，Files.walk方法等，
    // 3，借助Java.util.Spliterator，
    // 4，Stream的静态方法 of 可以将数组转换为Stream
    @Test
    public void testGet() {
        // 1， 通过Collections类，
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc4");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        stringCollection.stream().filter((s) -> s.startsWith("a")).forEach(System.out::println);

    }

    @Test
    public void testGet2() {
        // 2，通过静态方法，例如IntStream.range，Files.walk方法等，
        final Path path = Paths.get("C:\\Afolder");
        int maxDepth = 5;
        try (Stream<Path> entries = Files.walk(path, maxDepth)) {
            entries.onClose(() -> System.out.println("Done!")).forEach(p -> {
                System.out.println(p.toFile().getAbsolutePath());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testGet3() {
        // 3，借助Java.util.Spliterator，
        ArrayList<Integer> number_List = new ArrayList<>();
        Spliterator<Integer> mySpliterator, myParallelSpliterator;

        number_List.add(1);
        number_List.add(2);
        number_List.add(3);
        number_List.add(4);
        number_List.add(5);

        mySpliterator = number_List.spliterator();
        myParallelSpliterator = number_List.spliterator();

        System.out.println("Characteristics Of Spliterator are : " + mySpliterator.characteristics());
        System.out.println("estimateSize Of Spliterator is : " + mySpliterator.estimateSize());

        Stream<Integer> intStream = StreamSupport.stream(mySpliterator.trySplit(), false);
        Stream<Integer> intStream2 = StreamSupport.stream(mySpliterator.trySplit(), false);
        Stream<Integer> intStream3 = StreamSupport.stream(mySpliterator.trySplit(), false);
        // Stream<Integer> intStream4 = StreamSupport.stream(mySpliterator.trySplit(), false);
        Stream<Integer> intStream4 = StreamSupport.stream(myParallelSpliterator.trySplit(), true);
        System.out.println("All elements");
        intStream.filter((p) -> (p < 9)).forEach(System.out::println);

        System.out.println("All elements");
        intStream2.forEach(System.out::println);

        System.out.println("All elements");
        intStream3.forEach(System.out::println);

        System.out.println("All elements");
        intStream4.forEach(System.out::println);

    }

    @Test
    public void testGet4() {
        // Java8 创建Stream的几种方法
        // 1、Stream的静态方法 of 可以将数组转换为Stream
        // Stream.of(T... values) 参数为一组数组
        String[] strings = {"java", "js", "angular", "react", "vue"};
        Stream<String> stream1 = Stream.of(strings);
        Stream<String> stream2 = Stream.of("java", "js", "angular", "react", "vue");

        // 2、Arrays的静态方法 stream可以将数组【的一部分】转换为stream
        // Arrays.stream(T[] array) array为一组数组
        Stream<String> stream4 = Arrays.stream(strings);
        stream4.forEach(System.out::print);// abc
        // Arrays.stream(T[] array, int startInclusive, int endExclusive);//array为一组数组，startInclusive为要截取的那部分数组的起始下标，endExclusive为要截取的那部分数组的结束下标+1
        Stream<String> stream5 = Arrays.stream(strings, 0, 3);// java,js,angular

        // 3、Stream的静态方法 empty 可以创建空的stream
        // Stream.empty() 无参数
        Stream<String> stream32 = Stream.empty();
        System.out.println(stream32.count());// 0
        // 4、Stream的的静态方法generate 可以产生一个特定的值的stream
        // Stream.generate(Supplier<T> s) 参数为Supplier函数接口，利用lambda表达式实现该接口，没有任何输入，返回T
        Stream<String> stream6 = Stream.generate(() -> "java");
        stream6.forEach(e -> System.out.println(e));// java

        Arrays.asList(strings).stream().forEach(System.out::println);// abc
        Stream.iterate(0, x -> x + 1).limit(10).forEach(System.out::println);// 0123456789
        Stream.generate(() -> "x").limit(10).forEach(System.out::println);// xxxxxxxxxx

        /**
         * 可以通过Collection 集合系列提供的 stream() 串行流 或 parallelStream() 并行流
         */
        List<String> list2 = new ArrayList<>();
        Stream<String> streamList = list2.stream();

        /**
         * 通过Arrays中的静态方法stream()获取数组流
         */
        String[] uses = new String[10];
        Stream<String> streamArray = Arrays.stream(uses);

        /**
         * 通过Stream类中的静态方法 of()
         */
        Stream<String> streamOf = Stream.of("aa");

        /**
         * 创建无限流
         */
        // 迭代
        Stream<Integer> streamiterate = Stream.iterate(0, (x) -> x + 2);
        streamiterate.limit(10).forEach((x) -> System.out.println(x));

        // 生成
        Stream.generate(() -> Math.random()) // 数据源,可以产生任意的随机数
                .limit(5) // 中间操作,只需要五个随机数
                .forEach(System.out::println); // 中止操作,遍历打印这五个

        // 4.1 迭代方式
        Stream.iterate(0, x -> x + 2).limit(10) // limit就是中间操作
                .forEach(System.out::println);
        // 4.2 生成方式
        Stream.generate(() -> Math.random()).limit(10).forEach(System.out::println);

        // 使用Stream
        Stream.iterate(1, x -> ++x).limit(10).collect(Collectors.toList());

        // 传统的
        List<Integer> list = new ArrayList<>(10);
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }

        // Stream的静态方法 empty 可以创建空的stream
        // Stream.empty() 无参数
        Stream<String> stream = Stream.empty();
        System.out.println(stream.count());// 0

        // Stream的的静态方法generate 可以产生一个特定的值的stream
        // Stream.generate(Supplier<T> s) 参数为Supplier函数接口，利用lambda表达式实现该接口，没有任何输入，返回T
        Stream<String> stream3 = Stream.generate(() -> "java");
        stream3.forEach(e -> System.out.println(e));// java

    }

    // 将一个顺序执行的流转变成一个并发的流只要调用 parallel()方法
    // 并行流就是一个把内容分成多个数据块，并用不不同的线程分别处理每个数据块的流。最后合并每个数据块的计算结果。
    @Test
    public void parallelSum() {
        long n = 10;
        long result = Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(0L, Long::sum);
        System.out.println(result);
    }

    // JAVA8 Stream接口，map操作，filter操作，flatMap操作
    // 去重复
    // Stream<T> distinct();
    // 排序
    // Stream<T> sorted();
    // 根据属性排序
    // Stream<T> sorted(Comparator<? super T> comparator);
    // 对对象的进行操作
    // Stream<T> peek(Consumer<? super T> action);
    // 截断--取先maxSize个对象
    // Stream<T> limit(long maxSize);
    // 截断--忽略前N个对象
    // Stream<T> skip(long n);

    // stream的min，max，findFirst，findAny操作，这4个函数，都是返回的Optional对象，
    // stream的count，anyMatch，allMatch，noneMatch操作
    // https://blog.csdn.net/qq_28410283/article/details/80633292

    // 这个方法，传入一个Predicate的函数接口，关于Predicate函数接口定义，可以查看《JAVA8 Predicate接口》，这个接口传入一个泛型参数T，做完操作之后，返回一个boolean值；filter方法的作用，是对这个boolean做判断，返回true判断之后的对象
    @Test
    public void filter() {
        String[] dd = {"a", "b", "c"};
        Stream<String> stream = Arrays.stream(dd);
        stream.filter(str -> str.equals("a")).forEach(System.out::println);// 返回字符串为a的值

    }

    @Test
    public void map() {
        Integer[] dd = {1, 2, 3};
        Stream<Integer> stream = Arrays.stream(dd);
        stream.map(str -> Integer.toString(str)).forEach(str -> {
            System.out.println(str);// 1 ,2 ,3
            System.out.println(str.getClass());// class java.lang.String
        });

        List<Emp> list = Arrays.asList(new Emp("a"), new Emp("b"), new Emp("c"));
        list.stream().map(emp -> emp.getName()).forEach(str -> {
            System.out.println(str);
        });

    }

    @Test
    public void flatMap() {
        String[] strs = {"aaa", "bbb", "ccc"};
        Arrays.stream(strs).map(str -> str.split("")).forEach(System.out::println);// Ljava.lang.String;@53d8d10a
        Arrays.stream(strs).map(str -> str.split("")).flatMap(Arrays::stream).forEach(System.out::println);// aaabbbccc
        Arrays.stream(strs).map(str -> str.split("")).flatMap(str -> Arrays.stream(str)).forEach(System.out::println);// aaabbbccc
    }

    public static void println(Stream<Emp> stream) {
        stream.forEach(emp -> {
            System.out.println(String.format("名字：%s，年纪：%s，薪水：%s", emp.getName(), emp.getAge(), emp.getSalary()));
        });
    }

    @Test
    public void otheroper() {

        List<Emp> list = new ArrayList<>();
        list.add(new Emp("xiaoHong1", 20, 1000.0));
        list.add(new Emp("xiaoHong2", 25, 2000.0));
        list.add(new Emp("xiaoHong3", 30, 3000.0));
        list.add(new Emp("xiaoHong4", 35, 4000.0));
        list.add(new Emp("xiaoHong5", 38, 5000.0));
        list.add(new Emp("xiaoHong6", 45, 9000.0));
        list.add(new Emp("xiaoHong7", 55, 10000.0));
        list.add(new Emp("xiaoHong8", 42, 15000.0));

        // 对数组流，先过滤重复，在排序，再控制台输出 1，2，3
        Arrays.asList(3, 1, 2, 1).stream().distinct().sorted().forEach(str -> {
            System.out.println(str);
        });
        // 对list里的emp对象，取出薪水，并对薪水进行排序，然后输出薪水的内容，map操作，改变了Strenm的泛型对象
        list.stream().map(emp -> emp.getSalary()).sorted().forEach(salary -> {
            System.out.println(salary);
        });
        // 根据emp的属性name，进行排序
        println(list.stream().sorted(Comparator.comparing(Emp::getName)));

        // 给年纪大于30岁的人，薪水提升1.5倍，并输出结果
        Stream<Emp> stream = list.stream().filter(emp -> {
            return emp.getAge() > 30;
        }).peek(emp -> {
            emp.setSalary(emp.getSalary() * 1.5);
        });
        println(stream);
        // 数字从1开始迭代（无限流），下一个数字，是上个数字+1，忽略前5个 ，并且只取10个数字
        // 原本1-无限，忽略前5个，就是1-5数字，不要，从6开始，截取10个，就是6-15
        Stream.iterate(1, x -> ++x).skip(5).limit(10).forEach(System.out::println);
    }

    @Test
    public void forEach() {
        // 使用的是stream的流，这个是一个串行流，也就是程序是串行执行的，所有看到遍历的结果都是按照集合的元素放入的顺序；
        List<String> strs = Arrays.asList("a", "b", "c");
        strs.stream().forEachOrdered(System.out::print);// abc
        System.out.println();
        strs.stream().forEach(System.out::print);// abc
        System.out.println();
        strs.parallelStream().forEachOrdered(System.out::print);// abc
        System.out.println();
        // forEachOrdered表示严格按照顺序取数据，forEach在并行中，随机排列了
        strs.parallelStream().forEach(System.out::print);// bca

    }

    @Test
    public void arrays() {

        List<String> strs = Arrays.asList("a", "b", "c");
        String[] dd = strs.stream().toArray(str -> new String[strs.size()]);
        String[] dd1 = strs.stream().toArray(String[]::new);
        Object[] obj = strs.stream().toArray();

        String[] dd2 = strs.toArray(new String[strs.size()]);
        Object[] obj1 = strs.toArray();
        // 可以看到，前三个，是调用的stream的toArray的函数，以及一些用法，后面的两个，是直接调用的List接口的toArray函数，List接口里的，只是顺带提了一下，用法就是这样，请大家自己get吧
    }

    // stream的min，max，findFirst，findAny操作，这4个函数，都是返回的Optional对象，
    // 通过函数的定义，我们其实，已经可以看到，这4个函数的作用了，min和max传入的是一个Comparator这个是一个对比接口，那么返回就是根据比较的结果，取到的集合里面，最大的值，和最小的值；
    // findFirst和findAny，通过名字，就可以看到，对这个集合的流，做一系列的中间操作后，可以调用findFirst，返回集合的第一个对象，
    // findAny返回这个集合中，取到的任何一个对象；
    // 通过这样的描述，我们也可以知道，在串行的流中，findAny和findFirst返回的，都是第一个对象；
    // 而在并行的流中，findAny返回的是最快处理完的那个线程的数据，所以说，在并行操作中，对数据没有顺序上的要求，那么findAny的效率会比findFirst要快的

    @Test
    public void minMaxFind() {
        // 可以看到在min和max中，两种不同的写法，Function.identity()，表示当前对象String本身，如果集合里，是其他的实体类对象，那么可以根据实体类对象里的具体成员进行对象
        // 后面的findFirst和findAny也都在串行和并行中，都有解释；最后Optional的get方法，是取到这个对象，这里的是String，取出来的就是字符串
        List<String> strs = Arrays.asList("d", "b", "a", "c", "a");
        Optional<String> min = strs.stream().min(Comparator.comparing(Function.identity()));
        Optional<String> max = strs.stream().max((o1, o2) -> o1.compareTo(o2));
        System.out.println(String.format("min:%s; max:%s", min.get(), max.get()));// min:a; max:d

        Optional<String> aa = strs.stream().filter(str -> !str.equals("a")).findFirst();
        Optional<String> bb = strs.stream().filter(str -> !str.equals("a")).findAny();

        Optional<String> aa1 = strs.parallelStream().filter(str -> !str.equals("a")).findFirst();
        Optional<String> bb1 = strs.parallelStream().filter(str -> !str.equals("a")).findAny();

        System.out.println(aa.get() + "===" + bb.get());// d===d
        System.out.println(aa1.get() + "===" + bb1.get());// d===b or d===c

    }

    // stream的count，anyMatch，allMatch，noneMatch操作
    // count方法，跟List接口的size一样，返回的都是这个集合流的元素的长度，不同的是，流是集合的一个高级工厂，中间操作是工厂里的每一道工序，我们对这个流操作完成后，可以进行元素的数量的和；
    // 剩下的三个方法，传入的都是Predicate的函数式接口，
    // anyMatch表示，判断的条件里，任意一个元素成功，返回true
    // allMatch表示，判断条件里的元素，所有的都是，返回true
    // noneMatch跟allMatch相反，判断条件里的元素，所有的都不是，返回true

    // 通过例子可以看到，变量aa的表达式，strs里的元素，任意有“a”，表示true
    // 变量bb的表达式，strs里的元素，全部为“a”，表示true，否则false
    // 变量cc的表达式，strs里的元素，全部不为“a”，表示true，否则false

    @Test
    public void countMatch() {
        List<String> strs = Arrays.asList("a", "a", "a", "a", "b");
        boolean aa = strs.stream().anyMatch(str -> str.equals("a"));
        boolean bb = strs.stream().allMatch(str -> str.equals("a"));
        boolean cc = strs.stream().noneMatch(str -> str.equals("a"));
        long count = strs.stream().filter(str -> str.equals("a")).count();
        System.out.println(aa);// TRUE
        System.out.println(bb);// FALSE
        System.out.println(cc);// FALSE
        System.out.println(count);// 4
    }

    public static class Emp {

        private String name;

        private Integer age;

        private Double salary;

        public Emp() {
            super();
        }

        public Emp(String name) {
            super();
            this.name = name;
        }

        public Emp(String name, Integer age, Double salary) {
            super();
            this.name = name;
            this.age = age;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Double getSalary() {
            return salary;
        }

        public void setSalary(Double salary) {
            this.salary = salary;
        }

    }

}
