/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Teacher.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.template.method;

/**
 * Teacher
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午11:47:26
 * @since 1.0
 */
public class Teacher extends AbstractPerson {
    @Override
    protected void dressUp() {
        System.out.println("穿工作服");
    }

    @Override
    protected void eatBreakfast() {
        System.out.println("做早饭，照顾孩子吃早饭");
    }

    @Override
    protected void takeThings() {
        System.out.println("带上昨晚准备的考卷");
    }
}
