/**
 * 项目名称：quickstart-json 
 * 文件名：FastJsonTest.java
 * 版本信息：
 * 日期：2017年12月13日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.fastjson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quickstart.json.fastjson.model.UserInfo;

import com.alibaba.fastjson.JSON;

/**
 * FastJsonTest
 * 
 * @author：youngzil@163.com
 * @2017年12月13日 下午10:28:49
 * @since 1.0
 */
public class FastJsonTest {

    public static void main(String[] args) {
        mapAndJson1(); // map<-->JSON
        objectAndJson(); // Object<-->JSON
        listObjectAndJson();// List<VO><-->JSON
        listIntAndJson(); // List<Integer><---->JSON
    }

    // 1.map<-->JSON
    public static void mapAndJson1() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        String jsonStr = JSON.toJSONString(map);
        System.out.println(jsonStr); // {"key2":"value2","key1":"value1"}

        Map<String, String> mapFromJson = (Map<String, String>) JSON.parse(jsonStr);
        String value1 = mapFromJson.get("key1");
        String value2 = mapFromJson.get("key2");
        System.out.println("value1->" + value1 + ",value2->" + value2); // value1->value1,value2->value2

        /**
         * key2:value2 key1:value1
         */
        for (String key : mapFromJson.keySet()) {
            System.out.println(key + ":" + mapFromJson.get(key));
        }
    }

    // 2.Object<-->JSON
    public static void objectAndJson() {
        UserInfo userInfo = new UserInfo();
        userInfo.setAge(30);
        userInfo.setName("name1");
        String jsonStr = JSON.toJSONString(userInfo);
        System.out.println(jsonStr); // {"age":30,"username":"name1"}
        UserInfo userInfoFromJson = JSON.parseObject(jsonStr, UserInfo.class);
        System.out.println("userInfoFromJson.getAge():" + userInfoFromJson.getAge() + ",userInfoFromJson.getName():" + userInfoFromJson.getName());// userInfoFromJson.getAge():30,userInfoFromJson.getName():name1
    }

    // 3.List<VO><-->JSON
    public static void listObjectAndJson() {
        UserInfo userInfo1 = new UserInfo();
        userInfo1.setAge(30);
        userInfo1.setName("name1");
        UserInfo userInfo2 = new UserInfo();
        userInfo2.setAge(20);
        userInfo2.setName("name2");
        List<UserInfo> userInfoList = new ArrayList<UserInfo>();
        userInfoList.add(userInfo1);
        userInfoList.add(userInfo2);
        String jsonStr = JSON.toJSONString(userInfoList);
        List<UserInfo> userInfoListFromJson = JSON.parseArray(jsonStr, UserInfo.class);
        System.out.println("userInfoListFromJson.size()->" + userInfoListFromJson.size()); // userInfoListFromJson.size()->2

        for (UserInfo userInfoTemp : userInfoListFromJson) {
            /*
              userInfoTemp,age:30,name:name1
              userInfoTemp,age:20,name:name2
             */
            System.out.println("userInfoTemp,age:" + userInfoTemp.getAge() + ",name:" + userInfoTemp.getName());
        }
    }

    // 4.List<Integer><---->JSON
    public static void listIntAndJson() {
        List<Object> objectList = new ArrayList<Object>();
        objectList.add(1);;
        objectList.add("strobject");
        String jsonStr = JSON.toJSONString(objectList);
        System.out.println("jsonStr->" + jsonStr); // jsonStr->[1,"strobject"]

        List<Object> objectFromJson = JSON.parseArray(jsonStr, Object.class);
        for (Object object : objectFromJson) {
            /*
                object:1
                object:strobject
             */
            System.out.println("object:" + object);
        }
    }

}
