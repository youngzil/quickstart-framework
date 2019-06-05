/**
 * 项目名称：quickstart-json 
 * 文件名：Birthday.java
 * 版本信息：
 * 日期：2017年12月13日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.jackson.v1.entity;

/**
 * Birthday
 * 
 * @author：youngzil@163.com
 * @2017年12月13日 下午11:10:17
 * @since 1.0
 */
public class Birthday {

    private String birthday;

    public Birthday(String birthday) {
        super();
        this.birthday = birthday;
    }

    // getter、setter

    public Birthday() {}

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
