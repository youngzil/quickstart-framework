/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：ConcreteAggregate.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.iterator.intrinsic;

import org.quickstart.design.pattern.iterator.extrinsic.Aggregate;
import org.quickstart.design.pattern.iterator.extrinsic.Iterator;

/**
 * ConcreteAggregate
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午9:10:08
 * @since 1.0
 */
public class ConcreteAggregate extends Aggregate {

    private Object[] objArray = null;

    /**
     * 构造方法，传入聚合对象的具体内容
     */
    public ConcreteAggregate(Object[] objArray) {
        this.objArray = objArray;
    }

    @Override
    public Iterator createIterator() {

        return new ConcreteIterator();
    }

    /**
     * 内部成员类，具体迭代子类
     */
    private class ConcreteIterator implements Iterator {
        // 内部索引，记录当前迭代到的索引位置
        private int index = 0;
        // 记录当前聚集对象的大小
        private int size = 0;

        /**
         * 构造函数
         */
        public ConcreteIterator() {

            this.size = objArray.length;
            index = 0;
        }

        /**
         * 迭代方法：返还当前元素
         */
        @Override
        public Object currentItem() {
            return objArray[index];
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
}
