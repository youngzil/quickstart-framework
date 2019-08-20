1、
2、

---------------------------------------------------------------------------------------------------------------------

http://projectreactor.io
http://www.reactive-streams.org/
https://github.com/reactor/reactor-core



Reactor 是一个运行在 Java8 之上的响应式流框架，它提供了一组响应式风格的 API


Flux 和 Mono
Flux 和 Mono 是 Reactor 中的两个基本概念。

Flux 表示的是包含 0 到 N 个元素的异步序列。在该序列中可以包含三种不同类型的消息通知：正常的包含元素的消息、序列结束的消息和序列出错的消息。当消息通知产生时，订阅者中对应的方法 onNext(), onComplete()和 onError()会被调用。
Mono 表示的是包含 0 或者 1 个元素的异步序列。该序列中同样可以包含与 Flux 相同的三种类型的消息通知。

Flux 和 Mono 之间可以进行转换。
对一个 Flux 序列进行计数操作，得到的结果是一个 Mono<Long>对象。
把两个 Mono 序列合并在一起，得到的是一个 Flux 对象。






示例参考
https://www.infoq.cn/article/reactor-by-example
https://www.infoq.com/articles/reactor-by-example/
https://www.ibm.com/developerworks/cn/java/j-cn-with-reactor-response-encode/index.html
https://zhuanlan.zhihu.com/p/55974997


Mono使用
https://blog.csdn.net/songhaifengshuaige/article/details/79248343


---------------------------------------------------------------------------------------------------------------------
TCP/HTTP/UDP client/server with Reactor over Netty http://projectreactor.io
https://github.com/reactor/reactor-netty

---------------------------------------------------------------------------------------------------------------------