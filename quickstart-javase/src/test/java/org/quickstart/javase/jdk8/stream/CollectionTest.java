package org.quickstart.javase.jdk8.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-08-21 09:48
 */
public class CollectionTest {

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<String>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("5");
        list1.add("6");

        List<String> list2 = new ArrayList<String>();
        list2.add("2");
        list2.add("3");
        list2.add("7");
        list2.add("8");

        // 交集
        List<String> intersection = list1.stream().filter(item -> list2.contains(item)).collect(toList());
        System.out.println("---交集 intersection---");
        intersection.parallelStream().forEach(System.out::println);

        // 差集 (list1 - list2)
        List<String> reduce1 = list1.stream().filter(item -> !list2.contains(item)).collect(toList());
        System.out.println("---差集 reduce1 (list1 - list2)---");
        reduce1.parallelStream().forEach(System.out::println);

        // 差集 (list2 - list1)
        List<String> reduce2 = list2.stream().filter(item -> !list1.contains(item)).collect(toList());
        System.out.println("---差集 reduce2 (list2 - list1)---");
        reduce2.parallelStream().forEach(System.out::println);

        // 并集
        List<String> listAll = list1.parallelStream().collect(toList());
        List<String> listAll2 = list2.parallelStream().collect(toList());
        listAll.addAll(listAll2);
        System.out.println("---并集 listAll---");
        listAll.parallelStream().forEachOrdered(System.out::println);

        // 去重并集
        List<String> listAllDistinct = listAll.stream().distinct().collect(toList());
        System.out.println("---得到去重并集 listAllDistinct---");
        listAllDistinct.parallelStream().forEachOrdered(System.out::println);

