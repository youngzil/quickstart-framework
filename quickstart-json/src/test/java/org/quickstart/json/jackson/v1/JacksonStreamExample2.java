/**
 * 项目名称：quickstart-json 
 * 文件名：JacksonStreamExample2.java
 * 版本信息：
 * 日期：2017年12月14日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.jackson.v1;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.JsonMappingException;

/**
 * JacksonStreamExample2
 * 
 * @author：youngzil@163.com
 * @2017年12月14日 下午10:57:22
 * @since 1.0
 */
public class JacksonStreamExample2 {

    public static void main(String[] args) {

        try {

            JsonFactory jfactory = new JsonFactory();

            /*** read from file ***/
            JsonParser jParser = jfactory.createJsonParser(new File("c:\\user.json"));

            // loop until token equal to "}"
            while (jParser.nextToken() != JsonToken.END_OBJECT) {

                String fieldname = jParser.getCurrentName();
                if ("name".equals(fieldname)) {

                    // 当前结点为name
                    jParser.nextToken();
                    System.out.println(jParser.getText()); // 输出 mkyong

                }

                if ("age".equals(fieldname)) {

                    // 当前结点为age
                    // move to next, which is "name"'s value
                    jParser.nextToken();
                    System.out.println(jParser.getIntValue()); // display 29

                }

                if ("messages".equals(fieldname)) {

                    jParser.nextToken();
                    while (jParser.nextToken() != JsonToken.END_ARRAY) {

                        // display msg1, msg2, msg3
                        System.out.println(jParser.getText());

                    }

                }

            }
            jParser.close();

        } catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}
