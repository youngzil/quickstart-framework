/**
 * 项目名称：quickstart-javase 
 * 文件名：StreamApiTest.java
 * 版本信息：
 * 日期：2018年3月28日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk8.stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * StreamApiTest
 * 
 * http://www.importnew.com/14841.html
 * 
 * @author：yangzl@asiainfo.com
 * @2018年3月28日 下午8:06:14
 * @since 1.0
 */
public class StreamApiTest {

    List<Article> articles = new ArrayList<>();

    @BeforeClass
    public static void setUpBeforeClass() {
        System.out.println("Set up before class");
    }

    @Test
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
        return articles.stream().collect(Collectors.groupingBy(Article::getAuthor));
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

}
