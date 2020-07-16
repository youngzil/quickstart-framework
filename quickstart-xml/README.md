1》底层解析方式：存在编码复杂性、难扩展、难复用....。想了解底层解析方式请参考：浅谈 Java XML 底层解析方式

2》Dom4j：基于 JAXP 解析方式，性能优异、功能强大、极易使用的优秀框架。

3》Jdom：本质也是基于 JAXP 但包结构被重新组织， API 大量使用了 Collections 类，在性能上被 dm4j 压了好几个档次。

4》XStream：基于 xmlpull 的 OXMapping 技术，更加倾向于将 XML 解析后映射为 Java 世界中的对象。




https://dom4j.github.io/
https://github.com/dom4j/dom4j




http://www.jdom.org/
https://github.com/hunterhacker/jdom/



使用XStream解析MXL文件:依赖xmlpull-1.1.3.1.jar和xpp3_min-1.1.4c.jar
总结：
   (1) XStream是一个可以将javaBean与XML双向转换的java类库。
   (2) Java到xml，使用toXML()方法；xml到Java，用fromXML()方法。
   (3) 类别名，用alias(String name, Class type)。
   (4) 类成员别名，用aliasField(String alias, Class definedIn, String fieldName)。
   (5) 将某一个类的属性，作为xml头信息的属性，而不是子节点，用useAttributeFor(Class definedIn, String fieldName)。
   (5) 类成员作为属性别名，用 aliasAttribute(Class definedIn, String attributeName, String alias)，单独命名没有意义，还要通过useAttributeFor(Class definedIn, String fieldName) 应用到某个类上。
   (6) XStream默认当String类型的属性值为null时不封装到xml中。可以根据实际传xml情况，选择对象属性set空字符串还是null。


Pull XML解析器早已经被google集成到android sdk当中，它是google官方推荐的解析器。
如果我们要在Java桌面、J2ME等当中使用Pull方式生成xml文件和解析xml文件，需要用到kxml2；
KXML解析器是基于普通XML PULL解析器的一个小巧的解析器，官网是http://kxml.org/
普通XML PULL解析器的官网是http://xmlpull.org/





示例参考
https://blog.csdn.net/qq_36769100/article/details/82768723



https://www.cnblogs.com/aspirant/p/10299004.html
https://blog.csdn.net/lzghxjt/article/details/54564382



