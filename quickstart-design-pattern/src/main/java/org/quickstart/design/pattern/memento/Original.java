/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Original.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.memento;

/**
 * Original
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月27日 上午10:27:55
 * @since 1.0
 */
public class Original {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Original(String value) {
        this.value = value;
    }

    public Memento createMemento() {
        return new Memento(value);
    }

    public void restoreMemento(Memento memento) {
        this.value = memento.getValue();
    }
}
