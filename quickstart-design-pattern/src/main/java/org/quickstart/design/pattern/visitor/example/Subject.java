/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Subject.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.visitor.example;

/**
 * Subject
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月27日 上午11:18:41
 * @since 1.0
 */
public interface Subject {
    public void accept(Visitor visitor);

    public String getSubject();
}
