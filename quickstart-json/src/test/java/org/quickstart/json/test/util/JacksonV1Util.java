/**
 * 项目名称：quickstart-json 
 * 文件名：JacksonUtil.java
 * 版本信息：
 * 日期：2017年12月13日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.test.util;

import java.io.IOException;
import java.io.StringWriter;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * JacksonUtil
 * 
 * @author：youngzil@163.com
 * @2017年12月13日 下午10:40:22
 * @since 1.0
 */
public class JacksonV1Util {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String bean2Json(Object obj) throws IOException {
        StringWriter sw = new StringWriter();
        JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);
        mapper.writeValue(gen, obj);
        gen.close();
        return sw.toString();
    }

    public static <T> T json2Bean(String jsonStr, Class<T> objClass) throws JsonParseException, JsonMappingException, IOException {
        return mapper.readValue(jsonStr, objClass);
    }

}
