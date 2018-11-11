/**
 * 项目名称：quickstart-javase 
 * 文件名：SaxTest1.java
 * 版本信息：
 * 日期：2017年12月21日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.xml.sax;

import java.io.File;
import java.util.Stack;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SaxTest1 
 *  
 * @author：youngzil@163.com
 * @2017年12月21日 下午9:05:27 
 * @since 1.0
 */
/**
 * SAX解析XML 查看事件调用
 *
 */
public class SaxTest1 {

    public static void main(String[] args) throws Exception {
        // step 1: 获得SAX解析器工厂实例
        SAXParserFactory factory = SAXParserFactory.newInstance();

        // step 2: 获得SAX解析器实例
        SAXParser parser = factory.newSAXParser();

        // step 3: 开始进行解析
        // 传入待解析的文档的处理器
        parser.parse(new File("/Users/yangzl/git/quickstart-all/quickstart-javase/src/main/java/org/quickstart/javase/xml/sax/bookstore.xml"), new MyBookHandler());

    }
}


class MyHandler extends DefaultHandler {
    // 使用栈这个数据结构来保存
    private Stack<String> stack = new Stack<String>();

    @Override
    public void startDocument() throws SAXException {
        System.out.println("start document -> parse begin");
    }

    @Override
    public void endDocument() throws SAXException {

        System.out.println("end document -> parse finished");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("start element-----------");
        System.out.println("    localName: " + localName);
        System.out.println("    qName: " + qName);

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.println("characters-----------");
        // System.out.println(" ch: " + Arrays.toString(ch) );
        System.out.println("    ch: " + ch);
        System.out.println("    start: " + start);
        System.out.println("    length: " + length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("end element-----------");

        System.out.println("    localName: " + localName);
        System.out.println("    qName: " + qName);

    }
}
