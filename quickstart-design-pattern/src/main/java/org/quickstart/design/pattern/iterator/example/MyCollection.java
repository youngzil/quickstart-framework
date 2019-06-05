/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：MyCollection.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.iterator.example;

/**
 * MyCollection
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午9:13:30
 * @since 1.0
 */
public class MyCollection implements Collection {

    public String string[] = {"A", "B", "C", "D", "E"};

    @Override
    public Iterator iterator() {
        return new MyIterator(this);
    }

    @Override
    public Object get(int i) {
        return string[i];
    }

    @Override
    public int size() {
        return string.length;
    }

    @Override
    public void remove() {
        // TODO Auto-generated method stub

    }
}
