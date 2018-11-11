/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：ObjectStruture.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ObjectStruture
 * 
 * @author：youngzil@163.com
 * @2018年1月27日 上午11:09:34
 * @since 1.0
 */
public class ObjectStruture {
    public static List<Element> getList() {
        List<Element> list = new ArrayList<Element>();
        Random ran = new Random();
        for (int i = 0; i < 10; i++) {
            int a = ran.nextInt(100);
            if (a > 50) {
                list.add(new ConcreteElement1());
            } else {
                list.add(new ConcreteElement2());
            }
        }
        return list;
    }
}
