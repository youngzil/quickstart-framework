/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：ClientTest.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.visitor;

import java.util.List;

/**
 * ClientTest
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午11:10:04
 * @since 1.0
 */
public class ClientTest {

    public static void main(String[] args) {
        List<Element> list = ObjectStruture.getList();
        for (Element e : list) {
            e.accept(new Visitor());
        }
    }

}
