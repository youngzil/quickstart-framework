package org.quickstart.hutool;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;

public class JSONUtilTest {

    /*parseXXX和toXXX
    这两种方法主要是针对JSON和其它对象之间的转换。

    readXXX
    这类方法主要是从JSON文件中读取JSON对象的快捷方法。包括：
    readJSON
        readJSONObject
    readJSONArray*/

    // 除了上面中常用的一些方法，JSONUtil还提供了一些JSON辅助方法：
    // quote 对所有双引号做转义处理（使用双反斜杠做转义）
    // wrap 包装对象，可以将普通任意对象转为JSON对象
    // formatJsonStr 格式化JSON字符串，此方法并不严格检查JSON的格式正确与否

    @Test
    public void testJSONObject() {
        JSONObject json = new JSONObject();

        JSONObject json1 = JSONUtil.createObj();
        json1.put("a", "value1");
        json1.put("b", "value2");
        json1.put("c", "value3");

        String jsonStr = "{\"b\":\"value2\",\"c\":\"value3\",\"a\":\"value1\"}";
        //方法一：使用工具类转换
        JSONObject jsonObject = JSONUtil.parseObj(jsonStr);
        //方法二：new的方式转换
        JSONObject jsonObject2 = new JSONObject(jsonStr);
        //JSON对象转字符串
        jsonObject.toString();

    }

    @Test
    public void testJSONArray() {

        //方法1
        JSONArray array2 = JSONUtil.createArray();
        //方法2
        JSONArray array = new JSONArray();
        array.add("value1");
        array.add("value2");
        array.add("value3");
        //转为JSONArray字符串
        array.toString();


    }

}
