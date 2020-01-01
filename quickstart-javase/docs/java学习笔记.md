1、Java泛型的类型擦除和Java语法糖（12个）
2、sleep和wait方法
3、jar包和替换jar包类
4、java中的数字魔法
5、java8lambda表达式的优缺点总结
6、java在读多写少、写多读少分别使用什么类
7、
8、
9、
10、




学习网站
https://www.toutiao.com/c/user/93762594808/#mid=1594621692172296

---------------------------------------------------------------------------------------------------------------------

Java泛型的类型擦除和Java语法糖（12个）


https://www.jianshu.com/p/36356dba3ee9
Java泛型的类型擦除

通常情况下，一个编译器处理泛型有两种方式：
1.Code specialization。在实例化一个泛型类或泛型方法时都产生一份新的目标代码（字节码or二进制代码）。例如，针对一个泛型list，可能需要 针对string，integer，float产生三份目标代码。
2.Code sharing。对每个泛型类只生成唯一的一份目标代码；该泛型类的所有实例都映射到这份目标代码上，在需要的时候执行类型检查和类型转换。
Code sharing方式为每个泛型类型创建唯一的字节码表示，并且将该泛型类型的实例都映射到这个唯一的字节码表示上。将多种泛型类形实例映射到唯一的字节码表示是通过类型擦除（type erasue）实现的。

类型擦除的主要过程如下：
 1.将所有的泛型参数用其最左边界（最顶级的父类型）类型替换。
 2.移除所有的类型参数。



https://zhuanlan.zhihu.com/p/65696212?edition=yidianzixun&utm_source=yidianzixun
https://www.cnblogs.com/dolphin0520/p/3780005.html

Java语法糖：作用在编译阶段，编译后就会编译成普通的语法，语法糖的作用就是方便程序员的使用，但最终还是要转成编译器认识的语言。
语法糖（Syntactic Sugar），也称糖衣语法，指在计算机语言中添加的某种语法，这种语法对语言的功能并没有影响，但是更方便程序员使用。简而言之，语法糖让程序更加简洁，有更高的可读性。
语法糖的存在主要是方便开发人员使用。但其实，Java虚拟机并不支持这些语法糖。这些语法糖在编译阶段就会被还原成简单的基础语法结构，这个过程就是解语法糖。
说到编译，大家肯定都知道，Java语言中，javac命令可以将后缀名为.java的源文件编译为后缀名为.class的可以运行于Java虚拟机的字节码。
如果你去看com.sun.tools.javac.main.JavaCompiler的源码，你会发现在compile()中有一个步骤就是调用desugar()，这个方法就是负责解语法糖的实现的。
Java 中最常用的语法糖主要有泛型、变长参数、条件编译、自动拆装箱、内部类等。本文主要来分析下这些语法糖背后的原理。一步一步剥去糖衣，看看其本质。


1、switch 支持 String 与枚举：进行switch的实际是哈希值，然后通过使用equals方法比较进行安全检查
2、泛型：将所有的泛型参数用其最左边界（最顶级的父类型）类型替换
3、自动装箱与拆箱：基本类型和包装类型转换，在装箱的时候自动调用的是Integer的valueOf(int)方法。而在拆箱的时候自动调用的是Integer的intValue方法。
4、方法变长参数：数组实现
5、枚举类型：当我们使用enmu来定义一个枚举类型的时候，编译器会自动帮我们创建一个final类型的类继承Enum类，所以枚举类型不能被继承。
6、内部类：生成两个完全不同的.class文件了
7、条件编译：编译器的代码优化
8、断言：用开关-enableassertions或-ea来开启
9、数值字面量：数字下划线
10、增强for循环（for-each）：实现原理其实就是使用了普通的for循环和迭代器。
11、try-with-resource：编译器都帮我们做关闭资源的操作
12、Lambda表达式：转换成调用内部api的方式


