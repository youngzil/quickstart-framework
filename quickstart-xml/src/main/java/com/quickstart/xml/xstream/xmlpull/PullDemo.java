/**
 * 项目名称：quickstart-xml 
 * 文件名：PullDemo.java
 * 版本信息：
 * 日期：2018年5月20日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package com.quickstart.xml.xstream.xmlpull;
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.InputStream;  
import java.util.ArrayList;  
import java.util.List;  
  
import org.kxml2.io.KXmlParser;  
import org.kxml2.io.KXmlSerializer;  
import org.xmlpull.v1.XmlPullParser;  
import org.xmlpull.v1.XmlSerializer;  
/**
 * PullDemo 
 *  
 * @author：youngzil@163.com
 * @2018年5月20日 下午11:47:12 
 * @since 1.0
 */
public class PullDemo {  
    
    /** 
     * 解析输入流中的xml文件 
     *  
     * @param is 输入流 
     * @return 解析结果集 
     */  
    public List<Wisdom> parseXml(InputStream is) {  
        // 声明返回值  
        List<Wisdom> wisdomList = null;  
        // 获取解析对象  
        XmlPullParser xmlPullParser = new KXmlParser();  
          
        try {  
            // 设置输入流的编码  
            xmlPullParser.setInput(is, "utf-8");  
            // 获取解析的事件类型  
            int eventType = xmlPullParser.getEventType();  
            // 声明一个Wisdom引用  
            Wisdom wisdom = null;  
            // 判断文件解析的是否完毕  
            while (eventType != XmlPullParser.END_DOCUMENT) {  
                  
                String tagName = xmlPullParser.getName();  
                  
                switch (eventType) {  
                case XmlPullParser.START_DOCUMENT:  
                    wisdomList = new ArrayList<Wisdom>();  
                    break;  
  
                case XmlPullParser.START_TAG:  
                      
                    if ("wisdom".equals(tagName)) {  
                        // 创建wisdom对象  
                        wisdom = new Wisdom();  
                        wisdom.setId(Integer.parseInt(xmlPullParser  
                                .getAttributeValue(null, "id")));  
                    } else if ("content".equals(tagName)) {  
                        wisdom.setContent(xmlPullParser.nextText());  
                    } else if ("author".equals(tagName)) {  
                        wisdom.setAuthor(xmlPullParser.nextText());  
                    }  
                    break;  
                case XmlPullParser.END_TAG:  
                    if ("wisdom".equals(tagName) && wisdom != null) {  
                        // 把wisdom对象加入到集合中去  
                        wisdomList.add(wisdom);  
                        wisdom = null;  
                    }  
                    break;  
                }  
                //读取下一个事件  
                eventType = xmlPullParser.next();  
            }  
            //关闭输入流  
            is.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return wisdomList;  
    }  
  
    /** 
     * 根据List列表中的内容生成xml文件 
     *  
     * @param wisdomList 
     *            装在多个wisdom对象的List 
     * @return true表示生成成功，false表示生成失败 
     */  
    public boolean createXML(List<Wisdom> wisdomList) {  
        // 采用pull解析进行实现  
        // 目标文件路径  
        String filePath = "src/main/resources/wisdoms.xml";  
        // 目标文件  
        File file = new File(filePath);  
        // 获得xml序列化实例  
        XmlSerializer serializer = new KXmlSerializer();  
        // 文件写入流实例  
        FileOutputStream fos = null;  
        try {  
            // 根据文件对象创建一个文件的输出流对象  
            fos = new FileOutputStream(file);  
            // 设置输出的流及编码  
            serializer.setOutput(fos, "utf-8");  
            // 设置文件的开始  
            serializer.startDocument("UTF-8", true);  
            // 设置文件开始标签  
            serializer.startTag(null, "root");  
            for (Wisdom wisdom : wisdomList) {  
                // wisdom标签的开始  
                serializer.startTag(null, "wisdom");  
                // 设置wisdom标签的属性  
                serializer.attribute(null, "id", wisdom.getId() + "");  
  
                // 设置wisdom标签的子标签 content  
                serializer.startTag(null, "content");  
                serializer.text(wisdom.getContent());  
                serializer.endTag(null, "content");  
  
                // 设置wisdom标签的子标签的age  
                serializer.startTag(null, "author");  
                serializer.text(wisdom.getAuthor());  
                serializer.endTag(null, "author");  
  
                // wisdom标签的结束  
                serializer.endTag(null, "wisdom");  
  
            }  
  
            // 设置文件结束标签  
            serializer.endTag(null, "root");  
            // 文件的结束  
            serializer.endDocument();  
  
            serializer.flush();  
            fos.close();  
            return true;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }  
  
    }  
  
}  
