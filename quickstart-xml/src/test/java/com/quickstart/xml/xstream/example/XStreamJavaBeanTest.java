package com.quickstart.xml.xstream.example;

import com.thoughtworks.xstream.XStream;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @description TODO
 *
 * @author yangzl
 * @createTime 2020/7/16 15:28
 */
public class XStreamJavaBeanTest {

    @Test
    public void testJavaBeanToXml() {
        User user = new User();
        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        customer1.setCommodity("手表");
        customer2.setCommodity("电脑");

        List<Customer> list = new ArrayList<Customer>();
        list.add(customer1);
        list.add(customer2);
        user.setName("吕小布");
        user.setAge("23");
        user.setCustomer(list);

        //创建xStream对象
        XStream xStream = new XStream();

        //给class起别名(建议先注释掉,看一下不起别名输出的xml格式)
        xStream.alias("User", User.class);
        xStream.alias("Customer", Customer.class);

        //调用toXML 将对象转成字符串
        String s = xStream.toXML(user);
        System.out.println(s);

        //将字符串写入文件
        //handIo(s);

    }

    @Test
    public void testXmlToJavaBean() {

        //模拟一个xml格式字符串
        String xml = "<User>\n" + "  <name>吕小布</name>\n" + "  <age>23</age>\n" + "  <customer>\n" + "    <Customer>\n"
            + "      <commodity>手表</commodity>\n" + "    </Customer>\n" + "    <Customer>\n"
            + "      <commodity>电脑</commodity>\n" + "    </Customer>\n" + "  </customer>\n" + "</User>";

        XStream xstream = new XStream();

        //不设置会报错
//        XStream.setupDefaultSecurity(xstream);
//        xstream.allowTypes(new Class[] {User.class, Customer.class});

        //将别名与xml名字相对应
        xstream.alias("User", User.class);
        xstream.alias("Customer", Customer.class);

        User user2 = (User)xstream.fromXML(xml.toString());
        System.out.println(user2);

    }



}
