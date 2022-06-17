# FASTJSON2介绍

## FASTJSON2文档

[FASTJSON2 Github](https://github.com/alibaba/fastjson2)

[fastjson2 文档](https://github.com/alibaba/fastjson2/wiki)



🚄 FASTJSON2是FASTJSON项目的重要升级，目标是为下一个十年提供一个高性能的JSON库



`FASTJSON v2`是`FASTJSON`项目的重要升级，目标是为下一个十年提供一个高性能的`JSON`库。通过同一套`API`，

- 支持`JSON/JSONB`两种协议，[`JSONPath`](https://alibaba.github.io/fastjson2/jsonpath_cn) 是一等公民。
- 支持全量解析和部分解析。
- 支持`Java`服务端、客户端`Android`、大数据场景。
- 支持`Kotlin` https://alibaba.github.io/fastjson2/kotlin_cn
- 支持`JSON Schema` https://alibaba.github.io/fastjson2/json_schema_cn
- 支持`Android 8+` [(2.0.7.android)](https://repo1.maven.org/maven2/com/alibaba/fastjson2/fastjson2/2.0.7.android/)
- 支持`Graal Native-Image` [(2.0.7.graal)](https://repo1.maven.org/maven2/com/alibaba/fastjson2/fastjson2/2.0.7.graal/)





相关文档：

- `JSONB`格式文档： https://alibaba.github.io/fastjson2/jsonb_format_cn
- `FASTJSON v2`性能有了很大提升，具体性能数据看这里：
  https://alibaba.github.io/fastjson2/benchmark_cn

- [kimmking:JSON Best Practice（最佳实践）](http://kimmking.github.io/2017/06/06/json-best-practice/)
- [qq_35873847:解决FastJson中“$ref 循环引用”的问题<三种方式对应不同需求>](http://blog.csdn.net/qq_35873847/article/details/78850528)



## 1.2 其他模块

### `Fastjson v1`兼容模块

如果原来使用`fastjson 1.2.x`版本，可以使用兼容包，兼容包不能保证100%兼容，请仔细测试验证，发现问题请及时反馈。



### `Fastjson Kotlin`集成模块

如果项目使用`Kotlin`，可以使用`fastjson-kotlin`模块，使用方式上采用`kotlin`的特性。



### `Fastjson Extension`扩展模块

如果项目使用`SpringFramework`等框架，可以使用`fastjson-extension`模块，使用方式参考 [SpringFramework Support](https://github.com/alibaba/fastjson2/blob/main/docs/spring_support_cn.md)。





在`fastjson v2`中，`package`和`1.x`不一样，是`com.alibaba.fastjson2`。如果你之前用的是`fastjson1`，大多数情况直接更包名就即可。



## FASTJSON 2 Autotype机制介绍

### 1. AutoType功能介绍

FASTJSON支持AutoType功能，这个功能在序列化的JSON字符串中带上类型信息，在反序列化时，不需要传入类型，实现自动类型识别。

### 2. AutoType安全机制介绍

- 必须显式打开才能使用。和fastjson 1.x不一样，fastjson 1.x为了兼容有一个白名单，在fastjson 2中，没有任何白名单，也不包括任何Exception类的白名单，必须显式打开才能使用。这可以保证缺省配置下是安全的。
- 支持配置safeMode，在safeMode打开后，显式传入AutoType参数也不起作用
- 显式打开后，会经过内置黑名单过滤。该黑名单能拦截大部分常见风险，这个机制不能保证绝对安全，打开AutoType不应该在暴露在公网的场景下使用。










## 使用参考

[官方说明文档](https://github.com/alibaba/fastjson2)

[FASTJSON 2.0 发布，FASTJSON 项目的重要升级](https://www.oschina.net/news/191783/fastjson2-released)


[Jackson与Fastjson的恩怨情仇（完结篇）](https://blog.51cto.com/u_3631118/3153843)
