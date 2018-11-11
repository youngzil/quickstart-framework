/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Storage.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.memento;

/**
 * Storage
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午10:28:41
 * @since 1.0
 */
public class Storage {

    private Memento memento;

    public Storage(Memento memento) {
        this.memento = memento;
    }

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
