/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Iterator.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.iterator.example;

/**
 * Iterator
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午9:13:11
 * @since 1.0
 */
public interface Iterator {
    // 前移
    public Object previous();

    // 后移
    public Object next();

    public boolean hasNext();

    // 取得第一个元素
    public Object first();
}