        System.out.println("---原来的List1---");
        list1.parallelStream().forEachOrdered(System.out::println);
        System.out.println("---原来的List2---");
        list2.parallelStream().forEachOrdered(System.out::println);

    }

    @Test
    public void testCollectToList() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        // 要求：将list中每个数值扩大10倍 结果为： [10, 20, 30]

        //先将list转化为stream类型，map内 等于遍历map方法使所有数据乘以10
        Stream stream = list.stream().map(i -> i * 10);//
        List<Integer> collectOne = (List<Integer>)stream.collect(Collectors.toList());

        //简写可如下，一般使用简写方便快捷
        List<Integer> collectTwo = list.stream().map(i -> i * 10).collect(Collectors.toList());

        System.out.println(collectOne.toString());
        System.out.println(collectTwo.toString());
    }

    @Test
    public void testCollectToSet() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        // 要求：将list中每个数值扩大10倍

        //先将list转化为stream类型，map内 等于遍历map方法使所有数据乘以10
        Stream stream = list.stream().map(i -> i * 10);//
        Set<Integer> collect1 = (Set<Integer>)stream.collect(Collectors.toSet());

        //简写可如下，一般使用简写方便快捷
        Set<Integer> collect2 = list.stream().map(i -> i * 10).collect(Collectors.toSet());

        System.out.println(collect1.toString());
        System.out.println(collect2.toString());
    }

    @Test
    public void testCollectToMap() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        // {key1=value:10, key2=value:20, key3=value:30}  {key1=value:1 , key2=value:2 , key3=value:3 }
        // toMap 可用于将List转为Map，便于通过key快速查找到某个value
        Map<String, String> collect1 = list.stream().map(i -> i * 10)
            .collect(Collectors.toMap(key -> "key" + key / 10, value -> "value:" + value));
        Map<String, String> collect2 =
            list.stream().map(i -> i).collect(Collectors.toMap(key -> "key" + key, value -> "value:" + value));

        //实体list转化map id作为主键，对象作为value
        List<DatabusTask> databusTaskArrayList = new ArrayList<>();
        DatabusTask databusTask = new DatabusTask();
        databusTask.setId(1);
        databusTask.setFile_name("测试");
        databusTaskArrayList.add(databusTask);

        Map<Integer, DatabusTask> taskMap =
            databusTaskArrayList.stream().collect(Collectors.toMap(DatabusTask::getId, entity -> entity));
        System.out.println(collect1.toString());
        System.out.println(collect2.toString());
        System.out.println(taskMap.toString());
    }

    @Test
    public void testCollectToCollection() {
        //直接创建treeSet数据 [1, 3, 4]
        TreeSet<Integer> collect3 = Stream.of(1, 3, 4).collect(Collectors.toCollection(TreeSet::new));
        System.out.println(collect3.toString());
        List<String> list = Arrays.asList("java", "python", "C++", "php", "java");
        //用LinkedList收集
        List<String> linkedListResult = list.stream().collect(Collectors.toCollection(LinkedList::new));
        linkedListResult.forEach(System.out::println);
        System.out.println("--------------");

        //用CopyOnWriteArrayList收集
        List<String> copyOnWriteArrayListResult =
            list.stream().collect(Collectors.toCollection(CopyOnWriteArrayList::new));
        copyOnWriteArrayListResult.forEach(System.out::println);
        System.out.println("--------------");

        //用TreeSet收集
        TreeSet<String> treeSetResult = list.stream().collect(Collectors.toCollection(TreeSet::new));
        treeSetResult.forEach(System.out::println);

    }

    @Test
    public void testCollectJoining() {

        List<String> list = Arrays.asList("java", "python", "C++", "php", "java");
        //直接将输出结果拼接
        System.out.println(list.stream().collect(Collectors.joining()));
        //每个输出结果之间加拼接符号“|”
        System.out.println(list.stream().collect(Collectors.joining(" | ")));
        //输出结果开始头为Start--，结尾为--End，中间用拼接符号“||”
        System.out.println(list.stream().collect(Collectors.joining(" || ", "Start--", "--End")));

        // a,b,c
        List<String> list2 = Arrays.asList("a", "b", "c");
        //此方法等同于直接 String result = Joiner.on(",").join(list2); 将数组转化逗号分隔字符串
        String result = list2.stream().collect(Collectors.joining(","));
        System.out.println(result);

    }

    @Test
    public void testCollectingAndThan() {
        List<String> list2 = Arrays.asList("a", "b", "c");
        // Collectors.joining(",")的结果是：a,b,c  然后再将结果 x + "d"操作, 最终返回a,b,cd
        String str =
            Stream.of("a", "b", "c").collect(Collectors.collectingAndThen(Collectors.joining(","), x -> x + "d"));
        System.out.println(str);

    }

    @Test
    public void testReduce() {
        // 求最值 3
        List<Integer> list = Arrays.asList(1, 2, 3);
        Integer maxValue =
            list.stream().collect(Collectors.collectingAndThen(Collectors.maxBy((a, b) -> a - b), Optional::get));
        Integer maxValue2 = list.stream()
            .collect(Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(a -> a)), Optional::get));

        // 最小值 1
        Integer minValue =
            list.stream().collect(Collectors.collectingAndThen(Collectors.minBy((a, b) -> a - b), Optional::get));
        Integer minValue2 = list.stream()
            .collect(Collectors.collectingAndThen(Collectors.minBy(Comparator.comparingInt(a -> a)), Optional::get));

        // 求和 6
        Integer sumValue = list.stream().collect(Collectors.summingInt(item -> item));
        Integer sumValue2 = list.stream().mapToInt(item -> item).sum();

        // 平均值 2.0
        Double avg = list.stream().collect(Collectors.averagingDouble(x -> x));
    }

    @Test
    public void testMapping() {
        // 映射：先对集合中的元素进行映射，然后再对映射的结果使用Collectors操作
        // A,B,C
        Stream.of("a", "b", "c").collect(Collectors.mapping(x -> x.toUpperCase(), Collectors.joining(",")));
    }

    @Test
    public void testGroupBy() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // 奇偶数分组：奇数分一组，偶数分一组
        // groupingBy(Function<? super T, ? extends K> classifier) 参数是Function类型，Function返回值可以是要分组的条件，也可以是要分组的字段
        // 返回的结果是Map，其中key的数据类型为Function体中计算类型，value是List<T>类型，为分组的结果
        Map<Boolean, List<Integer>> result = list.stream().collect(Collectors.groupingBy(item -> item % 2 == 0));
        // {false=[1, 3, 5, 7, 9], true=[2, 4, 6, 8, 10]}
        System.out.println(result);

        // partitioningBy 用于分成两组的情况
        Map<Boolean, List<Integer>> twoPartiton =
            list.stream().collect(Collectors.partitioningBy(item -> item % 2 == 0));
        System.out.println(twoPartiton);

        User user = new User(1L, "zhangsan", 1);
        User user2 = new User(2L, "lisi", 2);
        User user3 = new User(3L, "wangwu", 3);
        User user4 = new User(4L, "fengliu", 1);
        List<User> users = Arrays.asList(user, user2, user3, user4);
        // 根据某个字段进行分组
        Map<Integer, List<User>> userGroup = users.stream().collect(Collectors.groupingBy(item -> item.type));

        /**
         * key 为要分组的字段
         * value 分组的结果
         * {
         *  1=[User{id=1, username='zhangsan', type=1}, User{id=4, username='fengliu', type=1}],
         *  2=[User{id=2, username='lisi', type=2}],
         *  3=[User{id=3, username='wangwu', type=3}]
         * }
         */
        System.out.println(userGroup);
    }

    @Test
    public void testReducing() {

        // sum: 是每次累计计算的结果，b是Function的结果
        System.out.println(Stream.of(1, 3, 4).collect(Collectors.reducing(0, x -> x + 1, (sum, b) -> {
            System.out.println(sum + "-" + b);
            return sum + b;
        })));

        // 下面代码是对reducing函数功能实现的描述，用于理解reducing的功能
        int sum = 0;
        List<Integer> list3 = Arrays.asList(1, 3, 4);
        for (Integer item : list3) {
            int b = item + 1;
            System.out.println(sum + "-" + b);
            sum = sum + b;
        }
        System.out.println(sum);

        // 注意reducing可以用于更复杂的累计计算，加减乘除或者更复杂的操作
        // result = 2 * 4 * 5 = 40
        System.out.println(Stream.of(1, 3, 4).collect(Collectors.reducing(1, x -> x + 1, (result, b) -> {
            System.out.println(result + "-" + b);
            return result * b;
        })));
    }

    @Test
    public void testExample() {

        //        要求将"a,bb#b#c|#d" #替换逗号，并加上单引号
        String param = "a,bb#b#c|#d";
        //第一种写法
        Optional<String> optional = Stream.of(param.split("#")).reduce((s1, s2) -> s1 + "','" + s2);
        String value = "'" + optional.get() + "'";
        //第二种写法
        List<String> lis = Arrays.asList(param.split("#"));
        String value2 = lis.stream().collect(Collectors.joining("','", "'", "'"));

        System.out.println(value);
        System.out.println(value2);
        //结果一致
    }

    @Data
    class DatabusTask {
        // id
        private Integer id;
        private String file_name;
    }

    @Data
    @AllArgsConstructor
    public class User {
        private Long id;
        private String username;
        private Integer type;
    }
}
