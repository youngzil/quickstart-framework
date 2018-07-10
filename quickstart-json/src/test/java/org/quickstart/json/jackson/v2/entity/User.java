package org.quickstart.json.jackson.v2.entity;

/**
 * Created by GatsbyNewton on 2016/4/15.
 */
public class User {
    private String name;
    private String gender;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[User: \n").append("name = " + this.name + "\n").append("gender = " + this.gender + "\n").append("age = " + this.age + "]");
        return sb.toString();
    }
}
