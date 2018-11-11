/**
 * 项目名称：quickstart-json 
 * 文件名：JacksonToJsonDemo.java
 * 版本信息：
 * 日期：2017年12月14日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.jackson.v2;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.quickstart.json.jackson.v2.pojo.User;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JacksonToJsonDemo
 * 
 * @author：youngzil@163.com
 * @2017年12月14日 下午11:00:25
 * @since 1.0
 */
public class JacksonToJsonDemo {

    public static void main(String[] args) throws ParseException, IOException {
        User user = new User();
        user.setName("小民");
        user.setEmail("xiaomin@sina.com");
        user.setAge(20);

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        user.setBirthday(dateformat.parse("1996-10-01"));

        /**
         * ObjectMapper是JSON操作的核心，Jackson的所有JSON操作都是在ObjectMapper中实现。 ObjectMapper有多个JSON序列化的方法，可以把JSON字符串保存File、OutputStream等不同的介质中。 writeValue(File arg0, Object arg1)把arg1转成json序列，并保存到arg0文件中。
         * writeValue(OutputStream arg0, Object arg1)把arg1转成json序列，并保存到arg0输出流中。 writeValueAsBytes(Object arg0)把arg0转成json序列，并把结果输出成字节数组。 writeValueAsString(Object arg0)把arg0转成json序列，并把结果输出成字符串。
         */
        ObjectMapper mapper = new ObjectMapper();

        // User类转JSON
        // 输出结果：{"name":"小民","age":20,"birthday":844099200000,"email":"xiaomin@sina.com"}
        String json = mapper.writeValueAsString(user);
        System.out.println(json);

        // Java集合转JSON
        // 输出结果：[{"name":"小民","age":20,"birthday":844099200000,"email":"xiaomin@sina.com"}]
        List<User> users = new ArrayList<User>();
        users.add(user);
        String jsonlist = mapper.writeValueAsString(users);
        System.out.println(jsonlist);
    }

}
