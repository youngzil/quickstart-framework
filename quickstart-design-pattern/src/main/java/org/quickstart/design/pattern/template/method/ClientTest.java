/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：ClientTest.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.template.method;

/**
 * ClientTest
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午11:48:00
 * @since 1.0
 */
public class ClientTest {

    public static void main(String[] args) {
        Student student = new Student();
        student.prepareGotoSchool();

        Teacher teacher = new Teacher();
        teacher.prepareGotoSchool();
    }

}
