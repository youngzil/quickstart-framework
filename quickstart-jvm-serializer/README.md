序列化(serialization、marshalling)的过程是指将数据结构或者对象的状态转换成可以存储(比如文件、内存)或者传输的格式(比如网络)。反向操作就是反序列化(deserialization、unmarshalling)的过程。

1987年曾经的Sun Microsystems发布了XDR。

二十世纪九十年代后期，XML开始流行，它是一种人类易读的基于文本的编码方式，易于阅读和理解，但是失去了紧凑的基于字节流的编码的优势。

JSON是一种更轻量级的基于文本的编码方式，经常用在client/server端的通讯中。

YAML类似JSON，新的特性更强大，更适合人类阅读，也更紧凑。

还有苹果系统的property list。

除了上面这些和Protobuf，还有许许多多的序列化格式，比如Thrift、Avro、BSON、CBOR、MessagePack, 还有很多非跨语言的编码格式。项目gosercomp对比了各种go的序列化库，包括序列化和反序列的性能，以及序列化后的数据大小。总体来说Protobuf序列化和反序列的性能都是比较高的，编码后的数据大小也不错。

Protobuf支持很多语言，比如C++、C#、Dart、Go、Java、Python、Rust等，同时也是跨平台的，所以得到了广泛的应用。

Protobuf包含序列化格式的定义、各种语言的库以及一个IDL编译器。正常情况下你需要定义proto文件，然后使用IDL编译器编译成你需要的语言。


fst、kryo、hessian2、protostuff、jdk、json序列化

专门针对Java语言的：Kryo，FST等等
跨语言的：Protostuff，ProtoBuf，Thrift，Avro，MsgPack等等

比如Thrift、Avro、BSON、CBOR、MessagePack,



kryo：
https://github.com/EsotericSoftware/kryo


fast-serialization
http://ruedigermoeller.github.io/fast-serialization/
https://github.com/RuedigerMoeller/fast-serialization


hessian
http://hessian.caucho.com/
http://hessian.caucho.com/doc/hessian-serialization.html



protostuff
http://www.protostuff.io/
https://github.com/protostuff/protostuff



ProtoBuf
https://developers.google.com/protocol-buffers/
https://github.com/google/protobuf



Thrift
http://thrift.apache.org/
https://github.com/apache/thrift



Avro
http://avro.apache.org/
https://github.com/apache/avro

[AVRO-通过生成类进行序列化](https://www.imangodoc.com/48786.html)
[AVRO-通过生成类进行反序列化](https://www.imangodoc.com/48788.html)
[AVRO – 快速指南](https://www.gingerdoc.com/avro/avro_quick_guide)



MsgPack
https://msgpack.org/
https://github.com/msgpack/msgpack
https://github.com/msgpack/msgpack-java

https://pypi.python.org/pypi/msgpack-python/
https://github.com/msgpack/msgpack-python




https://www.django-rest-framework.org/api-guide/serializers/


其他参考
https://github.com/eishay/jvm-serializers


https://tech.meituan.com/2015/02/26/serialization-vs-deserialization.html