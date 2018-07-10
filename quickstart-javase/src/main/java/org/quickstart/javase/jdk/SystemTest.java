/**
 * 项目名称：quickstart-javase 
 * 文件名：SystemTest.java
 * 版本信息：
 * 日期：2018年3月19日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk;

import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * SystemTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年3月19日 下午4:41:21
 * @since 1.0
 */
public class SystemTest {

    public static void main(String[] args) {
        String key = "key";
        String value = "default-value";
        try {
            if (System.getSecurityManager() == null) {
                value = System.getProperty(key);
            } else {
                // 还有一个方法，就是doPrivileged()方法，获取特权，用于绕过权限检查。
                value = AccessController.doPrivileged(new PrivilegedAction<String>() {
                    @Override
                    public String run() {
                        return System.getProperty(key);
                    }
                });
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

}
