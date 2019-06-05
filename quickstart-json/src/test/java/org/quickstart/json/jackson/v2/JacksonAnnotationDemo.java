/**
 * 项目名称：quickstart-json 
 * 文件名：JacksonAnnotationDemo.java
 * 版本信息：
 * 日期：2017年12月14日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.jackson.v2;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.quickstart.json.jackson.v2.annotation.TestBean;
import org.quickstart.json.jackson.v2.annotation.User;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * JacksonAnnotationDemo http://blog.csdn.net/sdyy321/article/details/40298081 http://blog.csdn.net/sdyy321/article/details/40298081
 * 
 * @author：youngzil@163.com
 * @2017年12月14日 下午11:04:15
 * @since 1.0
 */
public class JacksonAnnotationDemo {

    public static void main(String[] args) throws ParseException, IOException {
        User user = new User();
        user.setName("小民");
        user.setEmail("xiaomin@sina.com");
        user.setAge(20);

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        user.setBirthday(dateformat.parse("1996-10-01"));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);
        System.out.println(json);
        // 输出结果：{"name":"小民","birthday":"1996年09月30日","mail":"xiaomin@sina.com"}

        ObjectMapper objectMapper = new ObjectMapper();

        // MapperFeature配置和SerializationFeature配置\配置SerializationConfig(序列化)和DeserializationConfig(反序列化)方式
        objectMapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true); // 按字母顺序排序属性
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true); // 是否环绕根元素，默认false，如果为true，则默认以类名作为根元素，你也可以通过@JsonRootName来自定义根元素名称
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true); // 是否缩放排列输出，默认false，有些场合为了便于排版阅读则需要对输出做缩放排列

        // 比如如果一个类中有private Date date;这种日期属性，序列化后为：{"date" : 1413800730456}，若不为true，则为{"date" : "2014-10-20T10:26:06.604+0000"}
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true); // 序列化日期时以timestamps输出，默认true
        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true); // 序列化枚举是以toString()来输出，默认false，即默认以name()来输出

        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_INDEX, true); // 序列化枚举是以ordinal()来输出，默认false
        objectMapper.configure(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED, true); // 序列化单元素数组时不以数组来输出，默认false
        objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true); // 序列化Map时对key进行排序操作，默认false

        objectMapper.configure(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS, true); // 序列化char[]时以json数组输出，默认false
        objectMapper.configure(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN, true); // 序列化BigDecimal时之间输出原始数字还是科学计数，默认false，即是否以toPlainString()科学计数方式来输出

        // 有时候对每个实例进行可见级别的注解可能会非常麻烦，这时候我们需要配置一个全局的可见级别，通过objectMapper.setVisibilityChecker()来实现，
        // 默认的VisibilityChecker实现类为VisibilityChecker.Std，这样可以满足实现复杂场景下的基础配置。

        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY) // auto-detect all member fields
                .setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE) // but only public getters
                .setVisibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.NONE) // and none of "is-setters"
        ;
        // 你也可以通过下面方式来禁止所有的自动检测功能
        objectMapper.setVisibilityChecker(objectMapper.getVisibilityChecker().with(JsonAutoDetect.Visibility.NONE));

        String json2 = objectMapper.writeValueAsString(new TestBean());
        System.out.println(json2);

    }

    @Test
    public void enumTest() throws Exception {
        TestPOJO testPOJO = new TestPOJO();
        testPOJO.setName("myName");
        testPOJO.setMyEnum(TestEnum.ENUM01);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, false);
        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_INDEX, false);
        String jsonStr1 = objectMapper.writeValueAsString(testPOJO);
        Assert.assertEquals("{\"myEnum\":\"ENUM01\",\"name\":\"myName\"}", jsonStr1);

        ObjectMapper objectMapper2 = new ObjectMapper();
        objectMapper2.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        String jsonStr2 = objectMapper2.writeValueAsString(testPOJO);
        Assert.assertEquals("{\"myEnum\":\"enum_01\",\"name\":\"myName\"}", jsonStr2);

        ObjectMapper objectMapper3 = new ObjectMapper();
        objectMapper3.configure(SerializationFeature.WRITE_ENUMS_USING_INDEX, true);
        String jsonStr3 = objectMapper3.writeValueAsString(testPOJO);
        Assert.assertEquals("{\"myEnum\":0,\"name\":\"myName\"}", jsonStr3);
    }

    @Test
    public void singleElemArraysUnwrap() throws Exception {
        TestPOJO testPOJO = new TestPOJO();
        testPOJO.setName("myName");
        List<Integer> counts = new ArrayList<>();
        counts.add(1);
        testPOJO.setCounts(counts);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED, false);
        String jsonStr1 = objectMapper.writeValueAsString(testPOJO);
        Assert.assertEquals("{\"name\":\"myName\",\"counts\":[1]}", jsonStr1);

        ObjectMapper objectMapper2 = new ObjectMapper();
        objectMapper2.configure(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED, true);
        String jsonStr2 = objectMapper2.writeValueAsString(testPOJO);
        Assert.assertEquals("{\"name\":\"myName\",\"counts\":1}", jsonStr2);
    }

    @Test
    public void orderMapBykey() throws Exception {
        TestPOJO testPOJO = new TestPOJO();
        testPOJO.setName("myName");
        Map<String, Integer> countsMap = new HashMap<>();
        countsMap.put("a", 1);
        countsMap.put("d", 4);
        countsMap.put("c", 3);
        countsMap.put("b", 2);
        countsMap.put("e", 5);
        testPOJO.setCountsMap(countsMap);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, false);
        String jsonStr1 = objectMapper.writeValueAsString(testPOJO);
        Assert.assertEquals("{\"name\":\"myName\",\"counts\":{\"d\":4,\"e\":5,\"b\":2,\"c\":3,\"a\":1}}", jsonStr1);

        ObjectMapper objectMapper2 = new ObjectMapper();
        objectMapper2.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        String jsonStr2 = objectMapper2.writeValueAsString(testPOJO);
        Assert.assertEquals("{\"name\":\"myName\",\"counts\":{\"a\":1,\"b\":2,\"c\":3,\"d\":4,\"e\":5}}", jsonStr2);
    }

    @Test
    public void charArraysAsJsonArrays() throws Exception {
        TestPOJO testPOJO = new TestPOJO();
        testPOJO.setName("myName");
        char[] counts = new char[] {'a', 'b', 'c', 'd'};
        testPOJO.setCountsChar(counts);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS, false);
        String jsonStr1 = objectMapper.writeValueAsString(testPOJO);
        Assert.assertEquals("{\"name\":\"myName\",\"counts\":\"abcd\"}", jsonStr1);

        ObjectMapper objectMapper2 = new ObjectMapper();
        objectMapper2.configure(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS, true);
        String jsonStr2 = objectMapper2.writeValueAsString(testPOJO);
        Assert.assertEquals("{\"name\":\"myName\",\"counts\":[\"a\",\"b\",\"c\",\"d\"]}", jsonStr2);
    }

    @Test
    public void bigDecimalAsPlain() throws Exception {
        TestPOJO testPOJO = new TestPOJO();
        testPOJO.setName("myName");
        testPOJO.setCountsBig(new BigDecimal("1e20"));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN, false);
        String jsonStr1 = objectMapper.writeValueAsString(testPOJO);
        Assert.assertEquals("{\"name\":\"myName\",\"count\":1E+20}", jsonStr1);

        ObjectMapper objectMapper2 = new ObjectMapper();
        objectMapper2.configure(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN, true);
        String jsonStr2 = objectMapper2.writeValueAsString(testPOJO);
        Assert.assertEquals("{\"name\":\"myName\",\"count\":100000000000000000000}", jsonStr2);
    }

    public static class TestPOJO {
        TestPOJO() {}

        private TestEnum myEnum;

        private int id;
        @JsonIgnore
        private String name;
        private int count;

        private List<Integer> counts;

        private Map<String, Integer> countsMap;

        private char[] countsChar;

        private BigDecimal countsBig;

        // getters、setters省略

        public TestEnum getMyEnum() {
            return myEnum;
        }

        public void setMyEnum(TestEnum myEnum) {
            this.myEnum = myEnum;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Integer> getCounts() {
            return counts;
        }

        public void setCounts(List<Integer> counts) {
            this.counts = counts;
        }

        public Map<String, Integer> getCountsMap() {
            return countsMap;
        }

        public void setCountsMap(Map<String, Integer> countsMap) {
            this.countsMap = countsMap;
        }

        public char[] getCountsChar() {
            return countsChar;
        }

        public void setCountsChar(char[] countsChar) {
            this.countsChar = countsChar;
        }

        public BigDecimal getCountsBig() {
            return countsBig;
        }

        public void setCountsBig(BigDecimal countsBig) {
            this.countsBig = countsBig;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

    }

    public static enum TestEnum {
        ENUM01("enum_01"), ENUM02("enum_01"), ENUM03("enum_01");

        private String title;

        TestEnum(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return title;
        }
    }

}
