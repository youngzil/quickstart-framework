/**
 * 项目名称：quickstart-spring-framework 
 * 文件名：DatasourceNamespaceHandlerSupportTest.java
 * 版本信息：
 * 日期：2018年4月16日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.spring.framework.beans.factory.xml;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * DatasourceNamespaceHandlerSupportTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年4月16日 下午4:07:52
 * @since 1.0
 */
public class DatasourceNamespaceHandlerSupportTest {
    private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("datasource-test.xml");

    public static void main(String[] args) {
        DataSourceInfo d = (DataSourceInfo) context.getBean("myDataSourcce");
        System.out.println(d);
    }

}
