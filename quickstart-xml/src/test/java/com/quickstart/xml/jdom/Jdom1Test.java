package com.quickstart.xml.jdom;

import com.quickstart.xml.bean.StudentGridlb;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @description TODO
 *
 * @author yangzl
 * @createTime 2020/7/14 16:42
 */
public class Jdom1Test {
    public static void main(String[] args) throws ParseException, FileNotFoundException, IOException, JDOMException {

        String path = System.getProperty("user.dir") + "/quickstart-xml/src/main/resources/demo.xml";

        SAXBuilder jdomsaxBuilder = new SAXBuilder(false);
        Document doc = jdomsaxBuilder.build(path);
        Element rootElement = doc.getRootElement();

        List<StudentGridlb> studentGridlbList = new ArrayList<>();
        StudentGridlb studentGridlb;
        for (Object classGridlb : rootElement.getChildren("classGridlb")) {
            Element classGridlbEle = (Element)classGridlb;
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            for (Object studentGrid : classGridlbEle.getChild("studentGrid").getChildren("studentGridlb")) {
                Element studentGridEle = (Element)studentGrid;
                studentGridlb = new StudentGridlb();
                studentGridlb.setStu_id(studentGridEle.getChildTextTrim("stu_id"));
                studentGridlb.setStu_age(Integer.parseInt(studentGridEle.getChildTextTrim("stu_age")));
                studentGridlb.setStu_name(studentGridEle.getChildTextTrim("stu_name"));
                studentGridlb.setStu_birthday(format.parse(studentGridEle.getChildTextTrim("stu_birthday")));
                studentGridlbList.add(studentGridlb);
            }
        }
        XMLOutputter outputter = new XMLOutputter();
        outputter.output(doc, new FileOutputStream(path));
        System.out.println(studentGridlbList);
    }
}