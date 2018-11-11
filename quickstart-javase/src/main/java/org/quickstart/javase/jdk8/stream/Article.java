/**
 * 项目名称：quickstart-javase 
 * 文件名：Article.java
 * 版本信息：
 * 日期：2018年3月28日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk8.stream;

import java.util.List;

/**
 * Article
 * 
 * @author：youngzil@163.com
 * @2018年3月28日 下午8:05:06
 * @since 1.0
 */
public class Article {

    private final String title;
    private final String author;
    public final String published;
    private final List<String> tags;

    private Article(String title, String author, String published, List<String> tags) {
        this.title = title;
        this.author = author;
        this.published = published;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public List<String> getTags() {
        return tags;
    }

    public String published() {
        return published;
    }

}
