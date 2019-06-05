/**
 * 项目名称：quickstart-concurrentlinkedhashmap 
 * 文件名：CopiedIterator.java
 * 版本信息：
 * 日期：2018年5月21日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.concurrentlinkedhashmap;

/**
 * CopiedIterator 
 *  
 * @author：youngzil@163.com
 * @2018年5月21日 下午11:39:46 
 * @since 1.0
 */
import java.util.Iterator;
import java.util.LinkedList;

public class CopiedIterator<E> implements Iterator<E> {

    private Iterator<E> iterator = null;

    public CopiedIterator(Iterator<E> itr) {
        LinkedList<E> list = new LinkedList<E>();
        while (itr.hasNext()) {
            list.add(itr.next());
        }
        this.iterator = list.iterator();
    }

    /**
     * @see java.util.Iterator#hasNext()
     */
    @Override
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    /**
     * @see java.util.Iterator#next()
     */
    @Override
    public E next() {
        return this.iterator.next();
    }

    /**
     * @see java.util.Iterator#remove()
     */
    @Override
    public void remove() {}
}
