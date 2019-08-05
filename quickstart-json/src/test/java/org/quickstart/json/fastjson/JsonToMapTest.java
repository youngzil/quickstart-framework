package org.quickstart.json.fastjson;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-08-03 12:03
 */
public class JsonToMapTest {

  public static void main(String[] args) {

    String str = "{\"0\":\"zhangsan\",\"1\":\"lisi\",\"2\":\"wangwu\",\"3\":\"maliu\"}";
    // 第一种方式
    Map maps = (Map) JSON.parse(str);
    System.out.println("这个是用JSON类来解析JSON字符串!!!");
    for (Object map : maps.entrySet()) {
      System.out.println(((Map.Entry) map).getKey() + "     " + ((Map.Entry) map).getValue());
    }
    // 第二种方式
    Map mapTypes = JSON.parseObject(str);
    System.out.println("这个是用JSON类的parseObject来解析JSON字符串!!!");
    for (Object obj : mapTypes.keySet()) {
      System.out.println("key为：" + obj + "值为：" + mapTypes.get(obj));
    }
    // 第三种方式
    Map mapType = JSON.parseObject(str, Map.class);
    System.out.println("这个是用JSON类,指定解析类型，来解析JSON字符串!!!");
    for (Object obj : mapType.keySet()) {
      System.out.println("key为：" + obj + "值为：" + mapType.get(obj));
    }
    // 第四种方式
    /**
     * JSONObject是Map接口的一个实现类
     */
    Map json = (Map) JSONObject.parse(str);
    System.out.println("这个是用JSONObject类的parse方法来解析JSON字符串!!!");
    for (Object map : json.entrySet()) {
      System.out.println(((Map.Entry) map).getKey() + "  " + ((Map.Entry) map).getValue());
    }
    // 第五种方式
    /**
     * JSONObject是Map接口的一个实现类
     */
    JSONObject jsonObject = JSONObject.parseObject(str);
    System.out.println("这个是用JSONObject的parseObject方法来解析JSON字符串!!!");
    for (Object map : json.entrySet()) {
      System.out.println(((Map.Entry) map).getKey() + "  " + ((Map.Entry) map).getValue());
    }
    // 第六种方式
    /**
     * JSONObject是Map接口的一个实现类
     */
    Map mapObj = JSONObject.parseObject(str, Map.class);
    System.out.println("这个是用JSONObject的parseObject方法并执行返回类型来解析JSON字符串!!!");
    for (Object map : json.entrySet()) {
      System.out.println(((Map.Entry) map).getKey() + "  " + ((Map.Entry) map).getValue());
    }

    String strArr = "{{\"0\":\"zhangsan\",\"1\":\"lisi\",\"2\":\"wangwu\",\"3\":\"maliu\"},"
        + "{\"00\":\"zhangsan\",\"11\":\"lisi\",\"22\":\"wangwu\",\"33\":\"maliu\"}}";
    // JSONArray.parse()
    System.out.println(json);
  }

  /**
   * 将json转化成map
   * 
   * @param jsonStr
   * @return
   */
  public static Map<String, Object> convertJsonStrToMap(String jsonStr) {

    Map<String, Object> map = JSON.parseObject(jsonStr, new TypeReference<Map<String, Object>>() {});

    return map;
  }

  @Test
  public void testToMap() {

    String jsonData = "{id:100,list:[{a:1},{a:2}]";
    Map<String, Object> map = convertJsonStrToMap(jsonData);
    System.out.println(map);
    Integer id = MapUtils.getInteger(map, "id");
    Object list = MapUtils.getObject(map, "list");
    List<JSONObject> ll = (List) list;
    for (JSONObject s : ll) {
      String eleVal = s.get("a").toString();
      System.out.println(s + "---a的值是：" + eleVal);
    }

  }

}
