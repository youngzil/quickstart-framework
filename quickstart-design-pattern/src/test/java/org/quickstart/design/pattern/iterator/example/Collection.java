/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Collection.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.iterator.example;

/**
 * Collection
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午9:12:51
 * @since 1.0
 */
public interface Collection {

    public Iterator iterator();

    /*取得集合元素*/
    public Object get(int i);

    /*取得集合大小*/
    public int size();

    public void remove();
}
