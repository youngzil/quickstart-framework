/**
 * 项目名称：quickstart-javase 
 * 文件名：MyHandler.java
 * 版本信息：
 * 日期：2017年12月21日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.xml.sax;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * MyHandler
 * 
 * @author：yangzl@asiainfo.com
 * @2017年12月21日 下午8:51:17
 * @since 1.0
 */
public class MyBookHandler extends DefaultHandler {
    private Book book;
    private List<Book> books = new ArrayList<Book>();
    private String tag;

    public List<Book> getInfo() {
        return books;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // TODO Auto-generated method stub
        tag = qName;
        if ("book".equals(qName)) {
            book = new Book();
        }
        if ("title".equals(qName)) {
            book.setLangguage(attributes.getValue("lang"));
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // TODO Auto-generated method stub
        String newString = new String(ch, start, length);
        if ("title".equals(tag)) {
            book.setTitle(newString);
        } else if ("author".equals(tag)) {
            book.setAuthor(newString);
        } else if ("year".equals(tag)) {
            book.setYear(Integer.parseInt(newString));
        } else if ("price".equals(tag)) {
            book.setPrice(Double.parseDouble(newString));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // TODO Auto-generated method stub
        tag = "";
        if ("book".equals(qName)) {
            books.add(book);
        }
    }
}