1、switch 支持 String 与枚举：switch的其实是String的hashcode，然后再进行equals方法安全校验，都匹配才算switch到
Java中的swith自身原本就支持基本类型。比如int、char等。
对于int类型，直接进行数值的比较。对于char类型则是比较其ascii码。
所以，对于编译器来说，switch中其实只能使用整型，任何类型的比较都要转换成整型。比如byte。short，char(ackii码是整型)以及int。
进行switch的实际是哈希值，然后通过使用equals方法比较进行安全检查，这个检查是必要的，因为哈希可能会发生碰撞。因此它的性能是不如使用枚举进行switch或者使用纯整数常量，但这也不是很差。

2、虚拟机中没有泛型，只有普通类和普通方法，所有泛型类的类型参数在编译时都会被擦除，泛型类并没有自己独有的Class类对象。比如并不存在List<String>.class或是List<Integer>.class，而只有List.class。

类型擦除的主要过程如下：
 1.将所有的泛型参数用其最左边界（最顶级的父类型）类型替换。
 2.移除所有的类型参数。

3、自动装箱与拆箱
从反编译得到内容可以看出，在装箱的时候自动调用的是Integer的valueOf(int)方法。而在拆箱的时候自动调用的是Integer的intValue方法。
所以，装箱过程是通过调用包装器的valueOf方法实现的，而拆箱过程是通过调用包装器的 xxxValue方法实现的。
int 的范围是数值在[-128,127]之间，所以在这个范围定义的两个i值使用==比较是true，如果超过这个范围，就自动装箱，使用Integer对象，定义的两个i值使用==比较就是false


4、方法变长参数
可变参数(variable arguments)是在Java 1.5中引入的一个特性。它允许一个方法把任意数量的值作为参数。
从反编译后代码可以看出，可变参数在被使用的时候，他首先会创建一个数组，数组的长度就是调用该方法是传递的实参的个数，然后再把参数值全部放到这个数组当中，然后再把这个数组作为参数传递到被调用的方法中。

5、当我们使用enmu来定义一个枚举类型的时候，编译器会自动帮我们创建一个final类型的类继承Enum类，所以枚举类型不能被继承。

6、内部类
内部类又称为嵌套类，可以把内部类理解为外部类的一个普通成员。
内部类之所以也是语法糖，是因为它仅仅是一个编译时的概念。
outer.java里面定义了一个内部类inner，一旦编译成功，就会生成两个完全不同的.class文件了，分别是outer.class和outer$inner.class。所以内部类的名字完全可以和它的外部类名字相同。

7、条件编译：比如两个常量的==比较，if常量，编译后会优化成计算后的结果
—般情况下，程序中的每一行代码都要参加编译。但有时候出于对程序代码优化的考虑，希望只对其中一部分内容进行编译，此时就需要在程序中加上条件，让编译器只对满足条件的代码进行编译，将不满足条件的代码舍弃，这就是条件编译。
Java语法的条件编译，是通过判断条件为常量的if语句实现的。根据if判断条件的真假，编译器直接把分支为false的代码块消除。通过该方式实现的条件编译，必须在方法体内实现，而无法在正整个Java类的结构或者类的属性上进行条件编译。

8、断言
在Java中，assert关键字是从JAVA SE 1.4 引入的，为了避免和老版本的Java代码中使用了assert关键字导致错误，Java在执行的时候默认是不启动断言检查的（这个时候，所有的断言语句都将忽略！）。
如果要开启断言检查，则需要用开关-enableassertions或-ea来开启。
其实断言的底层实现就是if语言，如果断言结果为true，则什么都不做，程序继续执行，如果断言结果为false，则程序抛出AssertError来打断程序的执行。
-enableassertions会设置$assertionsDisabled字段的值。

9、数值字面量
在java 7中，数值字面量，不管是整数还是浮点数，都允许在数字之间插入任意多个下划线。这些下划线不会对字面量的数值产生影响，目的就是方便阅读。
反编译后就是把_删除了。也就是说编译器并不认识在数字字面量中的_，需要在编译阶段把他去掉。

10、for-each
增强for循环（for-each）的实现原理其实就是使用了普通的for循环和迭代器。

11、try-with-resource
Java里，对于文件操作IO流、数据库连接等开销非常昂贵的资源，用完之后必须及时通过close方法将其关闭，否则资源会一直处于打开状态，可能会导致内存泄露等问题。
关闭资源的常用方式就是在finally块里是释放，即调用close方法。
其实背后的原理也很简单，那些我们没有做的关闭资源的操作，编译器都帮我们做了。

