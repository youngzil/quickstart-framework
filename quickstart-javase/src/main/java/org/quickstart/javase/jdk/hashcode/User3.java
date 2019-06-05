/**
 * 项目名称：quickstart-javase 
 * 文件名：User3.java
 * 版本信息：
 * 日期：2018年1月24日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk.hashcode;

/**
 * User3
 * 
 * http://blog.csdn.net/zzg1229059735/article/details/51498310
 * 
 * @author：youngzil@163.com
 * @2018年1月24日 下午10:11:30
 * @since 1.0
 */
public class User3 {

    private String name;
    private int age;
    private String passport;

    // getters and setters, constructor
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User3 user = (User3) o;
        return user.name.equals(name) && user.age == age && user.passport.equals(passport);
    }

    // Idea from effective Java : Item 9
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + age;
        result = 31 * result + passport.hashCode();
        return result;
    }

}
