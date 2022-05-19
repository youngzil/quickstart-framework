[JSON官网](https://www.json.org/json-en.html)  
[JSON官网中文](http://www.json.org/json-zh.html)  




JSON(JavaScript Object Notation)是轻量级的数据交换格式。具有良好的可读和便于快速编写的特性，可在不同平台之间进行数据交换。兼容性很高、完全独立于语言文本格式。

JSON是JavaScript Object Notation的缩写，是一种轻量级的数据交换形式，是一种XML的替代方案，而且比XML更小，更快而且更易于解析。因为JSON描述对象的时候使用的是JavaScript语法，它是语言和平台独立的，并且这些年许多JSON的解析器和类库被开发出来。在这篇文章中，我们将会展示7种Java JSON类库。基本上，我们将会试着把Java对象转换JSON格式并且存储到文件，并且反向操作，读JSON文件转换成一个对象。为了让文章更有意义，我们将会测量每一种JSON类库在不同情况下的处理速度。


参考文章  
[JSON--七种JSON框架](http://blog.csdn.net/j080624/article/details/54574594)

[JSON在线格式化网站](http://www.bejson.com)



fastjson 的 API 十分简洁。
String text = JSON.toJSONString(obj); // 序列化
VO vo = JSON.parseObject("{...}", VO.class); // 反序列化



性能测试结果：
Json-lib在数据量在10W时OOM了，内存开到1G都不行，所以直接Pass了。 
从上面图表可以看到：
字符串解析成JavaBean：当数据量较少时首选FastJson，数据量较大使用Jackson。但是Jackson无法堆一个对象集合进行解析，只能转成一个Map集合，这点Gson和FastJson处理的比较好。
字符串解析成JSON：当数据量较少时首选FastJson，数据量较大使用Jackson。
JavaBean构造JSON：当数据量较少时选择Gson，数据量较大可使用Jackson。
集合构造JSON：首先Jackson，其次Fastjson。
上面是从性能角度分析四种JSON类库，从易用性角度来分析的话，FastJson的API设计的最简单，最方便使用，直接使用JSON的两个静态方法即可完成四种操作；而Gson和Jackson都需要new一个对象，虽然这个对象可以复用，但是在实际使用过程中还需要用一个全局变量来保存改变量，同时API设计的也不是很好理解，对于FastJson来说复杂的API是因为他支持流式解析，适合对JSON进行大量且复杂的操作，但是实际应用中对于JSON的操作都是简单的解析成JavaBean，然后JavaBean序列化成JSON字符串即可，复杂的操作很少。 


Java Bean序列化为Json，性能：Jackson > FastJson > Gson > Json-lib。这4中类库的序列化结构都正确。
Json字符串反序列化为Java Bean时，性能：Jackson > Gson > FastJson >Json-lib。并且Jackson、Gson、FastJson可以很好的支持复杂的嵌套结构定义的类，而Json-lib对于复制的反序列化会出错。
Jackson、FastJson、Gson类库各有优点，各有自己的专长，都具有很高的可用性。



[JSON-lib框架，转换JSON、XML不再困难](https://www.kancloud.cn/digest/json-xml/140871)




## JSON框架

1. fastjson
2. Gson类库
3. Jackson类库
4. JSON-lib类库
5. Hutool JSON工具-JSONUtil
6. Json-smart
7. Flexjson类库
8. Json-io类库
9. Genson类库
10. JSONiJ类库

