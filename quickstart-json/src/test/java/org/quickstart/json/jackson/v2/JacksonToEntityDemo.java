/**
 * 项目名称：quickstart-json 
 * 文件名：JacksonToEntityDemo.java
 * 版本信息：
 * 日期：2017年12月14日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.jackson.v2;

import java.io.IOException;
import java.text.ParseException;

import org.quickstart.json.jackson.v2.pojo.User;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JacksonToEntityDemo
 * 
 * @author：youngzil@163.com
 * @2017年12月14日 下午11:01:09
 * @since 1.0
 */
public class JacksonToEntityDemo {

    public static void main(String[] args) throws ParseException, IOException {
        String json = "{\"name\":\"小民\",\"age\":20,\"birthday\":844099200000,\"email\":\"xiaomin@sina.com\"}";

        /**
         * ObjectMapper支持从byte[]、File、InputStream、字符串等数据的JSON反序列化。
         */
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(json, User.class);
        System.out.println(user);
    }

}
