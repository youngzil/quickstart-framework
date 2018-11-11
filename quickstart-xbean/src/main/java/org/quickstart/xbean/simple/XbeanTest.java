/**
 * 项目名称：quickstart-xbean 
 * 文件名：XbeanTest.java
 * 版本信息：
 * 日期：2017年3月19日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.xbean.simple;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * XbeanTest
 * 
 * @author：youngzil@163.com
 * @2017年3月19日 上午11:56:24
 * @version 1.0
 */
public class XbeanTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:xbean.xml");
        Teacher teacher = (Teacher) context.getBean("teacher");
        assertNotNull(teacher);
        assertEquals(25, teacher.getAge());
        assertEquals("berdy", teacher.getName());
    }

}
