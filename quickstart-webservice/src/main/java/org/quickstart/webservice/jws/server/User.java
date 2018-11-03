/**
 * 项目名称：quickstart-webservice 
 * 文件名：User.java
 * 版本信息：
 * 日期：2018年11月2日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.webservice.jws.server;

import lombok.Getter;
import lombok.Setter;

/**
 * User
 * 
 * @author：yangzl@asiainfo.com
 * @2018年11月2日 下午4:56:18
 * @since 1.0
 */
@Setter
@Getter
public class User {

    private int id;
    private String username;
    private String password;

}
