/**
 * 项目名称：quickstart-javase 文件名：StreamApiTest.java 版本信息： 日期：2018年3月28日 Copyright yangzl Corporation 2018 版权所有 *
 */
package org.quickstart.javase.jdk8.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * StreamApiTest
 * 
 * http://www.importnew.com/14841.html
 * 
 * @author：youngzil@163.com
 * @2018年3月28日 下午8:06:14
 * @since 1.0
 */
public class StreamApiTest {

  List<Article> articles = new ArrayList<>();

  static List<Transaction> transactions = new ArrayList<>();

  @BeforeClass
  public static void setUpBeforeClass() {
    System.out.println("Set up before class");

    // ----------------------init-----------------------
    transactions = Arrays.asList(//
        new Transaction(1, new Currency(1, "人民币"), 22.22), //
        new Transaction(2, new Currency(2, "美元"), 30.21), //
        new Transaction(3, new Currency(2, "美元"), 29.22), //
        new Transaction(4, new Currency(2, "美元"), 18.22), //
        new Transaction(5, new Currency(1, "人民币"), 15.5));

  }

  public Article getFirstJavaArticle() {

    for (Article article : articles) {
      if (article.getTags().contains("Java")) {
        return article;
      }
    }

    return null;
  }

  // 在集合中查找包含“Java”标签的第一篇文章
  // 我们首先使用 filter 操作去找到所有包含Java标签的文章，然后使用 findFirst() 操作去获取第一次出现的文章。因为Stream是“延迟计算”（lazy）的并且filter返回一个流对象，所以这个方法仅在找到第一个匹配元素时才会处理元素。
  public Optional<Article> getFirstJavaArticle2() {
    return articles.stream().filter(article -> article.getTags().contains("Java")).findFirst();
  }

  public List<Article> getAllJavaArticles() {

    List<Article> result = new ArrayList<>();

    for (Article article : articles) {
      if (article.getTags().contains("Java")) {
        result.add(article);
      }
    }

    return result;
  }

  // 使用 collection 操作在返回流上执行少量代码而不是手动声明一个集合并显式地添加匹配的文章到集合里。
  public List<Article> getAllJavaArticles2() {
    return articles.stream().filter(article -> article.getTags().contains("Java")).collect(Collectors.toList());
  }

  // 分组
  public Map<String, List<Article>> groupByAuthor() {

    Map<String, List<Article>> result = new HashMap<>();

    for (Article article : articles) {
      if (result.containsKey(article.getAuthor())) {
        result.get(article.getAuthor()).add(article);
      } else {
        ArrayList<Article> articles = new ArrayList<>();
        articles.add(article);
        result.put(article.getAuthor(), articles);
      }
    }

    return result;
  }

  public Map<String, List<Article>> groupByAuthor2() {

    Map<String, List<Article>> map =
        articles.stream().filter(article -> article.getTags().contains("Javafffff")).collect(Collectors.groupingBy(Article::getAuthor));

    return map;
  }

  // 查找集合中所有不同的标签。
  public Set<String> getDistinctTags() {

    Set<String> result = new HashSet<>();

    for (Article article : articles) {
      result.addAll(article.getTags());
    }

    return result;
  }

  public Set<String> getDistinctTags2() {
    return articles.stream().flatMap(article -> article.getTags().stream()).collect(Collectors.toSet());
  }

  // 创建Stream流
  @Test
  public void testCreateStream() throws IOException {

    // 1、of方法创建
    Stream<Integer> integerStream = Stream.of(1, 2, 3, 5);
    Stream<String> stringStream = Stream.of("taobao");

    // 2. generator方法
    Stream.generate(new Supplier<Double>() {
      @Override
      public Double get() {
        return Math.random();
      }
    });
    Stream.generate(() -> Math.random());
    Stream.generate(Math::random);

    // 3. iterate方法
    Stream.iterate(1, item -> item + 1).limit(10).forEach(System.out::println);

    // 4、通过Collection子类获取Stream
    long count = Arrays.asList(1, 3, 4, 6).stream().filter(num -> num != null).count();
    System.out.println("count=" + count);

    // Lists是Guava中的一个工具类
    List<Integer> nums = Lists.newArrayList(1, null, 3, 4, null, 6);
    nums.stream().filter(num -> num != null).count();

    // 数组创建流
    // 根据参数的数组类型创建对应的流：
    // Arrays.stream(T[ ])
    // Arrays.stream(int[ ])
    // Arrays.stream(double[ ])
    // Arrays.stream(long[ ])
    int[] a = {1, 2, 3, 4};
    Arrays.stream(a, 1, 3).forEach(System.out::println);

    // 文件生成流， 每个元素是给定文件的其中一行
    Stream<String> stream = Files.lines(Paths.get("data.txt"));

  }

