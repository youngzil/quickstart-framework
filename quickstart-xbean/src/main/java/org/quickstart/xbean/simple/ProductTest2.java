/**
 * 项目名称：quickstart-xbean 
 * 文件名：ProductTest.java
 * 版本信息：
 * 日期：2017年3月19日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.xbean.simple;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * ProductTest
 * 
 * @author：youngzil@163.com
 * @2017年3月19日 下午1:35:05
 * @version 1.0
 */
public class ProductTest2 {
    @Test
    public void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:product2.xml");
        Product2 product2 = (Product2) context.getBean("product2");
        assertNotNull(product2);
        assertEquals(1, product2.getId());
        assertEquals("USD/JPY", product2.getName());
        assertEquals(new BigDecimal("100.00"), product2.getPrice());
    }
}
