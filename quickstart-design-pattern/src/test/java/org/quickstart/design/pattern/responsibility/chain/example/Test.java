/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Test.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.responsibility.chain.example;

/**
 * Test
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午10:01:25
 * @since 1.0
 */
public class Test {

    public static void main(String[] args) {
        MyHandler h1 = new MyHandler("h1");
        MyHandler h2 = new MyHandler("h2");
        MyHandler h3 = new MyHandler("h3");

        h1.setHandler(h2);
        h2.setHandler(h3);

        h1.operator();
    }
}
