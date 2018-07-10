/**
 * 项目名称：quickstart-json 
 * 文件名：MyBean.java
 * 版本信息：
 * 日期：2017年12月13日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.jsonlib.entity;

import java.util.List;

import net.sf.json.JSONFunction;

/**
 * MyBean
 * 
 * @author：yangzl@asiainfo.com
 * @2017年12月13日 下午8:57:31
 * @since 1.0
 */
public class MyBean {

    private String name = "json";
    private int pojoId = 1;
    private char[] options = new char[] {'a', 'f'};
    private String func1 = "function(i){ return this.options[i]; }";
    private JSONFunction func2 = new JSONFunction(new String[] {"i"}, "return this.options[i];");

    private List data;
    // getters & setters

    // getters & setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPojoId() {
        return pojoId;
    }

    public void setPojoId(int pojoId) {
        this.pojoId = pojoId;
    }

    public char[] getOptions() {
        return options;
    }

    public void setOptions(char[] options) {
        this.options = options;
    }

    public String getFunc1() {
        return func1;
    }

    public void setFunc1(String func1) {
        this.func1 = func1;
    }

    public JSONFunction getFunc2() {
        return func2;
    }

    public void setFunc2(JSONFunction func2) {
        this.func2 = func2;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

}
