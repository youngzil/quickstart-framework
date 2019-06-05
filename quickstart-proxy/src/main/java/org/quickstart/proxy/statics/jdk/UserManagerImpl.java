/**
 * 项目名称：quickstart-proxy 
 * 文件名：UserManagerImpl.java
 * 版本信息：
 * 日期：2018年4月17日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.proxy.statics.jdk;

/**
 * UserManagerImpl
 * 
 * @author：youngzil@163.com
 * @2018年4月17日 下午5:40:25
 * @since 1.0
 */
public class UserManagerImpl implements UserManager {

    public void addUser(String userId, String userName) {

        try {
            System.out.println("UserManagerImpl.addUser() userId-->>" + userId);
        } catch (Exception e) {
            e.printStackTrace();

            throw new RuntimeException();
        }

    }

    public void delUser(String userId) {

        System.out.println("UserManagerImpl.delUser() userId-->>" + userId);
    }

    public String findUser(String userId) {

        System.out.println("UserManagerImpl.findUser() userId-->>" + userId);
        return "于亮";
    }

    public void modifyUser(String userId, String userName) {

        System.out.println("UserManagerImpl.modifyUser() userId-->>" + userId);
    }

}
