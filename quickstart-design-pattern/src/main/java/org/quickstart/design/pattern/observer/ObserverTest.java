/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：ObserverTest.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.observer;

/**
 * ObserverTest
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午11:54:45
 * @since 1.0
 */
public class ObserverTest {

    public static void main(String[] args) {
        Transporter transporter = new Transporter();

        Police police = new Police();
        Security security = new Security();
        Thief thief = new Thief();

        transporter.addWatcher(police);
        transporter.addWatcher(security);
        transporter.addWatcher(security);

        transporter.notifyWatchers();
    }

}