12、Lambda表达式
关于lambda表达式，有人可能会有质疑，因为网上有人说他并不是语法糖。其实我想纠正下这个说法。
Labmda表达式不是匿名内部类的语法糖，但是他也是一个语法糖。实现方式其实是依赖了几个JVM底层提供的lambda相关api。
在forEach方法中，其实是调用了java.lang.invoke.LambdaMetafactory#metafactory方法，该方法的第四个参数implMethod指定了方法实现。可以看到这里其实是调用了一个lambda$main$0方法进行了输出。
filter和forEach两个lambda表达式分别调用了lambda$main$1和lambda$main$0两个方法。
所以，lambda表达式的实现其实是依赖了一些底层的api，在编译阶段，编译器会把lambda表达式进行解糖，转换成调用内部api的方式。






---------------------------------------------------------------------------------------------------------------------
java中的数字魔法：
1、缓存问题：对于int的变量，在对于值范围在-128到127之间的数，==是相等的，超过这个范围的因为自动装箱，是两个不同的Integer对象，==就不相等了
2、越界问题：
  i==Math.abs(i)： Long、Short、Byte等的如负数Integer.MIN_VALUE值等于自己绝对值Math.abs(Integer.MIN_VALUE))
  i+1<i ：Integer.MAX_VALUE，因为+1越界成为负数
  i != 0 && i == -i：还是Integer.MIN_VALUE
3、浮点奥秘：
  i=i+1，i=i-1：Double.POSITIVE_INFINITY代表正无穷大，正无穷大加减一个数还是正无穷大
  i != i：Double.NaN，



一、缓存问题
JAVA编译器编译Integer a = 50的时候,被翻译成Integer a = Integer.valueOf(50);
而valueOf的源码是下面这样的
‍看到了嘛，Integer内部有一个IntegerCache缓存。对于值范围在-128到127之间的数，会进行缓存。因此a和b范围在-128到127之间，所以指向的是同一个对象，所以判断结果是true。而c和d在128之外，所以每次都是返回一个新对象，所以判断结果是false。


二、越界问题
Math.abs(Integer.MIN_VALUE))的结果是正数还是负数
绝对值运算的原理是判断这个数是否大于零，如果小于零则取负值
Integer.MIN_VALUE，它的十六进制表示是 0x80000000。其符号位为1，其余所有的位都是0。取负数(反码+1)则为 0x7fffffff+1，也就是 0x80000000。你会发现对Integer.MIN_VALUE取负值还是本身。因此，结果还是负数。
这套理论对Long、Short、Byte都成立
Math.abs(Short.MIN_VALUE)=它本身-32768
是否存在一个数i，可以使其满足i+1<i，这样看来，这个i就是Integer.MAX_VALUE，因为加完1就溢出了变为负值了。"
是否存在一个数，满足i != 0 && i == -i"其实还是Integer.MIN_VALUE，原因你刚才说过了！"


三、浮点奥秘
double i = Double.POSITIVE_INFINITY代表正无穷大，无穷大加一个常数还是无穷大，无穷大减去一个常数也是无穷大
所以 i 等于 i+1 等于 i-1

double j = Double.NaN;翻译过来就是(Not a Number)，所以他本身不等于它本身 j != j 是true


---------------------------------------------------------------------------------------------------------------------

jar包和替换jar包类

 jar uvf aiesb.jar com/yangzl/openplatform/isb/restful/security/resources/Authorization.class 


Java 生态系统提供了标准的格式来分发同一个应用中的所有 Java 类。我们可以将这些类打包为 JAR（Java Archive）、WAR（Web Archive）以及 EAR（Enterprise Archive），在这些格式中包含了前端、后端以及嵌入其中的库。

---------------------------------------------------------------------------------------------------------------------
sleep和wait方法：
1、这两个方法来自不同的类分别是Thread和Object
2、最主要是sleep方法没有释放锁，而wait方法释放了锁，使得其他线程可以使用同步控制块或者方法。
3、wait，notify和notifyAll只能在同步控制方法或者同步控制块里面使用，而sleep可以在任何地方使用(使用范围)
4、sleep必须捕获异常，而wait，notify和notifyAll不需要捕获异常

