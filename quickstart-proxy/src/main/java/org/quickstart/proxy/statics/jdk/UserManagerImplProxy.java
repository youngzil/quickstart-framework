/**
 * 项目名称：quickstart-proxy 
 * 文件名：UserManagerImplProxy.java
 * 版本信息：
 * 日期：2018年4月17日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.proxy.statics.jdk;

/**
 * UserManagerImplProxy
 * 
 * @author：yangzl@asiainfo.com
 * @2018年4月17日 下午5:40:58
 * @since 1.0
 */
public class UserManagerImplProxy implements UserManager {

    private UserManager userManager;

    public UserManagerImplProxy(UserManager userManager) {
        this.userManager = userManager;
    }

    public void addUser(String userId, String userName) {
        // 记录日志等操作或打印输入参数
        System.out.println("start-->>addUser() userId-->>" + userId);
        try {

            userManager.addUser(userId, userName);
            // 执行成功，打印成功信息
            System.out.println("success-->>addUser()");
        } catch (Exception e) {
            e.printStackTrace();
            // 失败时，打印失败信息
            System.out.println("error-->>addUser()");
            // throw new RuntimeException();
        }
    }

    public void delUser(String userId) {
        // 同上，略
        userManager.delUser(userId);
    }

    public String findUser(String userId) {
        // 同上，略
        userManager.findUser(userId);
        return null;
    }

    public void modifyUser(String userId, String userName) {
        // 同上，略
        userManager.modifyUser(userId, userName);

    }

}
