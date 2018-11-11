/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Student.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.template.method;

/**
 * Student
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午11:46:51
 * @since 1.0
 */
public class Student extends AbstractPerson {
    @Override
    protected void dressUp() {
        System.out.println("穿校服");
    }

    @Override
    protected void eatBreakfast() {
        System.out.println("吃妈妈做好的早饭");
    }

    @Override
    protected void takeThings() {
        System.out.println("背书包，带上家庭作业和红领巾");
    }
}
