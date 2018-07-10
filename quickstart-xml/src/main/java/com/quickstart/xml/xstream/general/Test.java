/**
 * 项目名称：quickstart-xml 
 * 文件名：Test.java
 * 版本信息：
 * 日期：2018年5月20日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package com.quickstart.xml.xstream.general;

import com.thoughtworks.xstream.XStream;

/**
 * Test
 * 
 * @author：yangzl@asiainfo.com
 * @2018年5月20日 下午10:55:21
 * @since 1.0
 */
public class Test {

    public static void main(String[] args) {
        java2Xml();
        xml2Java();
    }

    public static void java2Xml() {
        User user = new User("lanweihong", "lwhhhp@gmail.com");

        // 创建解析XML对象
        XStream xStream = new XStream();
        // 设置别名, 默认会输出全路径
        xStream.alias("User", User.class);
        // 转为xml
        String xml = xStream.toXML(user);
        System.out.println(xml);
    }
    
    public static void xml2Java() {

        XStream xStream = new XStream();
        xStream.alias("User", User.class);
        String xml = "<User>\n" + "  <userName>lanweihong</userName>\n" + "  <email>lwhhhp@gmail.com</email>\n" + "</User>";
        // 转对象
        User user = (User) xStream.fromXML(xml);
        System.out.println(user.toString());
    }

}
