/**
 * 项目名称：quickstart-json 
 * 文件名：Test2.java
 * 版本信息：
 * 日期：2017年12月13日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.jsonlib;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;
import org.junit.Test;

/**
 * Test2
 * 
 * @author：youngzil@163.com
 * @2017年12月13日 下午10:30:52
 * @since 1.0
 */
public class Test2 {

    /**
     * 将对象序列化成json字符串
     * 
     * @param obj
     * @return
     */
    public static String bean2Json(Object obj) {
        JSONObject jsonObject = JSONObject.fromObject(obj);
        return jsonObject.toString();
    }

    /**
     * 将json字符串反序列化为对象
     * 
     * @param jsonStr
     * @param objClass 反序列化为该类的对象
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
        return (T) JSONObject.toBean(JSONObject.fromObject(jsonStr), objClass);
    }

    @Test
    public void testJsonType() {
        // String str = "{\"name\":\"firmware\"}";
        String str = "[{\"name\":\"name1\"},{\"name\":\"name2\"}]";
        Object typeObject = new JSONTokener(str).nextValue();
        /*if (typeObject instanceof org.json.JSONArray) {
            System.out.print("JSONArray");
        } else if (typeObject instanceof org.json.JSONObject) {
            System.out.print("JSONObject");
        }*/
    }

}
