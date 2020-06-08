package org.quickstart.sensitive.json;

/**
 * @description TODO
 *
 * @author youngzil@163.com
 * @createTime 2020/6/7 10:00
 */
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by beibei on 18/1/24.
 */
public class JsonTest {

    public static void main(String[] args) {
        String sa = "{'sa':'saas','sb':['sa','ds','sda'],'sc':{'dsa':'21'}}";
        JSONObject jsonObject = JSON.parseObject(sa);
        System.out.println(changeSensitiveMsg(jsonObject).toJSONString());
    }

    //递归对象
    private static JSONObject changeSensitiveMsg(JSONObject jsonObject) {
        for (String key : jsonObject.keySet()) {
            String json = jsonObject.getString(key);
            if (isObject(json)) {
                jsonObject.put(key, changeSensitiveMsg(JSON.parseObject(json)));
            } else if (isArray(json)) {
                jsonObject.put(key, changeSensitiveMsg(JSON.parseArray(json)));
            } else {
                //这里才是最终覆盖敏感属性的操作
                if (isSensitiveKey(key))
                    jsonObject.put(key, "测试");
            }
        }
        return jsonObject;
    }

    //递归数组
    private static JSONArray changeSensitiveMsg(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.size(); i++) {
            String value = jsonArray.getString(i);
            if (isArray(value)) {
                jsonArray.set(i, changeSensitiveMsg(jsonArray.getJSONArray(i)));
            } else if (isObject(value)) {
                jsonArray.set(i, changeSensitiveMsg(JSON.parseObject(value)));
            }
        }
        return jsonArray;
    }

    //判断是否是对象，这个方法需要优化，遇到特殊字符相当占时间，可以根据json串首字母直接判断
    private static boolean isObject(String str) {
        try {
            JSON.parseObject(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //判断是否是数组，这个方法需要优化，遇到特殊字符相当占时间，可以根据json串首字母直接判断
    private static boolean isArray(String str) {
        try {
            JSON.parseArray(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //是否是敏感key
    public static boolean isSensitiveKey(String key) {
        return true;
    }
}
