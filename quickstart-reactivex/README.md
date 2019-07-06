官网：http://reactivex.io/ 
http://reactivex.io/intro.html
Github：https://github.com/ReactiveX/RxJava
doc文档：http://reactivex.io/RxJava/javadoc/
文档中文版：https://mcxiaoke.gitbooks.io/rxdocs/content/
文档中文版：https://legacy.gitbook.com/book/mcxiaoke/rxdocs/details



Github：https://github.com/ReactiveX/RxJava 
RxDoc：http://reactivex.io/RxJava/2.x/javadoc/ 
RxJava2Demo：https://github.com/linzhiyong/RxJava2Demo


ReactiveX其他框架
https://github.com/ReactiveX/RxNetty
https://github.com/ReactiveX/RxJavaAsyncUtil
https://github.com/ReactiveX/RxApacheHttp
https://github.com/ReactiveX/RxJavaGuava



ReactiveX简介：
An API for asynchronous programming with observable streams。

分解一下这个句子：
API: 首先它是个编程接口，不同语言提供不同实现。例如JVM语言中的RxJava。
For asynchronous programming: 使用场景，异步变成环境中。
With observable streams: 基于可观察的事件流

事实上，在异步协作（编程）中，所有的协作都是基于事件机制，通过事件发布与订阅机制，实现双方的解耦。流的概念则与流式中的概念是一样的，流意味中源源不断，只要有流到来，就会启动相应的处理流程。

ReactiveX组合了以下3者： 
1. 观察者模式：观察感兴趣的目标并在特定事件发生时做出反应。 
2. Iterator模式：迭代，其实就是流式的概念，不断迭代、循环、处理。 
3. 函数式变成： 在针对目标事件做出反应的时候，使用函数式编程的方式进行相应。

简单的说, ReactiveX (Rx) 是一个简化异步调用的库. Rx是从微软的函数响应式编程库(Reactive Extensions)发展而来的, 提供了一种新的组织和协调异步事件的方式. 例如协调多个从网络上返回的多个异步的数据流, Rx能够是的我们用一个简单的方式来处理这些数据流, 极大的简化了代码的编写.
Rx作为一个通用库, 现在已经多种语言的实现版本(都是开源的), 包含RxJava, RxCpp, RxSwift, RxKotlin, RxGroovy, RxJavaScript等, 具体可以参考所有支持语言.

1.X版本package:rx
2.X版本package:io.reactivex



Observable和Observer
在Rx里, 两个最主要的角色就是Observable和Observer, 跟Java的观察者模式非常类似, 虽然会有细微的区别, 但作为初学的话, 将他们当成一个东西更方便理解.
Observable代表一个事件源(也可以叫被观察者), 可以被订阅.
Observer代表一个订阅者(也可以叫观察者), 订阅Observable, 获取数据.
Observable是一个类, 有个subscribe方法, 接收一个Observer类型的参数, 用于订阅事件.
Observer是一个接口(在iOS里是protocol, 一个意思), 有个onEvent接口, 当Observable发出事件时被调用, 使用者实现此接口来处理事件.


在Rx里, 几乎所有的方法都返回一个Observable, 在RxJava和RxSwift里, Observable都是泛型. Observable发出有三种事件: 
一种是Next事件, 可能有一个或多个; 事件的数据类型就是绑定的泛型, 
一种是Error事件, 最多1个该事件, 表示发生了错误, 在RxJava里是Throwable, 在RxSwift里是ErrorType; 
还有一种是Complete事件, 最多1个该事件, 表示该Observable完成了. Error事件和Complete事件是二选一, 二者有且只有一个.
如果只关心Next事件, 有重载方法可以单独订阅Next事件.




参考
https://blog.csdn.net/u012527802/article/details/81117684
https://blog.csdn.net/xx326664162/article/details/52068014
https://blog.csdn.net/bingduanlbd/article/details/51836132
https://www.jianshu.com/p/065ba28c938a
https://www.jianshu.com/p/8651f15c7054
https://blog.csdn.net/qq_26787115/article/details/53954262
https://www.jianshu.com/p/e0891032ee4d


