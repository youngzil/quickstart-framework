/**
 * 项目名称：quickstart-xml 
 * 文件名：User.java
 * 版本信息：
 * 日期：2018年5月20日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package com.quickstart.xml.xstream.general;

import lombok.Getter;
import lombok.Setter;

/**
 * User 
 *  
 * @author：youngzil@163.com
 * @2018年5月20日 下午10:52:35 
 * @since 1.0
 */
@Getter@Setter
public class User {
    
   
    
    private String userName;

    private String email;

    public User() {}

     public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public String toString() {
        return "User:{userName=" + this.userName + ",email=" + this.email + "}";
    }

}
