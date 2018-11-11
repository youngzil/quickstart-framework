/**
 * 项目名称：quickstart-xml 
 * 文件名：WriteXml.java
 * 版本信息：
 * 日期：2018年5月20日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package com.quickstart.xml.xstream.xmlpull;

import java.io.FileOutputStream;

import org.kxml2.io.KXmlSerializer;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

/**
 * WriteXml
 * 
 * @author：youngzil@163.com
 * @2018年5月20日 下午11:25:51
 * @since 1.0
 */
public class WriteXml {

    public static void main(String[] args) throws Exception {

        // 创建xml解析工厂
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        // 通过工厂创建序列化器(xml生成器)
//        XmlSerializer ser = factory.newSerializer();
        XmlSerializer ser = new KXmlSerializer();  
        // 为序列化器指定输出流(将xml文件写到指定的某个文件中)
        ser.setOutput(new FileOutputStream("src/main/resources/b.xml"), "utf-8");
        
        
        
        // 开始写xml文件
        
        
//        XmlPullParserFactory factory = XmlPullParserFactory.newInstance(
//
//                System.getProperty(XmlPullParserFactory.PROPERTY_NAME),
//
//       Thread.currentThread().getContextClassLoader().getClass() );

        // 1.xml的声明---------- <?xml version='1.0' encoding='utf-8' standalone='yes' ?>
        ser.startDocument("utf-8", true);
        // 2. 开始标签 ---------- <书库>
        ser.startTag(null, "书库");
        for (int i = 0; i < 2; i++) {
            // 3.开始标签 ------------- <书>
            ser.startTag(null, "书");
            // 4. 开始标签------------ <书名>
            ser.startTag(null, "书名");
            // 4.1为书名标签设置 属性--------- <书名 id = "1001">
            ser.attribute(null, "id", "1001");
            // 4.2标签设置 文本------------ <书名 id = "1001" >百年孤独
            ser.text("百年孤独");
            // 5,结束标签 ------------ </书名>
            ser.endTag(null, "书名");

            // 6.结束标签------------</书>
            ser.endTag(null, "书");

        }
        // 7.根标签 结束-----------</书库>
        ser.endTag(null, "书库");
        ser.endDocument();

    }

}
