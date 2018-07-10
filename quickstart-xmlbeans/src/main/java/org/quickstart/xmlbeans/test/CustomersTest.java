/**
 * 项目名称：quickstart-xmlbeans 
 * 文件名：CustomersTest.java
 * 版本信息：
 * 日期：2017年3月10日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.xmlbeans.test;

import java.io.File;
import java.io.IOException;

import org.apache.xmlbeans.XmlException;

import noNamespace.CustomersDocument;
import noNamespace.CustomersDocument.Customers;

/**
 * CustomersTest
 * 
 * @author：yangzl@asiainfo.com
 * @2017年3月10日 下午6:22:37
 * @version 1.0
 */
public class CustomersTest {

    public static void main(String[] args) throws XmlException, IOException {

        CustomersDocument customersDocument = CustomersDocument.Factory.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("customers.xml"));

        System.out.println(customersDocument.getCustomers().getCustomerArray(1).getLastname());

        Customers customers = CustomersDocument.Factory.parse(new File("customers.xml")).getCustomers();
    }

}
