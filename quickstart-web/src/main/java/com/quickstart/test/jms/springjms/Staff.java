/**
 * 项目名称：quickstart-web 
 * 文件名：Staff.java
 * 版本信息：
 * 日期：2017年8月20日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package com.quickstart.test.jms.springjms;

import java.io.Serializable;

/**
 * Staff
 * 
 * @author：yangzl@asiainfo.com
 * @2017年8月20日 上午10:58:25
 * @since 1.0
 */
public class Staff implements Serializable {

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     */
    private static final long serialVersionUID = 1L;

    private int id;
    private String profession;

    Staff(int id, String profession) {
        this.id = id;
        this.profession = profession;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

}
