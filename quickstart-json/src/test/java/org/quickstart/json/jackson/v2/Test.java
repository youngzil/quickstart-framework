/**
 * 项目名称：quickstart-json 
 * 文件名：Test.java
 * 版本信息：
 * 日期：2017年12月14日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.jackson.v2;

import org.apache.xpath.operations.String;
import org.quickstart.json.test.model.Person;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * Test
 * 
 * @author：youngzil@163.com
 * @2017年12月14日 上午9:31:56
 * @since 1.0
 */
public class Test {

    public static void main(String[] args) throws JsonProcessingException {

        Person person = new Person();
        // 设置person属性
        ObjectMapper mapper = new XmlMapper();
        System.out.println(mapper.writeValueAsString(person));

        // 美化过的输出

        // 方式1.使用：writerWithDefaultPrettyPrinter
        ObjectMapper mapper2 = new XmlMapper();
        System.out.println(mapper2.writerWithDefaultPrettyPrinter().writeValueAsString(person));
        mapper2.enable(SerializationFeature.INDENT_OUTPUT);

        // 方式2.使用：SerializationFeature.INDENT_OUTPUT
        ObjectMapper mapper3 = new XmlMapper();
        mapper3.enable(SerializationFeature.INDENT_OUTPUT);
        System.out.println(mapper3.writeValueAsString(person));

        // 序列化为json时，操作基本一致，只需要使用ObjectMapper替代XmlMapper。如：
        // XmlMapper在jackson-dataformat-xml中，ObjectMapper却是在jackson-databind
        // 核心组件包括：jackson-annotations、jackson-core、jackson-databind。
        Person person2 = new Person();
        // 设置person属性
        ObjectMapper mapper4 = new ObjectMapper();
        System.out.println(mapper4.writeValueAsString(person2));

    }

}
