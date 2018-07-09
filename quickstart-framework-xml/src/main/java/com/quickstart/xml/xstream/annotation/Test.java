/**
 * 项目名称：quickstart-xml 
 * 文件名：Test.java
 * 版本信息：
 * 日期：2018年5月20日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package com.quickstart.xml.xstream.annotation;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Test 
 *  
 * @author：yangzl@asiainfo.com
 * @2018年5月20日 下午11:00:42 
 * @since 1.0
 */
public class Test {
    
    public static void main(String[] args) {
        java2Xml();
        xml2Java();
    }

    public static void java2Xml() {
        User user = new User("lanweihong", "lwhhhp@gmail.com");
        XStream xStream = new XStream();
        //声明XStream注解来源
        xStream.processAnnotations(User.class);
        String xml = xStream.toXML(user);
        System.out.println(xml);
    }
    
    public static void xml2Java() {
        XStream xStream = new XStream();
        xStream.processAnnotations(User.class);
        String xml = "<User>\n" + "  <userName>lanweihong</userName>\n" + "  <email>lwhhhp@gmail.com</email>\n" + "</User>";
        // 转对象
        User user = (User) xStream.fromXML(xml);
        System.out.println(user.toString());
    }
    
    public static <T> T parseFromXml(Class<T> clazz, String xml) {
        //创建解析XML对象
        XStream xStream = new XStream(new DomDriver());
        //处理注解
        xStream.processAnnotations(clazz);
        @SuppressWarnings("unchecked")
        //将XML字符串转为bean对象
        T t = (T)xStream.fromXML(xml);
        return t;
    }
    
    public static String toXml(Object obj) {
        XStream xStream = new XStream(new DomDriver());
        xStream.processAnnotations(obj.getClass());
        return xStream.toXML(obj);
    }
    

}
