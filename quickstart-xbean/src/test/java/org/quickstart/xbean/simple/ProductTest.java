/**
 * 项目名称：quickstart-xbean 
 * 文件名：ProductTest.java
 * 版本信息：
 * 日期：2017年3月19日
 * Copyright yangzl Corporation 2017
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
public class ProductTest {
    @Test
    public void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:product.xml");
        Product product = (Product) context.getBean("product");
        assertNotNull(product);
        assertEquals("USD/JPY", product.getName());
        assertEquals(new BigDecimal("100.00"), product.getPrice());
    }
}
