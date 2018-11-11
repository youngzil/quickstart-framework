/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Police.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.observer;

/**
 * Police
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午11:52:40
 * @since 1.0
 */
public class Police implements Watcher {
    @Override
    public void update() {
        System.out.println("运输车有行动，警察护航");
    }
}
