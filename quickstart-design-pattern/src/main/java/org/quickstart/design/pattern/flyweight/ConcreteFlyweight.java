/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：ConcreteFlyweight.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.flyweight;

/**
 * ConcreteFlyweight
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月26日 下午11:17:05
 * @since 1.0
 */
public class ConcreteFlyweight extends Flyweight {
    private String string;

    public ConcreteFlyweight(String str) {
        string = str;
    }

    public void operation() {
        System.out.println("Concrete---Flyweight : " + string);
    }
}
