/**
 * 项目名称：quickstart-json 
 * 文件名：FullName.java
 * 版本信息：
 * 日期：2017年12月13日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.test.model;

/**
 * FullName
 * 
 * @author：youngzil@163.com
 * @2017年12月13日 下午10:45:09
 * @since 1.0
 */
public class FullName {

    private String firstName;
    private String middleName;
    private String lastName;

    // 构造方法、getter setter 方法，略

    public FullName(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "[firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + "]";
    }

}
