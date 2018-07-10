/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：BackDoor.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.strategy;

/**
 * BackDoor
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月26日 下午11:38:33
 * @since 1.0
 */
public class BackDoor implements Strategy {
    @Override
    public void operate() {
        System.out.println("找乔国老帮忙，让吴国太给孙权施加压力，使孙权不能杀刘备");
    }
}
