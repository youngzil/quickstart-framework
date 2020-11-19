ASM

[ASM官网](https://asm.ow2.io/)  
[ASM gitlab](https://gitlab.ow2.org/asm/asm)  


ASM is an all purpose Java bytecode manipulation and analysis framework. It can be used to modify existing classes or to dynamically generate classes, directly in binary form. ASM provides some common bytecode transformations and analysis algorithms from which custom complex transformations and code analysis tools can be built. ASM offers similar functionality as other Java bytecode frameworks, but is focused on performance. Because it was designed and implemented to be as small and as fast as possible, it is well suited for use in dynamic systems (but can of course be used in a static way too, e.g. in compilers).

ASM是一个通用的Java字节码操作和分析框架。 它可以直接以二进制形式用于修改现有类或动态生成类。 ASM提供了一些常见的字节码转换和分析算法，可以从中构建定制的复杂转换和代码分析工具。 ASM提供与其他Java字节码框架类似的功能，但侧重于性能。 因为它的设计和实现是尽可能的小和尽可能快，所以它非常适合在动态系统中使用（但当然也可以以静态方式使用，例如在编译器中使用）。


什么是ASM
  首先看下官方中的说明 ASM a very small and fast Java bytecode manipulation framework。
  ASM是一个JAVA字节码分析、创建和修改的开源应用框架。它可以动态生成二进制格式的stub类或其他代理类，或者在类被JAVA虚拟机装入内存之前，动态修改类。在ASM中提供了诸多的API用于对类的内容进行字节码操作的方法。与传统的BCEL和SERL不同，在ASM中提供了更为优雅和灵活的操作字节码的方式。ASM相当小巧，并且它有更高的执行效率，是BCEL的7倍，SERP的11倍以上(摘自网络，具体没有测试)。目前ASM已被广泛的开源应用架构所使用，例如：Spring、Hibernate等。
  
  ASM能做什么:诸如代码生成，代码混淆，代码转换等等以字节码为操作目标的工作
  我们都知道，一般情况下，Class文件是通过javac编译器产生的，然后通过类加载器加载到虚拟机内，再通过执行引擎去执行。现在我们可以通过ASM的API直接生成符合Java虚拟机规范的Class字节流，这样，ASM做的事情一定程度上正是javac解释器做的工作。
 可以说ASM分析一个类、从字节码角度创建一个类、修改一个已经被编译过的类文件。
 那么，我们就可以通过ASM来实现诸如代码生成，代码混淆，代码转换等等以字节码为操作目标的工作
 
 
 什么是asm呢？asm是assembly的缩写，是汇编的称号，对于java而言，asm就是字节码级别的编程。  
而这里说到的asm是指objectweb asm,一种.class的代码生成器的开源项目.  
ASM是一套java字节码生成架构，它可以动态生成二进制格式的stub类或其它代理类，  
或者在类被java虚拟机装入内存之前，动态修改类。  
现在挺多流行的框架都使用到了asm.所以从aop追溯来到了这。  
  
  
参考
https://www.cnblogs.com/onlysun/p/4533798.html
http://perfect5085.iteye.com/blog/1612931
https://yq.aliyun.com/articles/4798
https://blog.csdn.net/teamlet/article/details/41926125




