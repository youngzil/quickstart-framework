fast字段基本只能是基础数据类型


如果有个字段是Set类型，但是传过来的是""空字符串就会报错，解析不了，如果是null还可以解析





https://github.com/alibaba/fastjson

https://github.com/alibaba/fastjson/wiki
https://github.com/Alibaba/fastjson/wiki/%E9%A6%96%E9%A1%B5

https://my.oschina.net/jsonavaj/blog/1573833


http://www.oschina.net/p/fastjson

https://github.com/eishay/jvm-serializers


fastjson 详细介绍
fastjson 是一个性能很好的 Java 语言实现的 JSON 解析器和生成器，来自阿里巴巴的工程师开发。

主要特点：
快速FAST (比其它任何基于Java的解析器和生成器更快，包括jackson）
强大（支持普通JDK类包括任意Java Bean Class、Collection、Map、Date或enum）
零依赖（没有依赖其它任何类库除了JDK）




FastJson（项目地址：https://github.com/alibaba/fastjson）。Fastjson是一个Java语言编写的高性能的JSON处理器,由阿里巴巴公司开发。无依赖，不需要例外额外的jar，能够直接跑在JDK上。FastJson在复杂类型的Bean转换Json上会出现一些问题，可能会出现引用的类型，导致Json转换出错，需要制定引用。FastJson采用独创的算法，将parse的速度提升到极致，超过所有json库。

Fastjson API入口类是com.alibaba.fastjson.JSON，常用的序列化操作都可以在JSON类上的静态方法直接完成。
public static final Object parse(String text); // 把JSON文本parse为JSONObject或者JSONArray 
public static final JSONObject parseObject(String text)； // 把JSON文本parse成JSONObject    
public static final <T> T parseObject(String text, Class<T> clazz); // 把JSON文本parse为JavaBean 
public static final JSONArray parseArray(String text); // 把JSON文本parse成JSONArray 
public static final <T> List<T> parseArray(String text, Class<T> clazz); //把JSON文本parse成JavaBean集合 
public static final String toJSONString(Object object); // 将JavaBean序列化为JSON文本 
public static final String toJSONString(Object object, boolean prettyFormat); // 将JavaBean序列化为带格式的JSON文本 
public static final Object toJSON(Object javaObject); 将JavaBean转换为JSONObject或者JSONArray。
 
 
 3、有关类库的一些说明
SerializeWriter：相当于StringBuffer
JSONArray：相当于List<Object>
JSONObject：相当于Map<String, Object>
 
JSON反序列化没有真正数组，本质类型都是List<Object>

fastjson还有很多很高级的特性，比如支持注解、支持全类型序列化，这些都是很好的特性，功能强大，不在本次测试范围。






