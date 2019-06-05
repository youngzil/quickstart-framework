/**
 * 项目名称：quickstart-jython 
 * 文件名：Apple.java
 * 版本信息：
 * 日期：2019年4月17日
 * Copyright yangzl Corporation 2019
 * 版权所有 *
 */
package org.quickstart.jython;

/**
 * Apple
 * 
 * @author：youngzil@163.com
 * @2019年4月17日 下午3:56:11
 * @since 1.0
 */
public class Apple implements Fruit {
    public String getName() {
        return "java apple";
    }

    public String getType() {
        return "apple";
    }

    public void show() {
        System.out.println("Show: I am a java apple.");
    }
}
