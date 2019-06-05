/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：ConcreteAggregate.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.iterator.extrinsic;

/**
 * ConcreteAggregate
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午8:59:00
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

        return new ConcreteIterator(this);
    }

    /**
     * 取值方法：向外界提供聚集元素
     */
    public Object getElement(int index) {

        if (index < objArray.length) {
            return objArray[index];
        } else {
            return null;
        }
    }

    /**
     * 取值方法：向外界提供聚集的大小
     */
    public int size() {
        return objArray.length;
    }
}
