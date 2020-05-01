/**
 * 项目名称：quickstart-javase 
 * 文件名：FunctionTest.java
 * 版本信息：
 * 日期：2018年3月28日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk8.function;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Test;
import org.quickstart.javase.jdk8.stream.Article;

/**
 * FunctionTest
 * 
 * http://www.importnew.com/17209.html
 * 
 * @author：youngzil@163.com
 * @2018年3月28日 下午8:33:30
 * @since 1.0
 */
public class FunctionTest {

    public static void main(String[] args) {
        Function<Integer, Integer> times2 = e -> e * 2;

        Function<Integer, Integer> squared = e -> e * e;

        // compose 和 andThen 的不同之处是函数执行的顺序不同。compose 函数先执行参数，然后执行调用者，
        // 而 andThen 先执行调用者，然后再执行参数。
        Integer i1 = times2.compose(squared).apply(4); // Returns 32

        Integer i2 = times2.andThen(squared).apply(4); // Returns 64

        // 需对文章列表从新到旧进行排序，并返回排序后的文章列表。
        Function<List<Article>, List<Article>> sortByDate = articles -> articles.stream().sorted((x, y) -> y.published().compareTo(x.published())).collect(Collectors.toList());

        Function<List<Article>, Optional<Article>> first = a -> a.stream().findFirst();

        // 组合一个返回最近发表的文章列表函数。
        // 使用 first 这个函数以及我们之前创建的 sortByDate，我们能创建一个新的函数，该函数返回给定文章列表的最新文章。
        Function<List<Article>, Optional<Article>> newest = first.compose(sortByDate);

        // 找出作者的最新文章：
        // BiFunction<String, List<Article>, Optional<Article>> newestByAuthor = byAuthor.andThen(newest);

        // 对某一作者的文章进行排序
        // BiFunction<String, List<Article>, List<Article>> byAuthorSorted = byAuthor.andThen(sortByDate);

        // 可能不关心作者，只想根据你喜欢标签获取最新的文章：
        // BiFunction<String, List<Article>, Optional<Article>> newestByTag = byTag.andThen(newest);

    }
    
//    该接口目前发布在 java.util.function 包中。接口中主要有方法：
//    R apply(T t);
//     将Function对象应用到输入的参数上，然后返回计算结果。

    @Test
    public void test() throws InterruptedException {
        String name = "";
        String name1 = "12345";
        System.out.println(validInput(name, inputStr -> inputStr.isEmpty() ? "名字不能为空" : inputStr));
        System.out.println(validInput(name1, inputStr -> inputStr.length() > 3 ? "名字过长" : inputStr));
    }

//    将Function对象应用到输入的参数上，然后返回计算结果。
    public static String validInput(String name, Function<String, String> function) {
//        定义 function.apply(name)，也就是说，传入一个 name 参数，应用某些规则，返回一个结果，至于是什么规则，先不定义。
        return function.apply(name);
    }

}
