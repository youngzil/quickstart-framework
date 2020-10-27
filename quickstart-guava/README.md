Guava

[Guava Github](https://github.com/google/guava)

Google core libraries for Java


Guava是Java领域优秀的开源项目，它包含了Google在Java项目中使用一些核心库，  
包含集合(Collections)，缓存(Caching)，并发编程库(Concurrency)，常用注解(Common annotations)，String操作，I/O操作方面的众多非常实用的函数。  
Guava的RateLimiter提供了令牌桶算法实现：平滑突发限流(SmoothBursty)和平滑预热限流(SmoothWarmingUp)实现。


Guava工程包含了若干被Google的 Java项目广泛依赖 的核心库，例如：集合 [collections] 、缓存 [caching] 、原生类型支持 [primitives support] 、并发库 [concurrency libraries] 、通用注解 [common annotations] 、字符串处理 [string processing] 、I/O 等等。 所有这些工具每天都在被Google的工程师应用在产品服务中。



Guava提供了若干方法，来判断异常类型并且重新传播异常。  
如果调用者想让异常传播到栈顶，他不需要写任何catch代码块。因为他不打算从异常中恢复，他可能就不应该记录异常，或者有其他的动作。  
受检异常和非受检异常



参考文章  
[Google Guava官方教程（中文版）](http://ifeve.com/google-guava/)

[超详细的Guava RateLimiter限流原理解析](https://cloud.tencent.com/developer/article/1408819)

[超详细的Guava RateLimiter限流原理解析](http://remcarpediem.net/2019/03/30/%E8%B6%85%E8%AF%A6%E7%BB%86%E7%9A%84Guava-RateLimiter%E9%99%90%E6%B5%81%E5%8E%9F%E7%90%86%E8%A7%A3%E6%9E%90/)

[Guava StringsExplained](https://github.com/google/guava/wiki/StringsExplained)



