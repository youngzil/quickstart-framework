/**
 * 项目名称：quickstart-json 
 * 文件名：TestJson2Bean.java
 * 版本信息：
 * 日期：2017年12月13日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.test;

import org.junit.Before;
import org.junit.Test;
import org.quickstart.json.test.model.Person;
import org.quickstart.json.test.util.FastJsonUtil;
import org.quickstart.json.test.util.GsonUtil;
import org.quickstart.json.test.util.JacksonV1Util;
import org.quickstart.json.test.util.JsonlibUtil;

/**
 * TestJson2Bean Json字符串 反序列化为Java对象 测试类：
 * 
 * @author：youngzil@163.com
 * @2017年12月13日 下午10:47:06
 * @since 1.0
 */
public class TestJson2Bean {
    private String jsonStr;

    @Before
    public void init() {
        jsonStr =
                "{\"name\":\"邵同学\",\"fullName\":{\"firstName\":\"xxx_first\",\"middleName\":\"xxx_middle\",\"lastName\":\"xxx_last\"},\"age\":24,\"birthday\":null,\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"clothes\":{\"shoes\":\"安踏\",\"trousers\":\"adidas\",\"coat\":\"Nike\"},\"friends\":[{\"name\":\"小明\",\"fullName\":{\"firstName\":\"xxx_first\",\"middleName\":\"xxx_middle\",\"lastName\":\"xxx_last\"},\"age\":24,\"birthday\":null,\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"clothes\":{\"shoes\":\"安踏\",\"trousers\":\"adidas\",\"coat\":\"Nike\"},\"friends\":null},{\"name\":\"Tony\",\"fullName\":{\"firstName\":\"xxx_first\",\"middleName\":\"xxx_middle\",\"lastName\":\"xxx_last\"},\"age\":24,\"birthday\":null,\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"clothes\":{\"shoes\":\"安踏\",\"trousers\":\"adidas\",\"coat\":\"Nike\"},\"friends\":null},{\"name\":\"陈小二\",\"fullName\":{\"firstName\":\"xxx_first\",\"middleName\":\"xxx_middle\",\"lastName\":\"xxx_last\"},\"age\":24,\"birthday\":null,\"hobbies\":[\"篮球\",\"游泳\",\"coding\"],\"clothes\":{\"shoes\":\"安踏\",\"trousers\":\"adidas\",\"coat\":\"Nike\"},\"friends\":null}]}";
    }

    // @Test
    public void testGsonjson2Bean() throws Exception {
        Person pp = GsonUtil.json2Bean(jsonStr, Person.class);
        System.out.println(pp);

        for (int i = 0; i < 1000000; i++) {
            GsonUtil.json2Bean(jsonStr, Person.class);
        }
    }

    // @Test
    public void testJsonlibJson2Bean() throws Exception {
        Person pp = JsonlibUtil.json2Bean(jsonStr, Person.class);
        System.out.println(pp);

        for (int i = 0; i < 1000000; i++) {
            JsonlibUtil.json2Bean(jsonStr, Person.class);
        }
    }

    // @Test
    public void testJacksonJson2Bean() throws Exception {
        Person pp = JacksonV1Util.json2Bean(jsonStr, Person.class);
        System.out.println(pp);

        for (int i = 0; i < 1000000; i++) {
            JacksonV1Util.json2Bean(jsonStr, Person.class);
        }
    }

    @Test
    public void testFastJsonJson2Bean() throws Exception {
        Person pp = FastJsonUtil.json2Bean(jsonStr, Person.class);
        System.out.println(pp);

        for (int i = 0; i < 1000000; i++) {
            FastJsonUtil.json2Bean(jsonStr, Person.class);
        }
    }
}
