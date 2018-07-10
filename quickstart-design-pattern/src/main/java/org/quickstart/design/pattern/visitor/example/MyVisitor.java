/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：MyVisitor.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.visitor.example;

/**
 * MyVisitor
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月27日 上午11:18:08
 * @since 1.0
 */
public class MyVisitor implements Visitor {

    @Override
    public void visit(Subject sub) {
        System.out.println("visit the subject：" + sub.getSubject());
    }
}
