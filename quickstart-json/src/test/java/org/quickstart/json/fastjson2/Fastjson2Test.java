package org.quickstart.json.fastjson2;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONPath;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class Fastjson2Test {

    @Test
    public void testJSONObject() {

        // 将JSON解析为JSONObject
        String text = "{\"id\": 2,\"name\": \"fastjson2\"}";
        JSONObject obj = JSON.parseObject(text);

        byte[] bytes = text.getBytes();
        JSONObject data2 = JSON.parseObject(bytes);

        // 获取简单属性
        int id = obj.getIntValue("id");
        String name = obj.getString("name");

    }

    @Test
    public void testJSONArray() {

        // 2.2 将JSON解析为JSONArray
        String text = "[2, \"fastjson2\"]";
        JSONArray array = JSON.parseArray(text);

        // 获取简单属性
        int id = array.getIntValue(0);
        String name = array.getString(1);

    }

    @Test
    public void testJavaObject() {
        // 2.3 将JSON解析为Java对象
        String text = "...";
        User data = JSON.parseObject(text, User.class);

        // 读取JavaBean
        /*JSONArray array = ...
        JSONObject obj = ...

        User user = array.getObject(0, User.class);
        User user = obj.getObject("key", User.class);*/

        // 转为JavaBean
        /*JSONArray array = ...
        JSONObject obj = ...

        User user = obj.toJavaObject(User.class);
        List<User> users = array.toJavaList(User.class);*/
    }

    @Test
    public void testJavaObject2JSON() {
        // 将Java对象序列化为JSON
        Object data = "...";
        String text = JSON.toJSONString(data);
        byte[] text2 = JSON.toJSONBytes(data);

        User user = new User();
        user.id = 2;
        user.name = "FastJson2";
        String text3 = JSON.toJSONString(user);
        byte[] bytes = JSON.toJSONBytes(user);
    }

    @Test
    public void testJSONB() {

        // 将JavaBean对象序列化JSONB
        User user = new User();
        byte[] bytes = JSONB.toBytes(user);
        byte[] bytes2 = JSONB.toBytes(user, JSONWriter.Feature.BeanToArray);

        // 将JSONB数据解析为JavaBean
        User user2 = JSONB.parseObject(bytes, User.class);
        User user3 = JSONB.parseObject(bytes, User.class, JSONReader.Feature.SupportArrayToBean);

    }

    @Test
    public void testJsonPath() throws IOException {

        InputStream is = Fastjson2Test.class.getClassLoader().getResourceAsStream("json/JsonPathDemo.json");
        String str = IOUtils.toString(is, "UTF-8");
        Object ff = JSONPath.extract(str, "$.store.book[*].author");

        JSONReader jsonReader = JSONReader.of(str);
        JSONPath jsonPath = JSONPath.of("$.store.bicycle.color");
        Object fff = jsonPath.extract(jsonReader);

        // 3.2.1 使用JSONPath读取部分数据
        String text = "...";
        JSONPath path = JSONPath.of("$.id"); // 缓存起来重复使用能提升性能

        JSONReader parser = JSONReader.of(text);
        Object result = path.extract(parser);

        // 3.2.2 使用JSONPath读取部分byte[]的数据
        byte[] bytes = "...".getBytes();
        JSONPath path2 = JSONPath.of("$.id"); // 缓存起来重复使用能提升性能

        JSONReader parser2 = JSONReader.of(bytes);
        Object result2 = path2.extract(parser2);

        // 3.2.3 使用JSONPath读取部分byte[]的数据
        JSONPath path3 = JSONPath.of("$.id"); // 缓存起来重复使用能提升性能

        JSONReader parser3 = JSONReader.ofJSONB(bytes); // 注意这里使用ofJSONB方法
        Object result3 = path3.extract(parser3);

    }

    public class User {
        private int id;
        private String name;
        private int age;
    }

}
