/**
 * 项目名称：quickstart-javase 
 * 文件名：User2.java
 * 版本信息：
 * 日期：2018年1月24日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk.hashcode;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * User2
 * 
 * http://blog.csdn.net/zzg1229059735/article/details/51498310
 * 
 * @author：youngzil@163.com
 * @2018年1月24日 下午10:07:52
 * @since 1.0
 */
public class User2 {

    private String name;
    private int age;
    private String passport;
    // getters and setters, constructor

    @Override
    public boolean equals(Object o) {

        if (o == this)
            return true;
        if (!(o instanceof User2)) {
            return false;
        }
        User2 user = (User2) o;

        return new EqualsBuilder().append(age, user.age).append(name, user.name).append(passport, user.passport).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(name).append(age).append(passport).toHashCode();
    }

}
