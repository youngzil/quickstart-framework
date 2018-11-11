/**
 * 项目名称：quickstart-json 
 * 文件名：JsonUtil.java
 * 版本信息：
 * 日期：2017年12月14日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.jackson.v2;

import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JsonUtil
 * 
 * @author：youngzil@163.com
 * @2017年12月14日 下午11:06:55
 * @since 1.0
 */
public class JsonUtil {
    private static JsonUtil jsonUtil;
    private static JsonFactory jsonFactory;
    private static ObjectMapper objectMapper;

    private JsonUtil() {

    }

    public static JsonUtil getInstance() {
        if (jsonUtil == null)
            jsonUtil = new JsonUtil();
        return jsonUtil;
    }

    public static ObjectMapper getMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        return objectMapper;
    }

    public static JsonFactory getFactory() {
        if (jsonFactory == null)
            jsonFactory = new JsonFactory();
        return jsonFactory;
    }

    public String obj2json(Object obj) {
        JsonGenerator jsonGenerator = null;
        try {
            jsonFactory = getFactory();
            objectMapper = getMapper();
            StringWriter out = new StringWriter();
            jsonGenerator = jsonFactory.createGenerator(out);
            objectMapper.writeValue(jsonGenerator, obj);
            return out.toString();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (jsonGenerator != null)
                    jsonGenerator.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return null;
    }

    public Object json2obj(String json, Class<?> clz) {
        try {
            objectMapper = getMapper();
            return objectMapper.readValue(json, clz);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
