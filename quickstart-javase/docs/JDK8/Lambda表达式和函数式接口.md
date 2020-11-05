- [函数式接口定义和格式](#函数式接口定义和格式)
- [常用的函数式接口](#常用的函数式接口)
- [Java8lambda表达式的优缺点总结](#Java8lambda表达式的优缺点总结)


-------------------------------------------------------------------------

## 函数式接口定义和格式

函数式接口（functional interface 也叫功能性接口，其实是同一个东西）。
简单来说，函数式接口是只包含一个方法的接口。比如Java标准库中的java.lang.Runnable和java.util.Comparator都是典型的函数式接口。
java 8提供 @FunctionalInterface作为注解,这个注解是非必须的，只要接口符合函数式接口的标准（即只包含一个方法的接口），虚拟机会自动判断，但 最好在接口上使用注解@FunctionalInterface进行声明，以免团队的其他人员错误地往接口中添加新的方法。 
Java中的lambda无法单独出现，它需要一个函数式接口来盛放，lambda表达式方法体其实就是函数接口的实现，


定义：
- 第一个前提是要求接口类型，如示例中的 Runnable，可以从当前上下文中推断出来；
- 第二个前提是要求接口中只有一个抽象方法。如果一个接口仅有一个抽象方法（除了来自 Object 的方法之外），它被称为函数式接口（functional interface）。

函数式接口的特别之处在于其实例可以通过 Lambda 表达式或方法引用来创建。
Java 8 的 java.util.function 包中添加了很多新的函数式接口。
如果一个接口被设计为函数式接口，应该添加@FunctionalInterface 注解。
编译器会确保该接口确实是函数式接口。当尝试往该接口中添加新的方法时，编译器会报错。



Lambda语法包含三个部分
- 1、一个括号内用逗号分隔的形式参数，参数是函数式接口里面方法的参数
- 2、一个箭头符号：->
- 3、方法体，可以是表达式和代码块，方法体函数式接口里面方法的实现，如果是代码块，则必须用{}来包裹起来，且需要一个return 返回值，但有个例外，若函数式接口里面方法返回值是void，则无需{}


总体看起来像这样：  
(parameters) -> expression 或者 (parameters) -> { statements; }



方法引用
其实是lambda表达式的一个简化写法，所引用的方法其实是lambda表达式的方法体实现，语法也很简单，左边是容器（可以是类名，实例名），中间是"::"，右边是相应的方法名。如下所示：
ObjectReference::methodName


一般方法的引用格式是
- 1、如果是静态方法，则是ClassName::methodName。如 Object ::equals
- 2、如果是实例方法，则是Instance::methodName。如Object obj=new Object();obj::equals;
- 3、构造函数.则是ClassName::new


lambda的作用域  
在Lambda中，变量的作用域与访问操作主要遵循以下规则：
- 1、本地变量(Local Variable)可以访问但是不可以修改
- 2、类成员变量与静态变量可以被读写，即闭包中的this实际指向的是创建该Lambda表达式的方法的this参数
- 3、函数式接口的默认方法不可以在Lambda表达式中被访问



JavaLambda表达式使用
https://github.com/CarpenterLee/JavaLambdaInternals



参考
https://my.oschina.net/benhaile/blog/175012
https://www.infoq.cn/article/Java-8-Lambdas-A-Peek-Under-the-Hood
https://juejin.im/post/5abc9ccc6fb9a028d6643eea
https://blog.csdn.net/zxhoo/article/details/38349011


---------------------------------------------------------------------------------------------------------------------
https://segmentfault.com/a/1190000016596774
https://www.cnblogs.com/chenpi/p/5890144.html
https://colobu.com/2014/10/28/secrets-of-java-8-functional-interface/
https://blog.csdn.net/jiangchao858/article/details/73730038


## 常用的函数式接口

- Function：接受一个T参数，返回一个R参数。接收一个泛型T，返回泛型R，调用这个函数后，可以改变返回的类型
- Consumer：接受一个参数，不返回参数

- Supplier：无参数，有返回值
- Predicate：用于测试是否符合条件,传入一个泛型参数T，返回boolean类型


- Optional：Optional<T> 类是一个容器类，代表一个值存在或者不存在，原来用null表示一个值不存在，现在Optional可以更好的表达这个概念。并且可以避免空指针异常。
- Stream：Stream接口，map操作，filter操作，flatMap操作

- Function: 接收参数，并返回结果，主要方法 R apply(T t)
- Consumer: 接收参数，无返回结果, 主要方法为 void accept(T t)
- Supplier: 不接收参数，但返回结构，主要方法为 T get()
- Predicate: 接收参数，返回boolean值，主要方法为 boolean test(T t)


- Function: 表示一个方法接收参数并返回结果。
- Consumer: 表示一个方法接收参数但不产生返回值。
- Supplier: 返回一个结果，并不要求每次调用都返回一个新的或者独一的结果
- Predicate: 根据接收参数进行断言，返回boolean类型


- Function<T, R> 表示接受一个参数的函数，输入类型为 T，输出类型为 R。
- BiFunction<T, U, R>，T 和 U 分别是两个参数的类型，R 是输出类型。

- Consumer<T>：接受一个输入，没有输出。抽象方法为 void accept(T t)。
- Supplier<T>：没有输入，一个输出。抽象方法为 T get()。
- Predicate<T>：接受一个输入，输出为 boolean 类型。抽象方法为 boolean test(T t)。

- UnaryOperator<T>：接受一个输入，输出的类型与输入相同，相当于 Function<T, T>。
- BinaryOperator<T>：接受两个类型相同的输入，输出的类型与输入相同，相当于 BiFunction<T,T,T>。
- BiPredicate<T, U>：接受两个输入，输出为 boolean 类型。抽象方法为 boolean test(T t, U u)。




参考
https://www.ibm.com/developerworks/cn/java/j-understanding-functional-programming-4/index.html

https://www.cnblogs.com/paulwang92115/p/12128494.html
https://blog.csdn.net/chengxuyuan_110/article/details/81112913
https://www.cnblogs.com/CarpenterLee/p/6729368.html
https://blog.csdn.net/revivedsun/article/details/80088080
https://www.baeldung.com/vavr


---------------------------------------------------------------------------------------------------------------------
## Java8lambda表达式的优缺点总结

优点：
1. 代码简洁。
2. 非常容易并行计算。
3. 可能代表未来的编程趋势。
4. 结合 hashmap 的 computeIfAbsent 方法，递归运算非常快。java有针对递归的专门优化。


缺点：
1. 若不用并行计算，很多时候计算速度没有比传统的 for 循环快。（并行计算有时需要预热才显示出效率优势，并行计算目前对 Collection 类型支持的好，对其他类型支持的一般）
2. 不容易调试。
3. 类型推断让代码可读性变差，若其他程序员没有学过 lambda 表达式，代码不容易让其他语言的程序员看懂。
4. 在 lambda 语句中强制类型转换貌似不方便，一定要搞清楚到底是 map 还是 mapToDouble 还是 mapToInt


参考  
https://blog.csdn.net/GoGleTech/article/details/79454151
https://www.jianshu.com/p/173b02d5111a


---------------------------------------------------------------------------------------------------------------------







