/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：UserClientTest.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.facade.example2;

/**
 * UserClientTest
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午8:41:28
 * @since 1.0
 */
public class UserClientTest {

    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.startup();
        computer.shutdown();
    }

}
