/**
 * 项目名称：quickstart-json 
 * 文件名：TestBean2Json.java
 * 版本信息：
 * 日期：2017年12月13日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.quickstart.json.test.model.FullName;
import org.quickstart.json.test.model.Person;
import org.quickstart.json.test.util.FastJsonUtil;
import org.quickstart.json.test.util.GsonUtil;
import org.quickstart.json.test.util.JacksonV1Util;
import org.quickstart.json.test.util.JsonlibUtil;

/**
 * TestBean2Json Java对象序列化为Json字符串 测试类
 * 
 * @author：youngzil@163.com
 * @2017年12月13日 下午10:41:17
 * @since 1.0
 */
public class TestBean2Json {

    private Person p;

    private Person createAPerson(String name, List<Person> friends) {
        Person newPerson = new Person();
        newPerson.setName(name);
        newPerson.setFullName(new FullName("xxx_first", "xxx_middle", "xxx_last"));
        newPerson.setAge(24);
        List<String> hobbies = new ArrayList<String>();
        hobbies.add("篮球");
        hobbies.add("游泳");
        hobbies.add("coding");
        newPerson.setHobbies(hobbies);
        Map<String, String> clothes = new HashMap<String, String>();
        clothes.put("coat", "Nike");
        clothes.put("trousers", "adidas");
        clothes.put("shoes", "安踏");
        newPerson.setClothes(clothes);
        newPerson.setFriends(friends);
        return newPerson;
    }

    @Before
    public void init() {
        List<Person> friends = new ArrayList<Person>();
        friends.add(createAPerson("小明", null));
        friends.add(createAPerson("Tony", null));
        friends.add(createAPerson("陈小二", null));
        p = createAPerson("邵同学", friends);
    }

    // @Test
    public void testGsonBean2Json() {
        System.out.println(GsonUtil.bean2Json(p));

        for (int i = 0; i < 1000000; i++) {
            GsonUtil.bean2Json(p);
        }
    }

    // @Test
    public void testJsonObjectBean2Json() {
        System.out.println(JsonlibUtil.bean2Json(p));

        for (int i = 0; i < 1000000; i++) {
            JsonlibUtil.bean2Json(p);
        }
    }

    // @Test
    public void testJacksonBean2Json() throws Exception {
        System.out.println(JacksonV1Util.bean2Json(p));

        for (int i = 0; i < 1000000; i++) {
            JacksonV1Util.bean2Json(p);
        }
    }

    @Test
    public void testFastJsonBean2Json() throws Exception {
        System.out.println(FastJsonUtil.bean2Json(p));

        for (int i = 0; i < 1000000; i++) {
            FastJsonUtil.bean2Json(p);
        }
    }

}
