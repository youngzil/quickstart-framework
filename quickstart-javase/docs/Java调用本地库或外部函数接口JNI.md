

Java语言本身具有跨平台性，如果通过Java调用DLL的技术方便易用，使用Java开发前台界面可以更快速，也能带来跨平台性。

Java调用C/C++写好的DLL库时，由于基本数据类型不同、使用字节序列可能有差异，所以在参数传递过程中容易出现问题。

使用Java调用DLL动态链接库的方案通常有四种：JNI, Jawin, Jacob，JNative. 

其中JNI(Java Native Interface)是Java语言本身提供的调用本地已编译的函数库的方法，本身具有跨平台性，可以在不同的机器上调用不同的本地库。Jawin和Jacob及JNative都是sourceforge.net的开源项目，都是基于JNI技术的依赖Windows的实现，使得在Windows平台下使用COM和DLL的更加方便。





Java 外部函数接口：JNI, JNA, JNR

## JNI(ava Native Interface)

Java 下要想实现本地方法调用，需要通过 JNI。关于 JNI 的介绍，可以参阅“Java核心技术，卷II：高级特性，第9版2013”的“第12章 本地方法”，或者读当年 Sun 公司 JNI 设计者 Sheng Liang（梁胜）写的“Java Native Interface: Programmer's Guide and Specification”。本文只给出实现 getpid 的一个简单示例。

JNI 的问题是，胶水代码（黏合 Java 和 C 库的代码）需要程序员手动书写，对不熟悉 C/C++ 的同学是很大的挑战。





## JNA（Java Native Access）

JNA（Java Native Access, wiki, github, javadoc, mvn），提供了相对 JNI 更加简洁的调用本地方法的方式。除了 Java 代码外，不再需要额外的胶水代码。

JNA（Java Native Access ）提供一组Java工具类用于在运行期动态访问系统本地库（native library：如Window的dll，Android的so）而不需要编写任何Native/JNI代码。开发人员只要在一个Java接口中描述目标native library的函数与结构，JNA将自动实现Java接口到native function的映射。

说白了JNA就是JNI的替代品

Java Native Interface  Java本地接口
Java Native Access  Java本机访问





## JNR（Java Native Runtime）

最初，JRuby 的核心开发者 Charles Nutter 在实现 Ruby 的 POSIX 集成时就使用了 JNA [ref ]。但过了一段时候后，开始开发 JNR（Java Native Runtime, github, mvn） 替代 JNA。Charles Nutter 在介绍 JNR 的 slides 中阐述了原因：

Why Not JNA?
- Preprocessor constants?
- Standard API sets out of the box
- C callbacks?
- Performance?!?

即，(1) 预处理器的常量支持（通过 jnr-constants 解决）；(2) 开箱即用的标准 API（作者实现了 jnr-posix, jnr-x86asm, jnr-enxio, jnr-unixsocket）；(3) C 回调 callback 支持；(4) 性能（提升 8-10 倍）。




## JMH性能比较代码

[JMH性能比较代码](https://github.com/yulewei/java-ffi-demo/blob/master/src/main/java/com/test/BenchmarkFFI.java)  

即：JNI > JNR > JNA (Direct Mapping) > JNA (Interface Mapping)。相对 JNI 的实现性能，其他三种方式，从大到小的性能百分比依次为：74.8% (JNR), 13.2% (JnaDirect), 10.6% (JNA)。在博主电脑上测试，JNR 相比 JNA 将近快了 6-7 倍（JNR 作者 Charles Nutter 针对 getpid 的测试结果是 JNR 比 JNA 快 8-10 倍 [twitter slides ]）。


JNA比JNI慢得多，但容易得多。如果性能不是问题，请使用JNA。





## JNI还是不能废

我们已经见识了JNA的强大。JNI和它相比是多么的简陋啊！

但是，有些需求还是必须求助于JNI。

JNA是建立在JNI技术基础之上的一个框架。

使用JNI技术，不仅可以实现Java访问C函数，也可以实现C语言调用Java代码。

而JNA只能实现Java访问C函数，作为一个Java框架，自然不能实现C语言调用Java代码。此时，你还是需要使用JNI技术。

JNI是JNA的基础。是Java和C互操作的技术基础。

JNA是SUN出品

java调用.dll获取.so一般通过JNI，但是JNI的使用比较复杂，需要用C另写一个共享库进行适配。而JNA是一个自动适配工具，通过它调用.dll只需要一个借口即可。





## 参考

[Java 外部函数接口：JNI, JNA, JNR](https://segmentfault.com/a/1190000016793722)  
[JNR-FFI](https://github.com/jnr/jnr-ffi)  
[Java Native Access (JNA)](https://github.com/java-native-access/jna)  

[Java调用本地库，如调用DLL或者SO，如：JNI, Jawin, Jacob，JNative，JNA](https://blog.csdn.net/u010015633/article/details/8785955)  
[]()  
[]()  



