/**
 * 项目名称：quickstart-json 
 * 文件名：FastjsonExample.java
 * 版本信息：
 * 日期：2017年12月2日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.fastjson;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quickstart.json.fastjson.model.User;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * FastjsonExample
 * 
 * @author：youngzil@163.com
 * @2017年12月2日 上午11:00:29
 * @since 1.0
 */
public class FastjsonExample {

    public static void main(String[] args) {

        JSONObject json = new JSONObject();

        json.put("name", "hahha");
        json.put("age", 23);
        json.put("age", 24);
        System.out.println(json);

        // 想在哪个json对象插入数值，就用如下方法，全局json数据都会有效。
        json.fluentPut("来源", "新浪微博");
        System.out.println(json);

        // 要删除某个属性值用如下方法
        json.remove("name");
        System.out.println(json);

        // 用下列方法修改值
        json.replace("age", 25);
        System.out.println(json);

        // 传入一个对象和SerializerFeature类型的可变变量。SerializerFeature是一个枚举。

        // FastJSON可以直接对日期类型格式化，在缺省的情况下，FastJSON会将Date转成long。
        String dateJson = JSON.toJSONString(new Date(), SerializerFeature.WriteDateUseDateFormat);
        System.out.println(dateJson);

        String dateJson2 = JSON.toJSONStringWithDateFormat(new Date(), "yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println(dateJson2);

        // 使用单引号。
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("key1", "One");
        map1.put("key2", "Two");
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("key1", "Three");
        map2.put("key2", "Four");
        list.add(map1);
        list.add(map2);

        String listJson = JSON.toJSONString(list, SerializerFeature.UseSingleQuotes);
        System.out.println(listJson);

        String listJson2 = JSON.toJSONString(list, SerializerFeature.PrettyFormat);
        System.out.println(listJson2);

        // 缺省情况下FastJSON不输入为值Null的字段，可以使用SerializerFeature.WriteMapNullValue使其输出。
        Map<String, Object> map = new HashMap<String, Object>();
        String b = null;
        Integer i = 1;
        map.put("a", b);
        map.put("b", i);
        String listJson3 = JSON.toJSONString(map);
        System.out.println(listJson3);

        String listJson4 = JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
        System.out.println(listJson4);

        // 打开autotype功能
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);

        // 序列化时写入类型信息。由于序列化带了类型信息，使得反序列化时能够自动进行类型识别。
        User user = new User();
        user.setAge(18);
        user.setUserName("李四");
        String listJson5 = JSON.toJSONString(user, SerializerFeature.WriteClassName);
        System.out.println(listJson5);

        // 如果User序列化是没有加入类型信息（SerializerFeature.WriteClassName），就会报错（java.lang.ClassCastException）。
        // 大体原因就是使用fastjson的时候：序列化时将class信息写入，反解析的时候，fastjson默认情况下会开启autoType的检查，相当于一个白名单检查吧，如果序列化信息中的类路径不在autoType中，反解析就会报上面的com.alibaba.fastjson.JSONException: autoType is not support的异常
        User user1 = (User) JSON.parse(listJson5);
        System.out.println(user1.getAge());

        /*添加autotype白名单有三种方式，三选一，如下:
        1. 在代码中配置
        ParserConfig.getGlobalInstance().addAccept("com.taobao.pac.client.sdk.dataobject."); 
        如果有多个包名前缀，分多次addAccept
        2. 加上JVM启动参数
            -Dfastjson.parser.autoTypeAccept=com.taobao.pac.client.sdk.dataobject.,com.cainiao. 
        如果有多个包名前缀，用逗号隔开
        3. 通过fastjson.properties文件配置。
        在1.2.25/1.2.26版本支持通过类路径的fastjson.properties文件来配置，配置方式如下：
        fastjson.parser.autoTypeAccept=com.taobao.pac.client.sdk.dataobject.,com.cainiao. // 如果有多个包名前缀，用逗号隔开
        */
        /*如果通过配置白名单解决不了问题，可以选择继续打开autotype功能，fastjson在新版本中内置了多重防护，但是还是可能会存在一定风险。两种方法打开autotype，二选一，如下：
        1、JVM启动参数
        -Dfastjson.parser.autoTypeSupport=true
        2、代码中设置
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true); 
        如果有使用非全局ParserConfig则用另外调用setAutoTypeSupport(true)；*/

        // 反序列化就是把JSON格式的字符串转化为Java Bean对象。
        User user2 = new User();
        user2.setAge(18);
        user2.setUserName("李四");
        String userJson = JSON.toJSONString(user2);

        User user3 = JSON.parseObject(userJson, User.class);
        System.out.println(user3.getUserName());

        // List反序列化
        List<Map> list1 = JSON.parseArray(listJson, Map.class);
        for (Map<String, Object> map3 : list1) {
            System.out.println(map3.get("key1"));
            System.out.println(map3.get("key2"));
        }

        // 泛型的反序列化（使用TypeReference传入类型信息）。
        Map<String, Object> map4 = new HashMap<String, Object>();
        map4.put("key1", "One");
        map4.put("key2", "Two");
        String mapJson = JSON.toJSONString(map4);
        // 泛型的反序列化（使用TypeReference传入类型信息）。
        Map<String, Object> map5 = JSON.parseObject(mapJson, new TypeReference<Map<String, Object>>() {});
        System.out.println(map5.get("key1"));
        System.out.println(map5.get("key2"));

        /*JSONObject，JSONArray是JSON的两个子类。
        JSONObject相当于Map<String, Object>，
        JSONArray相当于List<Object>。*/

        // 将Map转成JSONObject，然后添加元素，输出。
        Map<String, Object> map6 = new HashMap<String, Object>();
        map6.put("key1", "One");
        map6.put("key2", "Two");
        JSONObject j = new JSONObject(map6);
        j.put("key3", "Three");
        System.out.println(j.get("key1"));
        System.out.println(j.get("key2"));
        System.out.println(j.get("key3"));

        // 将List对象转成JSONArray，然后输出。
        List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
        Map<String, Object> map7 = new HashMap<String, Object>();
        map7.put("key1", "One");
        map7.put("key2", "Two");
        Map<String, Object> map8 = new HashMap<String, Object>();
        map8.put("key1", "Three");
        map8.put("key2", "Four");
        list2.add(map7);
        list2.add(map8);

        JSONArray jsonarray = JSONArray.parseArray(JSON.toJSONString(list2));

        JSONArray newArray = new JSONArray();
        for (int m = 0; m < jsonarray.size(); m++) {
            System.out.println(jsonarray.get(m));
            com.alibaba.fastjson.JSONObject newObject = JSON.parseObject(JSON.toJSONString(jsonarray.get(m)));
            newObject.put("nicai", m);
            newArray.add(newObject);
        }

        System.out.println(newArray);

    }

}
