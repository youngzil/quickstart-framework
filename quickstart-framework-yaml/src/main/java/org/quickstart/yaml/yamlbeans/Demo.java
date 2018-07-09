/**
 * 项目名称：quickstart-yaml 
 * 文件名：Demo.java
 * 版本信息：
 * 日期：2017年11月22日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.yaml.yamlbeans;

import java.util.List;

/**
 * Demo
 * 
 * @author：yangzl@asiainfo.com
 * @2017年11月22日 下午7:44:53
 * @since 1.0
 */
public class Demo {

    private String name;
    private String desc;
    private List<Demo> items;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Demo> getItems() {
        return items;
    }

    public void setItems(List<Demo> items) {
        this.items = items;
    }

}
