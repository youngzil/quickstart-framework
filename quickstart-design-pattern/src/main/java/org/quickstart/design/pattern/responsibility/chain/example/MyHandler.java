/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：MyHandler.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.responsibility.chain.example;

/**
 * MyHandler
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月27日 上午10:01:08
 * @since 1.0
 */
public class MyHandler extends AbstractHandler implements Handler {

    private String name;

    public MyHandler(String name) {
        this.name = name;
    }

    @Override
    public void operator() {
        System.out.println(name + "deal!");
        if (getHandler() != null) {
            getHandler().operator();
        }
    }
}
