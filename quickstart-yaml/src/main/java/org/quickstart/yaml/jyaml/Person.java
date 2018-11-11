/**
 * 项目名称：quickstart-yaml 
 * 文件名：Person.java
 * 版本信息：
 * 日期：2017年11月22日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.yaml.jyaml;

/**
 * Person
 * 
 * @author：youngzil@163.com
 * @2017年11月22日 下午5:38:17
 * @since 1.0
 */
public class Person {

    private String name;
    private int age;
    private Person spouse;
    private Person[] children;

    public Person() {}

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }

    public void setChildren(Person[] children) {
        this.children = children;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public Person getSpouse() {
        return this.spouse;
    }

    public Person[] getChildren() {
        return this.children;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "{name=" + name + ",age=" + age + ",spouse=" + spouse + ",children=" + children + "}";
    }

}
