package org.quickstart.json.jackson.v2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadValueTest {

    // [Jackson objectMapper.readValue 方法 详解](https://www.cnblogs.com/del88/p/13098678.html)
    // [Jackson处理复杂对象类型](https://www.cnblogs.com/flyingeagle/articles/6854138.html)
    // [Jaskson处理复杂的泛型对象-非最优方案](https://www.hicode.club/articles/2018/03/18/1550590751627.html)

    /*直接说结论方便一目了然：
        1. 简单的直接Bean.class
        2. 复杂的用 TypeReference
        */

    @Test
    public void testReadValue() throws JsonProcessingException {
         /*
          首先说明 readValue 针对String 一共有3个重载，如下：
          public <T> T readValue(String content, Class<T> valueType)；简单型，就是 直接  UserBase.class 就可。
          public <T> T readValue(String content, TypeReference<T> valueTypeRef)；复杂的可以 用这个
          public <T> T readValue(String content, JavaType valueType)；这个书写起来比较麻烦，就不说明了，不常用，前2个已经彻底满足了。
         */

        ObjectMapper objectMapper = new ObjectMapper();
        String json1 = "{\"userName\":\"小李飞刀\",\"age\":18,\"addTime\":1591851786568}";
        String json2 = "[{\"userName\":\"小李飞刀\",\"age\":18,\"addTime\":123}, {\"userName\":\"小李飞刀2\",\"age\":182,\"addTime\":1234}]";

        //1.最简单的常用方法，直接将一个json转换成实体类
        UserBase userBase1 = objectMapper.readValue(json1, UserBase.class); //简单类型的时候，这样最方便
        System.out.println("简单: " + userBase1.getUserName());
        //用 TypeReference 也可以，但是麻烦 不如第一种直接 TypeReference 主要针对繁杂类型
        //UserBase userBase2 = objectMapper.readValue(json1, new TypeReference<UserBase>() {});

        //2.把Json转换成map，必须使用 TypeReference , map的类型定义 可以根据实际情况来定，比如若值都是String那么就可以 Map<String, String>
        Map<String, Object> userBaseMap = objectMapper.readValue(json1, new TypeReference<Map<String, Object>>() {
        });
        System.out.println("map: " + userBaseMap.get("userName"));

        //3.list<Bean>模式，必须用 TypeReference
        List<UserBase> userBaseList = objectMapper.readValue(json2, new TypeReference<List<UserBase>>() {
        });
        System.out.println("list: " + userBaseList.get(0).getUserName());

        //4.Bean[] 数组，必须用 TypeReference
        UserBase[] userBaseAry = objectMapper.readValue(json2, new TypeReference<UserBase[]>() {
        });
        System.out.println("ary: " + userBaseAry[0].getUserName());
    }

    @Test
    public void testSimple() throws JsonProcessingException {
         /*
         1.最简单的常用方法，直接将一个json转换成实体类
         */
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\"userName\":\"小李飞刀\",\"age\":18,\"addTime\":1591851786568}";

        //这里需要这么写，
        UserBase userBase = objectMapper.readValue(json, UserBase.class); //简单类型的时候，这样最方便

        UserBase userBase1 = objectMapper.readValue(json, new TypeReference<UserBase>() {
        }); //这样也可以，TypeReference主要针对复杂类型

        System.out.println(userBase.getUserName());
        System.out.println(userBase1.getUserName());
    }

    @Test
    public void testMap() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        //注意这里键名和键值都是String类型的
        Map<String, String> map = new HashMap<>();
        map.put("name", "小李飞刀");
        map.put("sex", "男");

        //先生成一个json方便理解
        String json = objectMapper.writeValueAsString(map);
        System.out.println(json);//{"sex":"男","name":"小李飞刀"}

        /*
         开始反序列化
         */
        Map<String, String> map1 = new HashMap<>();
        //我之前是这么写的直接 Map.class 总觉得不妥，感觉他用了默认的推断，然后程序也能正常运行
        map1 = objectMapper.readValue(json, Map.class);
        System.out.println(map1.get("name"));

        map1 = objectMapper.readValue(json, new TypeReference<Map<String, String>>() {
        }); //用这个
        System.out.println(map1.get("name"));

    }

    @Test
    public void testListBean() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        String json = "[{\"userName\":\"小李飞刀\",\"age\":18,\"addTime\":123}, {\"userName\":\"小李飞刀2\",\"age\":182,\"addTime\":1234}]";

        // List<UserBase> userBaseList = objectMapper.readValue(json, List.class);//这样写会报错

        List<UserBase> userBaseList = objectMapper.readValue(json, new TypeReference<List<UserBase>>() {
        });

        System.out.println(userBaseList.get(0).getUserName());
    }

    @Test
    public void testArrayBean() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        String json = "[{\"userName\":\"小李飞刀\",\"age\":18,\"addTime\":123}, {\"userName\":\"小李飞刀2\",\"age\":182,\"addTime\":1234}]";

        UserBase[] userBaseAry = objectMapper.readValue(json, new TypeReference<UserBase[]>() {
        });

        System.out.println(userBaseAry[0].getUserName());
    }

    @Test
    public void testJavaType() throws JsonProcessingException {

        // Jackson 处理复杂类型(List,map)两种方法

        ObjectMapper mapper = new ObjectMapper();
        // 排除json字符串中实体类没有的字段
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String json = "[{\"name\":\"a\",\"password\":\"345\"},{\"name\":\"b\",\"password\":\"123\"}]";

        //第一种方法
        List<UserBase> list = mapper.readValue(json, new TypeReference<List<UserBase>>() {/**/
        });

        //第二种方法
        JavaType javaType = mapper.getTypeFactory().constructCollectionType(List.class, UserBase.class);
        //如果是Map类型  mapper.getTypeFactory().constructParametricType(HashMap.class,String.class, Bean.class);
        List<UserBase> list2 = mapper.readValue(json, javaType);

    }

    @Setter
    @Getter
    static class UserBase {

        /**
         * 用户名
         */
        private String userName;

        /**
         * 年龄
         */
        private Integer age;

        /**
         * 增加时间
         */
        private Date addTime;

        private String name;
        private String password;

        private List<UserBase> userBaseList;
    }
}
