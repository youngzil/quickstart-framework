/**
 * 项目名称：quickstart-javase 
 * 文件名：Person.java
 * 版本信息：
 * 日期：2018年1月24日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk.hashcode;

/**
 * Person
 * 
 * 用来测试Set集合保存元素的方式中 hashCode()方法和equals()方法对Set集合保存元素影响
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月24日 下午9:33:42
 * @since 1.0
 */
public class Person {

    private int id;

    private String name;

    private int age;

    Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj != null && getClass() == obj.getClass()) {
            Person other = (Person) obj;
            if (null != this.name && this.name.equals(other.name) && this.age == other.age) {
                return true;
            }
        }

        return false;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + age;
        result += id;
        return result;
    }

}
