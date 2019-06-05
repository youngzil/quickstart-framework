/**
 * 项目名称：quickstart-json 
 * 文件名：Birthday.java
 * 版本信息：
 * 日期：2017年12月13日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.jsonlib.entity;

/**
 * Birthday
 * 
 * @author：youngzil@163.com
 * @2017年12月13日 下午8:24:27
 * @since 1.0
 */
public class Birthday {

    private String birthday;

    public Birthday() {}

    public Birthday(String birthday) {
        super();
        this.birthday = birthday;
    }

    // setter、getter
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return this.birthday;
    }

}
