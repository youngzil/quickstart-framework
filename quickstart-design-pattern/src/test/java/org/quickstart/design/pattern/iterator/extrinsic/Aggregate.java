/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Aggregate.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.iterator.extrinsic;

/**
 * Aggregate
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午8:58:04
 * @since 1.0
 */
public abstract class Aggregate {
    /**
     * 工厂方法，创建相应迭代子对象的接口
     */
    public abstract Iterator createIterator();
}
