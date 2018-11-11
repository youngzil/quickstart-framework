/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：ShallowCopy.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.prototype.clone;

/**
 * ShallowCopy
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午5:30:31
 * @since 1.0
 */
public class ShallowCopy implements Cloneable {

    private int age;
    private String name;

    public ShallowCopy(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public ShallowCopy() {}

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return (ShallowCopy) super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        ShallowCopy p = new ShallowCopy(23, "zhang");
        ShallowCopy p1 = (ShallowCopy) p.clone();

        System.out.println("p.getName().hashCode() : " + p.getName().hashCode());
        System.out.println("p1.getName().hashCode() : " + p1.getName().hashCode());

        String result = p.getName().hashCode() == p1.getName().hashCode() ? "clone是浅拷贝的" : "clone是深拷贝的";

        System.out.println(result);
    }

}
