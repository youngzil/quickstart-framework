深入理解 JAVA 反序列化漏洞
1、原理
2、如何防范




1. Java 序列化与反序列化
Java 序列化是指把 Java 对象转换为字节序列的过程便于保存在内存、文件、数据库中，ObjectOutputStream类的 writeObject() 方法可以实现序列化。



原理：
JDK中反序列化实例类，如MyObject 类实现了Serializable接口，并且重写了readObject()函数。在readObject()函数中包含恶意代码
或者
Client序列化包含恶意代码的类发送给Server端，远程代码反序列化并执行了


Java 反序列化漏洞产生的原因大多数是因为反序列化时没有进行校验，或者有些校验使用黑名单方式又被绕过，最终使得包含恶意代码的序列化对象在服务器端被反序列化执行。
核心问题都不是反序列化，但都是因为反序列化导致了恶意代码被执行。 这里总结了一些近两年的 Java 反序列化漏洞：http://seclists.org/oss-sec/2017/q2/307?utm_source=dlvr.it&utm_medium=twitter



如何防范：
1、1. 类白名单校验
  在 ObjectInputStream 中 resolveClass 里只是进行了 class 是否能被 load ，自定义 ObjectInputStream , 重载 resolveClass 的方法，对 className 进行白名单校验
  
2、禁止 JVM 执行外部命令 Runtime.exec
  通过扩展 SecurityManager 可以实现:


参考
https://paper.seebug.org/312/
