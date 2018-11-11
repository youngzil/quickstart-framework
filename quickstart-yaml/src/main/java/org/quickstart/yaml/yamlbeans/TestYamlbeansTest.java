/**
 * 项目名称：quickstart-yaml 
 * 文件名：TestYamlbeansTest.java
 * 版本信息：
 * 日期：2017年11月22日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.yaml.yamlbeans;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;

import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;

/**
 * TestYamlbeansTest
 * 
 * @author：youngzil@163.com
 * @2017年11月22日 下午7:51:25
 * @since 1.0
 */
public class TestYamlbeansTest {

    public static void main(String[] args) throws Exception {

        Contact contact = new Contact();
        contact.setName("Nathan Sweet");
        contact.setAge(28);
        YamlWriter writer = new YamlWriter(new FileWriter("Contact1.yaml"));
        writer.write(contact);
        writer.close();

        YamlReader reader = new YamlReader(new FileReader("Contact.yaml"));
        Map<String, Contact> contact2 = reader.read(Map.class);
        System.out.println(contact2.size());

        YamlWriter writer2 = new YamlWriter(new FileWriter("Contacts2.yaml"));
        writer2.write(contact);
        writer2.close();
    }

}
