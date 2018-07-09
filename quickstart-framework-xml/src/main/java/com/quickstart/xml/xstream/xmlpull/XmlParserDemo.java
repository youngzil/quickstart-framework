/**
 * 项目名称：quickstart-xml 
 * 文件名：XmlParserDemo.java
 * 版本信息：
 * 日期：2018年5月20日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package com.quickstart.xml.xstream.xmlpull;

import java.io.FileInputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 * XmlParserDemo 
 *  
 * @author：yangzl@asiainfo.com
 * @2018年5月20日 下午11:27:27 
 * @since 1.0
 */
public class XmlParserDemo {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        // 创建pull解析器工厂对象
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

        // 创建解析器对象
        ArrayList<Book> list = null;
        Book book = null;
        // 获得解析器
        XmlPullParser parser = factory.newPullParser();

        // 从指定文件中解析 出xml
        parser.setInput(new FileInputStream("src/b.xml"), "utf-8");

        // 1 . 判断是否是根标签的结束标签
        while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {

            switch (parser.getEventType()) {

            case XmlPullParser.START_TAG: // 开始标签
                if (parser.getName().equalsIgnoreCase("书库")) { // ------------<书库>
                    // 如果是 跟标签时,创建集合存储 对象
                    list = new ArrayList<Book>();
                } else if (parser.getName().equalsIgnoreCase("书")) { // ----------<书>

                    // 当标签是对象时. 创建 对象
                    book = new Book();

                } else if (parser.getName().equalsIgnoreCase("书名")) {// ----------<书名>

                    // 获得当前标签中的 属性以及text.并存储到对象中
                    String id = parser.getAttributeValue(null, "id");
                    book.setId(id);
                    String name = parser.nextText();
                    book.setName(name);

                }
                break;
            case XmlPullParser.END_TAG: // 结束标签
                // 标签为对象的结束标签时,将对象存储到集合中 </书>
                if (parser.getName().equalsIgnoreCase("书")) { // -------</书>
                                                            // 注意:当时</书名>结束标签时不用做处理
                    list.add(book);
                }

                break;

            }

            parser.next();

        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println("---" + list.get(i));
        }

    }
    }
