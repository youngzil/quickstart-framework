/**
 * 项目名称：quickstart-example 
 * 文件名：Singleton.java
 * 版本信息：
 * 日期：2017年3月7日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.design.pattern.singleton;

/**
 * Singleton
 * 
 * @author：youngzil@163.com
 * @2017年3月7日 下午2:25:12
 * @version 1.0
 */
public class Singleton3 {

    private static Singleton3 instance;

    private Singleton3() {}

    public static Singleton3 getInstance() { // 对获取实例的方法进行同步
        if (instance == null) {
            synchronized (Singleton3.class) {
                if (instance == null)
                    instance = new Singleton3();
            }
        }
        return instance;
    }

}
