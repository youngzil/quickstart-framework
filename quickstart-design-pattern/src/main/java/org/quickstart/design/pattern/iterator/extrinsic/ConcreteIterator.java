/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：ConcreteIterator.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.iterator.extrinsic;

/**
 * ConcreteIterator
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午9:01:30
 * @since 1.0
 */
public class ConcreteIterator implements Iterator {
    // 持有被迭代的具体的聚合对象
    private ConcreteAggregate agg;
    // 内部索引，记录当前迭代到的索引位置
    private int index = 0;
    // 记录当前聚集对象的大小
    private int size = 0;

    public ConcreteIterator(ConcreteAggregate agg) {
        this.agg = agg;
        this.size = agg.size();
        index = 0;
    }

    /**
     * 迭代方法：返还当前元素
     */
    @Override
    public Object currentItem() {
        return agg.getElement(index);
    }

    /**
     * 迭代方法：移动到第一个元素
     */
    @Override
    public void first() {

        index = 0;
    }

    /**
     * 迭代方法：是否为最后一个元素
     */
    @Override
    public boolean isDone() {
        return (index >= size);
    }

    /**
     * 迭代方法：移动到下一个元素
     */
    @Override
    public void next() {

        if (index < size) {
            index++;
        }
    }

}
