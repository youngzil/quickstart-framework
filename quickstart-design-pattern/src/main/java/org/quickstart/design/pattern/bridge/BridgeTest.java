/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：BridgeTest.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.bridge;

/**
 * BridgeTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月26日 下午8:46:04
 * @since 1.0
 */
public class BridgeTest {

    public static void main(String[] args) {

        Bridge bridge = new MyBridge();

        /*调用第一个对象*/
        Sourceable source1 = new SourceSub1();
        bridge.setSource(source1);
        bridge.method();

        /*调用第二个对象*/
        Sourceable source2 = new SourceSub2();
        bridge.setSource(source2);
        bridge.method();
    }
}
