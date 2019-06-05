/**
 * 项目名称：quickstart-xml 
 * 文件名：XStreamTest.java
 * 版本信息：
 * 日期：2018年5月20日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package com.quickstart.xml.xstream.example;

import java.util.ArrayList;
import java.util.List;

/**
 * XStreamTest 
 *  
 * @author：youngzil@163.com
 * @2018年5月20日 下午11:21:50 
 * @since 1.0
 */
public class XStreamTest {
    
    public static void main(String[] args) {  
        long time1  = System.currentTimeMillis();  
        //测试java对象转xml，java对象中包含集合对象  
        Person person = new Person();  
        person.setName("admin管理员");  
        person.setSex("男");  
        person.setAge(25);  
        List<Address> adds = new ArrayList<Address>();  
        Address address1 = new Address();  
        address1.setCountry("中国");  
        address1.setProvince("安徽");  
        address1.setCity("宿州");  
        address1.setCounty("萧县");  
        Address address2 = new Address();  
        address2.setCountry("中国");  
        address2.setProvince("上海");  
        address2.setCity(null);  
        address2.setCounty("");  
        adds.add(address1);  
        adds.add(address2);  
        person.setAddress(adds);  
        String xml = XStreamUtil.beanToXml(person);  
        System.out.println(xml);  
          
        //测试xml转java对象  
        Person person2 = (Person) XStreamUtil.xmlToBean(xml);  
        System.out.println(person2.getAddress().get(1).getProvince());  
          
        long time2 = System.currentTimeMillis();  
        System.out.println((time2-time1)+"ms");  
    }  

}
