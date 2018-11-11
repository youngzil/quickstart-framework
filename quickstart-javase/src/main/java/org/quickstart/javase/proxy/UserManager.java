/**
 * 项目名称：quickstart-javase 
 * 文件名：UserManager.java
 * 版本信息：
 * 日期：2018年1月19日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.proxy;

/**
 * UserManager
 * 
 * @author：youngzil@163.com
 * @2018年1月19日 下午10:37:01
 * @since 1.0
 */
public interface UserManager {
    public void addUser(String id, String password);

    public void delUser(String id);
}
