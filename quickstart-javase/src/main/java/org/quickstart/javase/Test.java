/**
 * 项目名称：quickstart-javase 
 * 文件名：Test.java
 * 版本信息：
 * 日期：2017年7月20日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase;

/**
 * Test
 * 
 * @author：youngzil@163.com
 * @2017年7月20日 下午10:19:08
 * @version 2.0
 */
public class Test {

    public static void main(String[] args) {
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler http://www.baidu.com");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
