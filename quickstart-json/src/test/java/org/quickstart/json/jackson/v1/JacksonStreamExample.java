/**
 * 项目名称：quickstart-json 
 * 文件名：JacksonStreamExample.java
 * 版本信息：
 * 日期：2017年12月14日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.jackson.v1;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonMappingException;

/**
 * JacksonStreamExample
 * 
 * @author：youngzil@163.com
 * @2017年12月14日 下午10:56:31
 * @since 1.0
 */
public class JacksonStreamExample {
    public static void main(String[] args) {

        try {

            JsonFactory jfactory = new JsonFactory();

            /*** write to file ***/
            JsonGenerator jGenerator = jfactory.createJsonGenerator(new File("c:\\user.json"), JsonEncoding.UTF8);
            jGenerator.writeStartObject(); // {

            jGenerator.writeStringField("name", "mkyong"); // "name" : "mkyong"
            jGenerator.writeNumberField("age", 29); // "age" : 29

            jGenerator.writeFieldName("messages"); // "messages" :
            jGenerator.writeStartArray(); // [

            jGenerator.writeString("msg 1"); // "msg 1"
            jGenerator.writeString("msg 2"); // "msg 2"
            jGenerator.writeString("msg 3"); // "msg 3"

            jGenerator.writeEndArray(); // ]

            jGenerator.writeEndObject(); // }

            jGenerator.close();

        } catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}
