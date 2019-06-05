/**
 * 项目名称：quickstart-javase 
 * 文件名：Test.java
 * 版本信息：
 * 日期：2017年7月20日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.example;

/**
 * Test
 * 
 * @author：youngzil@163.com
 * @2017年7月20日 下午10:19:08
 * @version 2.0
 */
public class InvokeBrowseTest {

    public static void main(String[] args) {

        // 第一种
        // try {
        // Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler http://www.baidu.com");
        // } catch (Exception e) {
        // e.printStackTrace() ;
        // }

        // 第二种
        if (java.awt.Desktop.isDesktopSupported()) {
            try {
                // 创建一个URI实例
                java.net.URI uri = java.net.URI.create("http://www.baidu.com/");
                // 获取当前系统桌面扩展
                java.awt.Desktop dp = java.awt.Desktop.getDesktop();
                // 判断系统桌面是否支持要执行的功能
                if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
                    // 获取系统默认浏览器打开链接
                    dp.browse(uri);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
