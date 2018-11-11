/**
 * 项目名称：quickstart-javase 
 * 文件名：SAXDemo.java
 * 版本信息：
 * 日期：2017年12月21日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.xml.sax;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;

/**
 * SAXDemo
 * 
 * @author：youngzil@163.com
 * @2017年12月21日 下午8:52:46
 * @since 1.0
 */
public class SAXDemo {
    public static void main(String[] args) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            MyBookHandler handler = new MyBookHandler();
            String xmlPath = "/Users/yangzl/git/quickstart-all/quickstart-javase/src/main/java/org/quickstart/javase/xml/sax/bookstore.xml";
            parser.parse(xmlPath, handler);
            // InputSource is = new InputSource(new BufferedInputStream(new FileInputStream("src\\org\\quickstart\\javase\\xml\\sax\\bookstore.xml")));
            // parser.parse(is, handler);
            // parser.parse("D:/bookstore.xml", handler);
            List<Book> books = handler.getInfo();
            Iterator<Book> iterator = books.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