  // 转换Stream
  @Test
  public void testTransformStream() throws InterruptedException {

    // Lists是Guava中的一个工具类
    List<Integer> nums = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);

    nums.stream().filter(num -> num != null).distinct().forEach(System.out::println);
    System.out.println("==============");

    nums.stream().filter(num -> num != null).distinct().mapToInt(num -> num * 2).forEach(System.out::println);

    System.out.println("==============");

    int sum = nums.stream().filter(num -> num != null).distinct().mapToInt(num -> num * 2).peek(System.out::println).skip(2).limit(4).sum();
    System.out.println("sum==============" + sum);

    IntStream ss = nums.stream().filter(num -> num != null).distinct().mapToInt(num -> num * 2).peek(System.out::println).skip(2).limit(4);
    // 上面相当于只是定义，并没有实际执行，只有遇到在汇聚操作的时候循环Stream对应的集合，然后对每个元素执行所有的函数。只会循环一次

    ss.forEach(item -> System.out.println(item));// 流只能执行聚合一次，执行完了就不能再执行了，所以下面报错
    System.out.println("sum is:" + ss.sum());// java.lang.IllegalStateException: stream has already been operated upon or closed

  }

  // 汇聚（Reduce）Stream
  @Test
  public void testReduceStream() {

    // Lists是Guava中的一个工具类
    List<Integer> nums = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
    List<Integer> numsWithoutNull = nums.stream().filter(num -> num != null).collect(() -> //
    new ArrayList<Integer>(), // 第一个函数生成一个新的ArrayList实例；
        (list, item) -> list.add(item), // 第二个函数接受两个参数，第一个是前面生成的ArrayList对象，二个是stream中包含的元素，函数体就是把stream中的元素加入ArrayList对象中。第二个函数被反复调用直到原stream的元素被消费完毕；
        (list1, list2) -> list1.addAll(list2));// 第三个函数也是接受两个参数，这两个都是ArrayList类型的，函数体就是把第二个ArrayList全部加入到第一个中

    // Java8还给我们提供了Collector的工具类–[Collectors]
    // Collectors.toCollection()收集到Collection中, Collectors.toList()收集到List中和Collectors.toSet()收集到Set中
    List<Integer> numsWithoutNull2 = nums.stream().filter(num -> num != null).collect(Collectors.toList());

    // reduce方法接受一个函数，这个函数有两个参数，第一个参数是上次函数执行的返回值（也称为中间结果），第二个参数是stream中的元素，
    // 这个函数把这两个值相加，得到的和会被赋值给下次执行这个函数的第一个参数。
    // 要注意的是：**第一次执行的时候第一个参数的值是Stream的第一个元素，第二个参数是Stream的第二个元素**。这个方法返回值类型是Optional，这是Java8防止出现NPE的一种可行方法
    List<Integer> ints = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    System.out.println("ints sum is:" + ints.stream().reduce((sum, item) -> sum + item).get());

    // 这个定义上上面已经介绍过的基本一致，不同的是：它允许用户提供一个循环计算的初始值，如果Stream为空，就直接返回该值。而且这个方法不会返回Optional，因为其不会出现null值。
    System.out.println("ints sum is:" + ints.stream().reduce(0, (sum, item) -> sum + item));

    System.out.println("ints sum is:" + ints.stream().count());

    // allMatch和max的例子
    System.out.println(ints.stream().allMatch(item -> item < 100));
    ints.stream().max((o1, o2) -> o1.compareTo(o2)).ifPresent(System.out::println);

    // 如果 Map 的 Key 重复了，可是会报错的哦
    // Map<Integer, Person> map = transactions.stream().collect(toMap(Person::getAge, p -> p));

  }

  @Test
  public void test1() {
    // 转换成一个新的list
    List<Transaction> filterList = transactions.stream().filter(t -> t.getMoney() > 20).collect(Collectors.toList());
    // 满足条件的循环处理
    transactions.stream().filter(t -> t.getMoney() > 20).forEach(System.out::println);
  }

  @Test
  public void test2() {
    Map<Currency, List<Transaction>> currencyListMap = transactions.stream().collect(Collectors.groupingBy(Transaction::getCurrency));
    Map<Currency, List<Transaction>> currencyListMap1 = transactions.stream().collect(Collectors.groupingBy(t -> t.getCurrency()));

    System.out.println("currencyListMap=" + currencyListMap);
    System.out.println("currencyListMap1=" + currencyListMap1);

  }

}


/**
 * 交易实体类
 */
@Setter
@Getter
@AllArgsConstructor
class Transaction {
  // id
  private Integer id;
  // 货币
  private Currency currency;
  // 金额
  private Double money;
  // -------省略 getter和setter--------
}


/**
 * 货币
 */
@Setter
@Getter
@AllArgsConstructor
class Currency {
  private Integer id;
  private String name;
  // -------省略 getter和setter--------
}
