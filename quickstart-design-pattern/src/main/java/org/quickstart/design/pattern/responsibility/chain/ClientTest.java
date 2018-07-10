/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：ClientTest.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.responsibility.chain;

/**
 * ClientTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月27日 上午9:51:53
 * @since 1.0
 */
public class ClientTest {

    public static void main(String[] args) {
        // 组装责任链
        Handler handler1 = new ConcreteHandler();
        Handler handler2 = new ConcreteHandler();
        handler1.setSuccessor(handler2);
        // 提交请求
        handler1.handleRequest();
    }

}
