/**
 * 项目名称：quickstart-example 
 * 文件名：Singleton.java
 * 版本信息：
 * 日期：2017年3月7日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.example.designpattern.singleton;

/**
 * Singleton
 * 
 * @author：yangzl@asiainfo.com
 * @2017年3月7日 下午2:25:12
 * @version 1.0
 */
public class Singleton {

    private static Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() { // 对获取实例的方法进行同步
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null)
                    instance = new Singleton();
            }
        }
        return instance;
    }

}
