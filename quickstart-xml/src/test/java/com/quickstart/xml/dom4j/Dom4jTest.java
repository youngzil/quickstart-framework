package com.quickstart.xml.dom4j;

import com.quickstart.xml.bean.StudentGridlb;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @description TODO
 *
 * @author yangzl
 * @createTime 2020/7/14 16:40
 */
public class Dom4jTest {
    public static void main(String[] args) throws DocumentException, ParseException {
        String path = System.getProperty("user.dir") + "/quickstart-xml/src/main/resources/demo.xml";
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(path));

        List<StudentGridlb> studentGridlbList = new ArrayList<>();
        StudentGridlb studentGridlbVo;
        for (Object classGridlb : document.getRootElement().elements("classGridlb")) {
            Element classGridlbEle = (Element)classGridlb;
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            for (Object studentGridlb : classGridlbEle.element("studentGrid").elements("studentGridlb")) {
                Element studentGridlbEle = (Element)studentGridlb;

                studentGridlbVo = new StudentGridlb();
                studentGridlbVo.setStu_id(studentGridlbEle.elementTextTrim("stu_id"));
                studentGridlbVo.setStu_age(Integer.parseInt(studentGridlbEle.elementTextTrim("stu_age")));
                studentGridlbVo.setStu_name(studentGridlbEle.elementTextTrim("stu_name"));
                studentGridlbVo.setStu_birthday(format.parse(studentGridlbEle.elementTextTrim("stu_birthday")));
                studentGridlbList.add(studentGridlbVo);
            }
        }
        System.out.println(studentGridlbList);
    }
}