为什么wait()，notify()方法要和synchronized一起使用？
因为wait()方法是通知当前线程等待并释放对象锁，notify()方法是通知等待此对象锁的线程重新获得对象锁，然而，如果没有获得对象锁，wait方法和notify方法都是没有意义的，即必须先获得对象锁，才能对对象锁进行操作，于是，才必须把notify和wait方法写到synchronized方法或是synchronized代码块中了。


java 接口之多继承,类为什么不可以多继承
接口 是可以多继承的。接口（jdk 1.7 以下版本）里面的方法并有实现,即使接口之间具有相同的方法仍然是可以的 几个接口可以有想通的实现类和实现方法。而且接口 接口里面的成员变量都是 static   final的  有自己静态域 只能自己使用。
https://blog.csdn.net/buzaiguihun/article/details/52996818


---------------------------------------------------------------------------------------------------------------------
https://blog.csdn.net/GoGleTech/article/details/79454151
https://www.jianshu.com/p/173b02d5111a

java8lambda表达式的优缺点总结


优点：
1. 代码简洁。
2. 非常容易并行计算。
3. 可能代表未来的编程趋势。
4. 结合 hashmap 的 computeIfAbsent 方法，递归运算非常快。java有针对递归的专门优化。


缺点：
1. 若不用并行计算，很多时候计算速度没有比传统的 for 循环快。（并行计算有时需要预热才显示出效率优势，并行计算目前对 Collection 类型支持的好，对其他类型支持的一般）
2. 不容易调试。
3. 类型推断让代码可读性变差，若其他程序员没有学过 lambda 表达式，代码不容易让其他语言的程序员看懂。
4. 在 lambda 语句中强制类型转换貌似不方便，一定要搞清楚到底是 map 还是 mapToDouble 还是 mapToInt






---------------------------------------------------------------------------------------------------------------------

java7开始提供Aio的实现，BIO、NIO、AIO
https://blog.csdn.net/anxpp/article/details/51512200
https://www.cnblogs.com/diegodu/p/6823855.html
https://www.cnblogs.com/doit8791/p/4951591.html



Java transient关键字使用小记
https://www.cnblogs.com/lanxuezaipiao/p/3369962.html




ClassLoader 详解及用途
https://blog.csdn.net/u010015108/article/details/52025220

Java代码编译过程简述
https://blog.csdn.net/fuzhongmin05/article/details/54880257


AccessController.doPrivileged
http://blog.csdn.net/laiwenqiang/article/details/54321588


Spring Boot 1.5.8.RELEASE同时配置Oracle和MySQL
https://segmentfault.com/a/1190000012148813
http://blog.csdn.net/thl331860203/article/details/77849662


redis两种持久化方式的优缺点
https://www.cnblogs.com/ssssdy/p/7132856.html


kafka配置
https://hacpai.com/article/1501331595492

https://github.com/search?o=desc&q=tutorials&s=stars&type=Repositories
https://github.com/search?utf8=%E2%9C%93&q=tutorials&type=Repositories


Jupyter notebook（又称IPython notebook）是一个交互式的笔记本，支持运行超过40种编程语言。
https://jupyter.org/
https://jupyterhub.readthedocs.io/en/latest/



JVM安全退出（如何优雅的关闭java服务）：Runtime.addShutdownHook(Thread hook)注册自定义钩子
http://www.voidcn.com/article/p-wfdtbucc-bbs.html


使用ThreadGroup监控线程退出
http://yang.run/2016/03/29/Using-ThreadGroup-to-monitor-thread-exit/


---------------------------------------------------------------------------------------------------------------------

java在读多写少、写多读少分别使用什么类

java并发控制参考
docs/java并发包concurrent和并发工具类.md中的并发工具类


并发控制：
1、乐观锁
2、读写锁：分治思想



在读多写少的环境下使用
1、ConcurrentHashMap
2、copyonwrite的机制：CopyOnWriteArrayList，CopyOnWriteArraySet。
3、读写锁实现



在写多读少环境下：
1、ConcurrentHashMap的思想：切分
2、CAS变量


---------------------------------------------------------------------------------------------------------------------
