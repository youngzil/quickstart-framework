/**
 * 项目名称：quickstart-javase 
 * 文件名：UserManagerImpl.java
 * 版本信息：
 * 日期：2018年1月19日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.proxy;

/**
 * UserManagerImpl
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月19日 下午10:37:23
 * @since 1.0
 */
public class UserManagerImpl implements UserManager {

    public void addUser(String id, String password) {
        System.out.println(".: 掉用了UserManagerImpl.addUser()方法！ ");

    }

    public void delUser(String id) {
        System.out.println(".: 掉用了UserManagerImpl.delUser()方法！ ");

    }
}
