/**
 * 项目名称：quickstart-javase 
 * 文件名：SAXDemo02.java
 * 版本信息：
 * 日期：2017年12月21日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.xml.sax;

import java.io.StreamCorruptedException;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAXDemo02
 * 
 * @author：youngzil@163.com
 * @2017年12月21日 下午9:39:48
 * @since 1.0
 */
public class SAXDemo02 {

    /**
     * 使用Sax方法解析students.xml文件，保存到集合中
     * 
     * @param args
     */
    public static void main(String[] args) throws Exception {
        // 1.创建工厂 SAX分析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();

        // 2.创建解释器
        SAXParser parser = factory.newSAXParser();

        // 3.从解释器获取读取器
        XMLReader reader = parser.getXMLReader();
        StudentContentHandler2 studentContentHandler2 = new StudentContentHandler2();
        // 4.设置内容处理器
        reader.setContentHandler(studentContentHandler2);

        // 5.解析
        String xmlPath = "/Users/yangzl/git/quickstart-all/quickstart-javase/src/main/java/org/quickstart/javase/xml/sax/studeng.xml";
        reader.parse(xmlPath);

        for (Student s : studentContentHandler2.getList()) {
            System.out.println(s.getId() + "--" + s.getName() + "--" + s.getAge() + "--" + s.getSex());
        }
    }
}


class StudentContentHandler2 extends DefaultHandler {
    ArrayList<Student> list = new ArrayList<Student>();
    String currentTagName;
    Student student = null;

    public ArrayList<Student> getList() {
        return list;
    }

    public void setList(ArrayList<Student> list) {
        this.list = list;
    }

    public String getCurrentTagName() {
        return currentTagName;
    }

    public void setCurrentTagName(String currentTagName) {
        this.currentTagName = currentTagName;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    // 内容处理器开始标签
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        currentTagName = qName;
        if (qName.equals("student")) {
            student = new Student();

        }
    }

    // 内容处理器 结束标签
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        currentTagName = "";
        if (qName.equals("student")) {
            list.add(student);
        }

    }

    // 内容处理 处理内容标签
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        if (currentTagName.equals("name")) {
            student.setName(new String(ch, start, length));
        } else if (currentTagName.equals("age")) {
            student.setAge(Integer.parseInt(new String(ch, start, length)));
        } else if (currentTagName.equals("sex")) {
            student.setSex(new String(ch, start, length));
        } else if (currentTagName.equals("id")) {
            student.setId(Integer.parseInt(new String(ch, start, length)));
        }

    }

}


class Student {
    private int id;
    private String name;
    private int age;
    private String sex;

    public Student() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Student(int id, String name, int age, String sex) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
