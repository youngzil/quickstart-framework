/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Prototype.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.prototype.deepcopy;

import java.util.ArrayList;

/**
 * Prototype
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月26日 下午5:19:48
 * @since 1.0
 */
public class Prototype implements Cloneable {
    private ArrayList list = new ArrayList();

    public Prototype clone() {
        Prototype prototype = null;
        try {
            prototype = (Prototype) super.clone();
            prototype.list = (ArrayList) this.list.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return prototype;
    }

    public static void main(String[] args) {
        Prototype p1 = new Prototype();
        p1.list.add(new Integer(100));

        Prototype p2 = p1.clone();

        System.out.println("p1=" + p1);
        System.out.println("p2=" + p2);

        System.out.println("p1.list=" + p1.list.toString());
        System.out.println("p2.list=" + p2.list.toString());

    }
}
