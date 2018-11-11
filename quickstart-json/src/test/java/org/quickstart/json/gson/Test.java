/**
 * 项目名称：quickstart-json 
 * 文件名：Test.java
 * 版本信息：
 * 日期：2017年12月13日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * Test
 * 
 * @author：youngzil@163.com
 * @2017年12月13日 下午10:32:15
 * @since 1.0
 */
public class Test {

    public static String bean2Json(Object obj) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(obj);
    }

    public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(jsonStr, objClass);
    }

    /**
     * 把混乱的json字符串整理成缩进的json字符串
     * 
     * @param uglyJsonStr
     * @return
     */
    public static String jsonFormatter(String uglyJsonStr) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(uglyJsonStr);
        String prettyJsonString = gson.toJson(je);
        return prettyJsonString;
    }

}
