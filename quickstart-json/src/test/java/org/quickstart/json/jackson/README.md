Jackson 2.x
https://github.com/FasterXML/jackson

http://wiki.fasterxml.com/JacksonInFiveMinutes Jackson官方教程示例
http://wiki.fasterxml.com/JacksonJavaDocs Jackson在线API文档
官方WIKI：https://github.com/FasterXML/jackson-databind/wiki
http://wiki.fasterxml.com/JacksonFeaturesSerialization
http://wiki.fasterxml.com/JacksonFeaturesDeserialization

参考文章
http://blog.csdn.net/u010376788/article/details/51169888

注解参考文章
http://blog.csdn.net/sdyy321/article/details/40298081
https://www.cnblogs.com/moon521/p/6179342.html


Jackson可以轻松的将Java对象转换成json对象和xml文档，同样也可以将json、xml转换成Java对象。Jackson库于2012.10.8号发布了最新的2.1版。
Jackson源码目前托管于GitHub，地址：https://github.com/FasterXML/
一、Jackson 2.x介绍

jackson主要的包
jackson-core——核心包（必须），提供基于“流模式”解析的API。核心包：JsonPaser（json流读取），JsonGenerator（json流输出）。
jackson-databind——数据绑定包（可选），提供基于“对象绑定”和“树模型”相关API。数据绑定包：ObjectMapper（构建树模式和对象绑定模式），JsonNode（树节点）。
jackson-annotations——注解包（可选），提供注解功能。
jackson-datatype-joda-2.1.5.jar——日期转换

Jackson 2.x版提供了三个JAR包供下载：
1. Core库：streaming parser/generator，即流式的解析器和生成器。
下载：
http://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-core/2.1.0/jackson-core-2.1.0.jar

2. Annotations库：databinding annotations，即带注释的数据绑定包。
下载：
http://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/2.1.0/jackson-annotations-2.1.0.jar

3. Databind库：ObjectMapper, Json Tree Model，即对象映射器，JSON树模型。
下载：
http://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/2.1.0/jackson-databind-2.1.0.jar

从Jackson 2.0起，
核心组件包括：jackson-annotations、jackson-core、jackson-databind。
数据格式模块包括：Smile、CSV、XML、YAML。



Jackson（项目地址：https://github.com/FasterXML/jackson）。相比json-lib框架，Jackson所依赖的jar包较少，简单易用并且性能也要相对高些。而且Jackson社区相对比较活跃，更新速度也比较快。Jackson对于复杂类型的json转换bean会出现问题，一些集合Map，List的转换出现问题。Jackson对于复杂类型的bean转换Json，转换的json格式不是标准的Json格式。


Jackson具有比较高的序列化和反序列化效率，据测试，无论是哪种形式的转换，Jackson > Gson > Json-lib，而且Jackson的处理能力甚至高出Json-lib近10倍左右，且正确性也十分高。相比之下，Json-lib似乎已经停止更新，最新的版本也是基于JDK15，而Jackson的社区则较为活跃。

ObjectMapper类的writeValueAsString、valueToTree、readTree等方法
ObjectMapper mapper = new ObjectMapper();
String  jsonStr=mapper.writeValueAsString(A);//A是任何一个自定的java类，或者是java类库的类如map   //得到的是一个json格式的String (字符型的json)
JsonNode jn=mapper.valueToTree(B);//B是任何一个自定的java类，或者是java类库的类如map //得到的是一个JsonNode类型（这个是Jackson框架jar包的一个类型）
JsonNode jn=mapper.readTree(C);//C是一个json格式String。//得到的是一个JsonNode类型（这个是Jackson框架jar包的一个类型）
List list=mapper.readValue(D,List.class);//D是一个json格式String。//得到的是一个List类型。
问：我怎么记住，每个方法得到的是什么？
记忆技巧：方法的最末的单词（即标红的位置）意思是什么，得到的就是什么。两者一样。


JSON的三种处理方式  Jackson提供了三种可选的JSON处理方法（一种方式及其两个变型）：
流式 API：（也称为"增量分析/生成"） 读取和写入 JSON 内容作为离散事件。
org.codehaus.jackson.JsonParser 读， org.codehaus.jackson.JsonGenerator 写。
StAX API 的激励。

树模型 ：提供一个 JSON 文档可变内存树的表示形式。
org.codehaus.jackson.map.ObjectMapper 生成树 ；树组成 JsonNode 节点集。
树模型类似于 XML DOM。

数据绑定： JSON和POJO相互转换，基于属性访问器规约或注解。
有 两种变体： 简单 和 完整 的数据绑定：
简单数据绑定： 是指从Java Map、List、String、Numbers、Boolean和空值进行转换
完整数据绑定 ：是指从任何 Java bean 类型 （及上文所述的"简单"类型） 进行转换
org.codehaus.jackson.map.ObjectMapper 对两个变种，进行编组（marshalling ）处理 （写入 JSON） 和反编组（unmarshalling ，读 JSON）。
JAXB激励下的基于注释的 （代码优先）变种。

从使用的角度来看，总结这些3 种方法的用法如下：
流 API： 性能最佳的方式 （最低开销、 速度最快的读/写； 其它二者基于它实现）。
数据绑定 ：使用最方便的方式。
树模型： 最灵活的方式。







Jackson 1.x

官网：http://jackson.codehaus.org/Home
http://www.codehaus.org/

jackson API文档：http://tool.oschina.net/apidocs/apidoc?api=jackson-1.9.9

https://github.com/codehaus/jackson

参考文章
http://blog.csdn.net/tanga842428/article/details/54864212

二、Jackson 1.9.X介绍
1. 单个库下载：提供了core-asl、mapper-asl、core-lpgl、mapper-lgpl、jax-rs、jax-xc、mrbean、smile等JAR包下载。

2. Jackson ALL库：包含了上面所有的JAR包，打包成了单个JAR文件。
下载：
http://jackson.codehaus.org/1.9.10/jackson-all-1.9.10.jar

3. Jackson Mini库：包含了jackson-core库，排除了注释库、许可证文件、用于使用受限的环境，比如移动设备，JAR包的尺寸显著减少。
下载：
http://jackson.codehaus.org/1.9.10/jackson-mini-1.9.10.jar

4. Smile Tool工具：一个命令行工具，用于在Smile格式和JSON格式之间相互转换。
下载：
http://jackson.codehaus.org/1.9.10/smile-tool-1.9.10.jar

注：Smile是二进制的JSON数据格式，等同于标准的JSON数据格式。Smile格式于2010年发布，于2010年9月Jackson 1.6版开始支持。

支持Smile格式的框架：
(1) Jackson JSON Processor：完全支持Smile格式，包括流式访问，数据绑定和树模型。
(2) libsmile：一个C语言库，支持读写Smile数据。
(3) Elastic Search：支持把Smile格式作为输入/输出的源。
(4) Protostuff：此项目支持Smile格式作为底层数据格式，也用于RPC的格式之一。



