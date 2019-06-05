/**
 * 项目名称：quickstart-json 
 * 文件名：GsonExample.java
 * 版本信息：
 * 日期：2017年12月2日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.gson;

import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * GsonExample
 * 
 * @author：youngzil@163.com
 * @2017年12月2日 上午11:00:08
 * @since 1.0
 */
public class GsonExample {

    @Test
    public void testJson() {

        // 要创建JSON格式的数据，首先要创建一个整体的JSON的对象，作为一个容器
        JsonObject jsonObject = new JsonObject();

        // 如果要为当前的JSON对象添加另一个JSON对象，使用add()方法
        // 如果要为当前的JSON对象添加属性值（键值对），使用addProperty()方法
        jsonObject.addProperty("category", "it");

        // 接下来构建JSON数组，名称是 languages
        JsonArray jsonArraysonArray = new JsonArray();

        JsonObject lan1 = new JsonObject();
        lan1.addProperty("id", 1);
        lan1.addProperty("name", "Java");
        lan1.addProperty("ide", "Eclipse");
        // 将 lan1 添加到 array
        jsonArraysonArray.add(lan1);

        JsonObject lan2 = new JsonObject();
        lan2.addProperty("id", 2);
        lan2.addProperty("name", "Swift");
        lan2.addProperty("ide", "Xcode");
        // 将 lan2 添加到 array
        jsonArraysonArray.add(lan2);

        JsonObject lan3 = new JsonObject();
        lan3.addProperty("id", 3);
        lan3.addProperty("name", "C#");
        lan3.addProperty("ide", "Visual Studio");
        // 将 lan3 添加到 array
        jsonArraysonArray.add(lan3);

        // 将 array 添加到 object，指定 array 的名称： languages（键）
        jsonObject.add("languages", jsonArraysonArray);

        // 添加最后一个属性：pop
        jsonObject.addProperty("pop", true);

        // 创建完毕，转换成字符串
        System.out.println(jsonObject.toString());

    }

}
