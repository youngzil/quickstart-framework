/**
 * 项目名称：quickstart-json 
 * 文件名：XmlTest.java
 * 版本信息：
 * 日期：2017年12月14日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.jackson.v2;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * XmlTest
 * 
 * @author：youngzil@163.com
 * @2017年12月14日 下午6:12:35
 * @since 1.0
 */
public class XmlTest {

    @Test
    public void testXml() throws JsonProcessingException {

        XmlEntity xmlEntity = new XmlEntity();
        xmlEntity.setName("wuxiongliu");
        xmlEntity.setCountry("china");

        String xmlStr = new XmlMapper().writeValueAsString(xmlEntity);
        System.out.println(xmlStr);
    }
}
