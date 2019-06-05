/**
 * 项目名称：quickstart-javase 
 * 文件名：Resource.java
 * 版本信息：
 * 日期：2017年7月23日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.example.singleton;

/**
 * Resource
 * 
 * @author：youngzil@163.com
 * @2017年7月23日 上午11:13:46
 * @version 2.0
 */
public class Singleton {

    // （Double-Check）
    private volatile Singleton instance = null;

    public Singleton getInstance1() {
        if (instance == null) {
            synchronized (this) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    // 推荐方法 是Initialization on Demand Holder（IODH）
    private static class SingletonHolder {
        private static Singleton instance = new Singleton();
    }

    public static Singleton getInstance2() {
        return SingletonHolder.instance;
    }

}
