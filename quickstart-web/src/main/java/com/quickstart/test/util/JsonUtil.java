package com.quickstart.test.util;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @Description: Json与Java对象互转工具类
 * @author tanw
 * @date 2013-8-14 上午9:58:24
 */
public class JsonUtil {

    private static String dateFormat = "yyyy-MM-dd HH:mm:ss";

    /*
     * Object转换为Json字符串  日期按照默认的类型(yyyy-MM-dd HH:mm:ss)格式化
     */
    public static String toJsonString(Object obj) {
        // return JSON.toJSONString(obj);
        return JSON.toJSONStringWithDateFormat(obj, dateFormat);
    }

    /*
     * Object转换为Json字符串  日期按照指定的类型格式化
     */
    public static String toJsonStringWithDateFormat(Object obj, String dateFormat) {
        return JSON.toJSONStringWithDateFormat(obj, dateFormat);
    }

    /*
     * Json字符串转为T类型的java对象
     */
    public static <T> T parseObject(String jsonStr, Class<T> clazz) {
        return JSON.parseObject(jsonStr, clazz);
    }

    public static <T> List<T> parseArray(String jsonStr, Class<T> clazz) {
        return JSON.parseArray(jsonStr, clazz);
    }

    /*
     * Object转换为Json字符串 （单引号）
     */
    public static String toJsonStringWithSingleQuotes(Object obj) {
        return JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.UseSingleQuotes);
    }

}
