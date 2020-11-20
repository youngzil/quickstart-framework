/**
 * 项目名称：quickstart-json 
 * 文件名：XmlTest.java
 * 版本信息：
 * 日期：2017年12月14日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.jackson.v2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;

import java.util.Map;

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

    @Test
    public void testXmlStr() throws JsonProcessingException {

        String b =
            "<?xml version='1.0' encoding='UTF-8'?><RESP_PARAM><PUB_INFO><RETURN_RESULT>21600013</RETURN_RESULT><RETURN_DESC>返回码未配置[11100015]:[输入号码不是用户号码！]</RETURN_DESC><OSB_SERIAL_NO>way-app-default^15920437560000000001</OSB_SERIAL_NO></PUB_INFO><BUSI_INFO></BUSI_INFO></RESP_PARAM>";

        XmlMapper xmlMapper = new XmlMapper();
        Map map = xmlMapper.readValue(b, Map.class);

        System.out.println(map);

        String ff = xmlMapper.writeValueAsString(map);
        System.out.println(ff);


    }

}
