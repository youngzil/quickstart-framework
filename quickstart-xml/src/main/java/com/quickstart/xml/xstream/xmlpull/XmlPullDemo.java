/**
 * 项目名称：quickstart-xml 
 * 文件名：XmlPullDemo.java
 * 版本信息：
 * 日期：2018年5月20日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package com.quickstart.xml.xstream.xmlpull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 * XmlPullDemo
 * 
 * @author：yangzl@asiainfo.com
 * @2018年5月20日 下午11:35:13
 * @since 1.0
 */
public class XmlPullDemo {

    public static void main(String[] args) throws XmlPullParserException, IOException {

        // 创建一个解析工厂
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        // 由工厂创建一个解析器对象
        XmlPullParser parser = factory.newPullParser();
        // 打开xml文档对应的输入流,填写xml文档的路径
        parser.setInput(new FileInputStream("src/main/resources/student.xml"), "UTF-8");
        // 获得事件类型
        int eventType = parser.getEventType();
        System.out.println(eventType == XmlPullParser.START_DOCUMENT);
        do {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    System.out.println("读取开始,开始文档!");
                    break;
                case XmlPullParser.START_TAG:
                    String tagName = parser.getName();
                    if (tagName.equals("student")) {
                        System.out.println("读取进度-开始标签:" + parser.getName() + ":" + parser.getAttributeName(0) + ":" + parser.getAttributeValue(0));
                    } else if (tagName.equals("stuno")) {
                        System.out.println("读取进度-学号:" + parser.nextText());
                    } else if (tagName.equals("name")) {
                        System.out.println("读取进度-姓名:" + parser.nextText());
                    } else if (tagName.equals("sex")) {
                        System.out.println("读取进度-性别:" + parser.nextText());
                    } else if (tagName.equals("grade")) {
                        System.out.println("读取进度-班级:" + parser.nextText());
                    } else if (tagName.equals("students")) {
                        System.out.println("读取开始-开始根目录:" + parser.getName());
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if (parser.getName().equals("student")) {
                        System.out.println("读取进度-结束标签:" + parser.getName());
                    } else if (parser.getName().equals("students")) {
                        System.out.println("读取完成-结束根目录:" + parser.getName());
                    }
                    break;
            }
            // 读取下一个节点
            eventType = parser.next();
        } while (eventType != XmlPullParser.END_DOCUMENT);
        System.out.println("读取完成，文档结束!");
    }

}
