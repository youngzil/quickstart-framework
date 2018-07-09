/**
 * 项目名称：quickstart-yaml 
 * 文件名：ExampleBean.java
 * 版本信息：
 * 日期：2017年11月22日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.yaml.snakeyaml;

import java.util.List;
import java.util.Map;

/**
 * ExampleBean
 * 
 * @author：yangzl@asiainfo.com
 * @2017年11月22日 下午12:40:52
 * @since 1.0
 */
public class ExampleBean {

    private Integer age;
    private String name;
    private Map<String, Object> params;
    private List<String> favoriteBooks;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public List<String> getFavoriteBooks() {
        return favoriteBooks;
    }

    public void setFavoriteBooks(List<String> favoriteBooks) {
        this.favoriteBooks = favoriteBooks;
    }

    @Override
    public String toString() {
        return "Me{" + "age=" + age + ", name='" + name + '\'' + ", params=" + params + ", favoriteBooks=" + favoriteBooks + '}';
    }

}
