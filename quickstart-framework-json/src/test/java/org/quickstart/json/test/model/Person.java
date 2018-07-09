/**
 * 项目名称：quickstart-json 
 * 文件名：Person.java
 * 版本信息：
 * 日期：2017年12月13日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.test.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Person
 * 
 * @author：yangzl@asiainfo.com
 * @2017年12月13日 下午10:37:31
 * @since 1.0
 */
public class Person {
    private String name;
    private FullName fullName;
    private int age;
    private Date birthday;
    private List<String> hobbies;
    private Map<String, String> clothes;
    private List<Person> friends;

    // getter setter 方法。略

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public Map<String, String> getClothes() {
        return clothes;
    }

    public void setClothes(Map<String, String> clothes) {
        this.clothes = clothes;
    }

    public List<Person> getFriends() {
        return friends;
    }

    public void setFriends(List<Person> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        String str = "Person [name=" + name + ", fullName=" + fullName + ", age=" + age + ", birthday=" + birthday + ", hobbies=" + hobbies + ", clothes=" + clothes + "]\n";
        if (friends != null) {
            str += "Friends:\n";
            for (Person f : friends) {
                str += "\t" + f;
            }
        }
        return str;
    }

}
