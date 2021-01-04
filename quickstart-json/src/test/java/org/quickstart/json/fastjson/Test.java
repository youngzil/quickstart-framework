/**
 * 项目名称：quickstart-json
 * 文件名：Test.java
 * 版本信息：
 * 日期：2017年12月13日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import org.quickstart.json.fastjson.model.Bar;
import org.quickstart.json.fastjson.model.Foo;
import org.quickstart.json.fastjson.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Test
 *
 * @author：youngzil@163.com
 * @2017年12月13日 下午4:56:05
 * @since 1.0
 */
public class Test {

    // 传入一个对象和SerializerFeature类型的可变变量。SerializerFeature是一个枚举。
    private static SerializeConfig mapping = new SerializeConfig();

    static {
        // FastJSON可以直接对日期类型格式化，在缺省的情况下，FastJSON会将Date转成long。
        mapping.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
    }

    public static void main(String[] args) {

        Object obj = JSON.toJSON(new Bar());
        String x1 = JSON.toJSONString(new Bar(), true);
        System.out.println(x1);
        String x2 = JSON.toJSONString(new Bar(), mapping);
        System.out.println(x2);

        System.out.println("================================");

        Foo f1 = new Foo();
        Date date = new Date();
        String text = JSON.toJSONString(date, mapping);
        System.out.println(text);
        System.out.println(JSON.toJSONString(f1, true));
        String x22 = JSON.toJSONString(f1, mapping);
        System.out.println(x22);

        System.out.println("================================");

        json2List();
        json2Map();

        array2JSON();
        array2JSON2();
        map2JSON();
        listMap2JSON();
        bean2JSON();
    }

    public static void json2List() {
        // List -> JSON array
        List<Bar> barList = new ArrayList<Bar>();
        barList.add(new Bar());
        barList.add(new Bar());
        barList.add(new Bar());
        String json = JSON.toJSONString(barList, true);
        System.out.println(json);
        // JSON array -> List
        List<Bar> barList1 = JSON.parseArray(json, Bar.class);
        for (Bar bar : barList1) {
            System.out.println(bar.toString());
        }
    }

    public static void json2Map() {
        // Map -> JSON
        Map<String, Bar> map = new HashMap<String, Bar>();
        map.put("a", new Bar());
        map.put("b", new Bar());
        map.put("c", new Bar());
        String json = JSON.toJSONString(map, true);
        System.out.println(json);
        // JSON -> Map
        Map<String, Bar> map1 = (Map<String, Bar>)JSON.parse(json);
        for (String key : map1.keySet()) {
            System.out.println(key + ":" + map1.get(key));
        }
    }

    public static void array2JSON() {
        String[] arr_String = {"a", "b", "c"};
        String json_arr_String = JSON.toJSONString(arr_String, true);
        System.out.println(json_arr_String);
        JSONArray jsonArray = JSON.parseArray(json_arr_String);
        for (Object o : jsonArray) {
            System.out.println(o);
        }
        System.out.println(jsonArray);
    }

    public static void array2JSON2() {
        Bar[] arr_Bar = {new Bar(), new Bar(), new Bar()};
        String json_arr_Bar = JSON.toJSONString(arr_Bar, true);
        System.out.println(json_arr_Bar);
        JSONArray jsonArray = JSON.parseArray(json_arr_Bar);
        for (Object o : jsonArray) {
            System.out.println(o);
        }
        System.out.println(jsonArray);
    }

    public static void map2JSON() {
        Map map = new HashMap();
        map.put("a", "aaa");
        map.put("b", "bbb");
        map.put("c", "ccc");
        String json = JSON.toJSONString(map);
        System.out.println(json);
        Map map1 = JSON.parseObject(json);
        for (Object o : map.entrySet()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>)o;
            System.out.println(entry.getKey() + "--->" + entry.getValue());
        }
    }

    public static void listMap2JSON() {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("key1", "One");
        map1.put("key2", "Two");

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("key1", "Three");
        map2.put("key2", "Four");

        list.add(map1);
        list.add(map2);

        String listJson = JSON.toJSONString(list);
        System.out.println(listJson);
    }

    public static void bean2JSON() {

        User user = new User();
        user.setUserName("李四");
        user.setAge(24);
        String userJson = JSON.toJSONString(user);
        System.out.println(userJson);

        String userJson2 = JSON.toJSONString(user, true);
        System.out.println(userJson2);

    }

    @org.junit.Test
    public void bean2JSON2() {

        User user = new User();
        user.setUserName("李四");
//        user.setAge(24);
        String userJson = JSON.toJSONString(user);
        System.out.println(userJson);

        String userJson2 = JSON.toJSONString(user, true);
        System.out.println(userJson2);

    }

}
