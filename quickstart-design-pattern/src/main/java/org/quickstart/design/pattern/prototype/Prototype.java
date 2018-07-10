/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Prototype.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.prototype;

/**
 * Prototype
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月26日 下午5:13:47
 * @since 1.0
 */
public class Prototype implements Cloneable {
    public Prototype clone() {
        Prototype prototype = null;
        try {
            prototype = (Prototype) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return prototype;
    }
}
