/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Iterator.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.iterator.extrinsic;

/**
 * Iterator
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月27日 上午9:01:02
 * @since 1.0
 */
public interface Iterator {
    /**
     * 迭代方法：移动到第一个元素
     */
    public void first();

    /**
     * 迭代方法：移动到下一个元素
     */
    public void next();

    /**
     * 迭代方法：是否为最后一个元素
     */
    public boolean isDone();

    /**
     * 迭代方法：返还当前元素
     */
    public Object currentItem();
}
