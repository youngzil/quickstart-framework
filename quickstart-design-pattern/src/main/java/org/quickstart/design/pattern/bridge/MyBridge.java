/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：MyBridge.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.bridge;

/**
 * MyBridge
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月26日 下午8:45:27
 * @since 1.0
 */
public class MyBridge extends Bridge {
    public void method() {
        getSource().method();
    }
}
