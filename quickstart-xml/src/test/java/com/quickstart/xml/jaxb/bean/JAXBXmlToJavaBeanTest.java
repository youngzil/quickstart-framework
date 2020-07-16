package com.quickstart.xml.jaxb.bean;

import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * @description TODO
 *
 * @author yangzl
 * @createTime 2020/7/16 15:17
 */
public class JAXBXmlToJavaBeanTest {

    @Test
    public void testJavaBean() {
        People p = new People();
        String xml="<?xml version='1.0' encoding='utf-8' standalone='yes'?>" +
            "<employee>"+
            "<id>002</id>"+
            "<name>李四</name>"+
            "<sex>女</sex>"+
            "<age>28</age>"+
            "</employee>";
        convertToObject(p, xml);
    }

    public static String convertToObject(People p, String xml){
        String result = null;
        try {
            StringReader sr = new StringReader(xml);
            JAXBContext context = JAXBContext.newInstance(p.getClass());
            Unmarshaller unmarshaller = context.createUnmarshaller();
            p=(People)unmarshaller.unmarshal(sr);
            result=p.toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
