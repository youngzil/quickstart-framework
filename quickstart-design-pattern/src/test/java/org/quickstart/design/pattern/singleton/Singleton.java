/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Singleton.java
 * 版本信息：
 * 日期：2018年1月25日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.singleton;

/**
 * Singleton
 * 
 * @author：youngzil@163.com
 * @2018年1月25日 上午11:59:22
 * @since 1.0
 */
public class Singleton {

    // 限制产生多个对象
    private Singleton() {}

    // 方法一
    // （是线程安全的）
    private static final Singleton singleton = new Singleton();

    // 通过该方法获得实例对象
    public static Singleton getInstance() {
        return singleton;
    }

    // 方法二，线程安全，volatile是关键
    /* 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载或者是懒加载 */
    private volatile static Singleton singleton2 = null;

    // 通过该方法获得实例对象
    // DCL（Double-Check Locking）
    public static Singleton getInstance2() {
        if (singleton2 == null) {
            synchronized (Singleton.class) {
                if (singleton2 == null)
                    singleton2 = new Singleton();
            }
        }
        return singleton2;
    }

    // 方法三
    // 推荐方法 是Initialization on Demand Holder（IODH）
    private static class SingletonHolder {
        private static Singleton instance = new Singleton();
    }

    public static Singleton getInstance3() {
        return SingletonHolder.instance;
    }

    // 类中其他方法，尽量是static
    public static void doSomething() {}

}
