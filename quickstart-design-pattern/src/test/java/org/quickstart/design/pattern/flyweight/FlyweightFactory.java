/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：FlyweightFactory.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.flyweight;

import java.util.Hashtable;

/**
 * FlyweightFactory
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午11:18:24
 * @since 1.0
 */
public class FlyweightFactory {
    private Hashtable flyweights = new Hashtable();// ----------------------------1

    public FlyweightFactory() {}

    public Flyweight getFlyWeight(Object obj) {
        Flyweight flyweight = (Flyweight) flyweights.get(obj);// ----------------2
        if (flyweight == null) {// ---------------------------------------------------3
            // 产生新的ConcreteFlyweight
            flyweight = new ConcreteFlyweight((String) obj);
            flyweights.put(obj, flyweight);// --------------------------------------5
        }
        return flyweight;// ---------------------------------------------------------6
    }

    public int getFlyweightSize() {
        return flyweights.size();
    }
}
