/**
 * 项目名称：quickstart-jython 
 * 文件名：Orange.java
 * 版本信息：
 * 日期：2019年4月17日
 * Copyright asiainfo Corporation 2019
 * 版权所有 *
 */
package org.quickstart.jython;

/**
 * Orange
 * 
 * @author：yangzl@asiainfo.com
 * @2019年4月17日 下午3:56:37
 * @since 1.0
 */
// Orange
public class Orange implements Fruit {
    public String getName() {
        return "ava orange";
    }

    public String getType() {
        return "orange";
    }

    public void show() {
        System.out.println("Show: I am a java orange.");
    }
}
