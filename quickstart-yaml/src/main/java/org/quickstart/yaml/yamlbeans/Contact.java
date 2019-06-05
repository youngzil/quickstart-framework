/**
 * 项目名称：quickstart-yaml 
 * 文件名：Contact.java
 * 版本信息：
 * 日期：2017年11月22日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.yaml.yamlbeans;

import java.util.List;

/**
 * Contact
 * 
 * @author：youngzil@163.com
 * @2017年11月22日 下午7:54:33
 * @since 1.0
 */
public class Contact {

    private String name;
    private int age;

    private List<Phone> phoneNumbers;

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

    public List<Phone> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<Phone> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

}
