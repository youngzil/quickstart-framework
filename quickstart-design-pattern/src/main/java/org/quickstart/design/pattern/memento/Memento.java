/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Memento.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.memento;

/**
 * Memento
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午10:28:17
 * @since 1.0
 */
public class Memento {

    private String value;

    public Memento(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
