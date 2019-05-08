计算机内存模型和CPU缓存一致性协议MESI
缓存伪共享
零拷贝
在JavaSE.md

JVM内存模型：五个区域和各自保存的对象，会抛出什么异常：年轻代、年老代、持久代（方法去）、堆外内存（直接内存）

垃圾回收：垃圾收集算法、垃圾收集器，对象存活方式判断，类回收条件，堆（年轻代、年老代），java对象的引用（强引用，软引用，弱引用，虚引用）
方法区垃圾回收：废弃常量、无用的类

finalize()方法不可靠表现2方面
判断对象是否存活一般有两种方式、GC Roots包括

内存调优：减少youngGC的频率和fullGC的次数
常见参数：废弃类回收的控制参数、堆参数（初始大小，最大大小、年轻代和年老代比例）、设置GC回收器，GC打印格式和文件、HeapDump日志路径、并行收集器设置
查看垃圾回收和内存问题定位工具和步骤 
           
类加载机制和双亲委派机制，类加载过程，java创建一个对象的流程  
类加载机制（双亲委派）：安全、缓存、高效，类加载过程：加载、验证、准备、初始化、卸载，4中类加载器，重写findClass是符合双拼委派机制，重写loadclass是破坏的，
                   ClassLoader可以实现的功能：
1、自定义加载类，实现切面功能、方法调用链、代码保护加解密等，如切面log日志，切面其他功能、
2、系统开发模块化，比如阿里的jarslink、支付宝的sofaArk
3、热部署功能、热加载

java对象的引用包括：强引用，软引用，弱引用，虚引用
Java中提供这四种引用类型主要有两个目的：
第一是可以让程序员通过代码的方式决定某些对象的生命周期；
第二是有利于JVM进行垃圾回收。

JVM内存结构，和Java虚拟机的运行时区域有关。 
Java内存模型，和Java的并发编程有关。 
Java对象模型，和Java对象在虚拟机中的表现形式有关。

JAVA中的内存泄露：堆区不断增长，最后不断触发FullGC, 甚至crash
jdk的命令行工具：jps、jinfo + jstat 、jmap（-dump、jhat）、jstack
堆外内存：原因、场景、使用：存活时间长，大内存的




JVM内存结构 VS Java内存模型 VS Java对象模型
https://blog.csdn.net/hollis_chuang/article/details/80839410


计算机内存模型：https://www.cnblogs.com/dingyingsi/p/3760447.html
CPU(Processer<-->HighSpeedCache)<-->CacheCoherence<-->MainMemory


JVM内部原理
http://ifeve.com/jvm-internals/

测试JVM GC
https://blog.csdn.net/shi2huang/article/details/80067608
https://blog.csdn.net/shi2huang/article/details/80077843



http://blog.csdn.net/carolzhang8406/article/details/6705831
java提供finalize()方法，垃圾回收器准备释放内存的时候，会先调用finalize()。
          (1).对象不一定会被回收。
       (2).垃圾回收不是析构函数。
       (3).垃圾回收只与内存有关。
       (4).垃圾回收和finalize()都是靠不住的，只要JVM还没有快到耗尽内存的地步，它是不会浪费时间进行垃圾回收的。
       
       
finalize()方法是在GC进行内存回收，清除对象的时候调用，但是如果内存一直充足，就不会进行GC，也就不会调用finalize()方法，所以GC和finalize()都是不可靠的
GC的回收只与内存有关

finalize()方法不可靠表现2方面：
1、如果内存一直充足，就不会进行GC，也就不会调用finalize()方法
2、即使执行了finalize()方法，但是之后对象又被引用，就不会进行回收，后续再次回收的时候，就不会执行finalize()方法了，finalize()方法只执行一次


JVM GC垃圾回收算法：
http://blog.csdn.net/doc_sgl/article/details/46594975
判断对象是否存活一般有两种方式：
1.引用计数：每个对象有一个引用计数属性，新增一个引用时计数加1，引用释放时计数减1，计数为0时可以回收。此方法简单，无法解决对象相互循环引用的问题。
2.可达性分析（Reachability Analysis）：从GC Roots开始向下搜索，搜索所走过的路径称为引用链。当一个对象到GC Roots没有任何引用链相连时，则证明此对象是不可用的。不可达对象。

在Java语言中，GC Roots包括：
1、虚拟机栈(栈帧中的本地变量表)中引用的对象；
2、本地方法栈中JNI引用的对象。原生方法栈（Native Method Stack）中 JNI 中引用的对象。
3、方法区中类静态属性实体引用的对象。
4、方法区中常量引用的对象。

堆：年轻代、年老代
方法区：持久代
堆外内存：直接内存，不属于JVM内存的范畴
年轻代（young）：一个Eden区，两个Survivor区，young generation的gc称为minor gc
年老代（tenured）：tenured generation的gc称为major gc，就是通常说的full gc
持久代（perm）：用于存放静态文件，如Java类、方法等，主要用于存放classloader信息，比如类信息和method信息



JVM 方法区回收小结：很多人习惯称方法区为永久代（hotspot以永久代来实现方法区）
java虚拟机规范中提到：可以不要求虚拟机在方法区实现垃圾收集。而且在方法区的垃圾回收“性价比”一般比较低。在堆中，尤其是在年轻的，一次垃圾回收一般可以回收70-95%的空间
方法区也会发生垃圾回收，只是效率和性价比较低。回收主要分为两部分内容：废弃常量、无用的类。
1、废弃常量的回收
回收常量与java堆的对象回收非常相似。“没有地方引用”
这与堆中对象的回收类似。以常量池的字符串为例，如果没有任何对象引用了此字符串，那么它就有可能被系统清理出常量池。
2、废弃类的回收
	此类回收条件较为苛刻，需要满足如下的3点：
	1、该类所有实例已被回收，即Java堆中不存在该类的任何实例
	2、加载该类的ClassLoader已经被回收
	3、该类对应的java.lang.Class对象没有在任何地方被引用，无法在任何地方通过反射访问该类的方法。
	满足这三点后，仅仅是“可以”回收，并非必然进行。

废弃类回收的控制参数
JVM提供了几个参数控制类回收：
-Xnoclassgc：关闭CLASS的垃圾回收功能
-verbose:class：在控制台查看类加载情况
-XX:+TraceClassLoading：查看类加载信息(诊断内存泄露很有用)
-XX:+TraceClassUnLoading ： 查看类卸载信息（FastDebug版的虚拟机才支持）(诊断内存泄露很有用)

在大量使用反射、动态代理、CGLib等bytecode框架的场景，以及动态生成JSP和OSGi这类频繁自定义ClassLoader的场景都需要虚拟机具备类卸载的功能，以保证永久代不会溢出。




Minor collections和Major collections 
Minor collection当young space被占满时执行。它比major collections快，因为minor collection仅仅检查major collection相应的一个子集对象。minor collection比major collection发生的频率高。
Major collection当tenured space被占满时执行。他会清理tenured和young。



http://blog.csdn.net/u014086926/article/details/52106589#
java对象的引用包括
  强引用，软引用，弱引用，虚引用
１.强引用：强引用有引用变量指向时永远不会被垃圾回收，JVM宁愿抛出OutOfMemory错误也不会回收这种对象。
2.软引用（SoftReference）：如果一个对象具有软引用，内存空间足够，垃圾回收器就不会回收它；
3.弱引用（WeakReference）：弱引用也是用来描述非必需对象的，当JVM进行垃圾回收时，无论内存是否充足，都会回收被弱引用关联的对象。
4.虚引用（PhantomReference）：如果一个对象与虚引用关联，则跟没有引用与之关联一样，在任何时候都可能被垃圾回收器回收。
  
Java中提供这四种引用类型主要有两个目的：
第一是可以让程序员通过代码的方式决定某些对象的生命周期；
第二是有利于JVM进行垃圾回收。

强引用有引用变量指向时永远不会被垃圾回收，JVM宁愿抛出OutOfMemory错误也不会回收这种对象。
被软引用关联的对象只有在内存不足时才会被回收，而被弱引用关联的对象在JVM进行垃圾回收时总会被回收。
如果一个对象与虚引用关联，则跟没有引用与之关联一样，在任何时候都可能被垃圾回收器回收。

某个对象的可及性如何判断:
◆单条引用路径可及性判断:在这条路径中，最弱的一个引用决定对象的可及性。
◆多条引用路径可及性判断:几条路径中，最强的一条的引用决定对象的可及性。


https://www.cnblogs.com/dolphin0520/p/3613043.html
http://blog.csdn.net/zcw4237256/article/category/7397381
JVM内存模型
http://blog.csdn.net/zcw4237256/article/details/79042143
Java虚拟机(Java Virtual Machine=JVM)的内存空间分为五个部分，分别是： 
1. 程序计数器 ：程序计数器里面记录的是当前线程正在执行的那一条字节码指令的地址。不会内存溢出
2. Java虚拟机栈(JVM Stack)：使用 递归方法的时候容易导致栈内存溢出的现象，由于每个线程正在执行的方法可能不同，因此每个线程都会有一个自己的Java栈，互不干扰。
3. 本地方法栈 ：本地方法栈与Java栈的作用和原理非常相似。区别只不过是Java栈是为执行Java方法服务的，而本地方法栈则是为执行本地方法（Native Method）服务的。
存放的是一个个的栈帧，每个栈帧对应一个被调用的方法，在栈帧中包括局部变量表(Local Variables)、操作数栈(Operand Stack)、指向当前方法所属的类的运行时常量池（运行时常量池的概念在方法区部分会谈到）的引用(Reference to runtime constant pool)、方法返回地址(Return Address)和一些额外的附加信息。
当线程执行一个方法时，就会随之创建一个对应的栈帧，并将建立的栈帧压栈。当方法执行完毕之后，便会将栈帧出栈。因此可知，线程当前执行的方法所对应的栈帧必定位于Java栈的顶部。
4. 堆 
5. 方法区。方法区在JVM中也是一个非常重要的区域，它与堆一样，是被线程共享的区域。在方法区中，存储了每个类的信息（包括类的名称、方法信息、字段信息）、静态变量、常量以及编译器编译后的代码等。
运行时常量池（Runtime Constant Pool）是方法区的一部分。Class 文件中除了有类的版本、字段、方法、接口等描述等信息外，还有一项信息是常量池（Constant Pool Table），用于存放编译期生成的各种字面量和符号引用，这部分内容将在类加载后存放到方法区的运行时常量池中。

直接内存（Direct Memory）并不是虚拟机运行时数据区的一部分，也不是Java虚拟机规范中定义的内存区域，物理机的内存，也会发生OOM

方法区的回收：
1、废弃常量的回收
2、废弃类的回收



程序计数器、虚拟机栈和本地方法栈这三个区域属于线程私有的，只存在于线程的生命周期内，线程结束之后也会消失，因此不需要对这三个区域进行垃圾回收。垃圾回收主要是针对 Java 堆和方法区进行。


在大量使用反射、动态代理、CGLib 等 ByteCode 框架、动态生成 JSP 以及 OSGi 这类频繁自定义 ClassLoader 的场景都需要虚拟机具备类卸载功能，以保证不会出现内存溢出。



关于进程、线程和轻量级进程，线程模型的讨论 
http://blog.csdn.net/tianyue168/article/details/7403693
https://blog.csdn.net/mm_hh/article/details/72587207
http://blog.chinaunix.net/uid-22287947-id-1775625.html
进程是资源管理的最小单元；而线程是程序执行的最小单元。
一个进程的组成实体可以分为两大部分：线程集合和资源集合。
资源，包括地址空间、打开的文件、用户信息等等，由进程内的线程共享。
进程中的线程是动态的对象；代表了进程指令的执行。
线程有自己的私有数据：程序计数器，栈空间以及寄存器。

线程：其实可以先简单理解成cpu的一个执行流，指令序列。

传统进程的缺点
a. fork一个子进程的消耗是很大的，fork是一个昂贵的系统调用，即使使用现代的写时复制(copy-on-write)技术。
b. 各个进程拥有自己独立的地址空间，进程间的协作需要复杂的IPC（进程间通信）技术，如***消息队列、共享内存、信号量***等。

用户态线程和内核态线程；主要的区分就是“谁来管理”线程，用户态是用户管理，内核态是内核管理（但肯定要提供一些API，例如创建）。






1. 程序计数器 ：程序计数器里面记录的是当前线程正在执行的那一条字节码指令的地址。
在JVM规范中规定，如果线程执行的是非native方法，则程序计数器中保存的是当前需要执行的指令的地址；如果线程执行的是native方法，则程序计数器中的值是undefined。
　　由于程序计数器中存储的数据所占空间的大小不会随程序的执行而发生改变，因此，对于程序计数器是不会发生内存溢出现象(OutOfMemory)的。
2. Java虚拟机栈(JVM Stack)：
存放的是一个个的栈帧，每个栈帧对应一个被调用的方法，在栈帧中包括局部变量表(Local Variables)、操作数栈(Operand Stack)、指向当前方法所属的类的运行时常量池（运行时常量池的概念在方法区部分会谈到）的引用(Reference to runtime constant pool)、方法返回地址(Return Address)和一些额外的附加信息。
使用 递归方法的时候容易导致栈内存溢出的现象
由于每个线程正在执行的方法可能不同，因此每个线程都会有一个自己的Java栈，互不干扰。
在JVM规范中，并没有对本地方发展的具体实现方法以及数据结构作强制规定，虚拟机可以自由实现它。在HotSopt虚拟机中直接就把本地方法栈和Java栈合二为一。
3. 本地方法栈 
4. 堆 
5. 方法区。
JVM 方法区回收小结：很多人习惯称方法区为永久代（hotspot以永久代来实现方法区）
java虚拟机规范中提到：可以不要求虚拟机在方法区实现垃圾收集。而且在方法区的垃圾回收“性价比”一般比较低。在堆中，尤其是在年轻的，一次垃圾回收一般可以回收70-95%的空间
方法区也会发生垃圾回收，只是效率和性价比较低。回收主要分为两部分内容：废弃常量、无用的类。
1、废弃常量的回收
回收常量与java堆的对象回收非常相似。“没有地方引用”
这与堆中对象的回收类似。以常量池的字符串为例，如果没有任何对象引用了此字符串，那么它就有可能被系统清理出常量池。
2、废弃类的回收
	此类回收条件较为苛刻，需要满足如下的3点：
	1、该类所有实例已被回收，即Java堆中不存在该类的任何实例
	2、该类对应的java.lang.Class对象没有在任何地方被引用，无法在任何地方通过反射访问该类的方法。
	3、加载该类的ClassLoader已经被回收
	满足这三点后，仅仅是“可以”回收，并非必然进行。

废弃类回收的控制参数
JVM提供了几个参数控制类回收：
-Xnoclassgc：关闭CLASS的垃圾回收功能
-verbose:class：在控制台查看类加载情况
-XX:+TraceClassLoading：查看类加载信息(诊断内存泄露很有用)
-XX:+TraceClassUnLoading ： 查看类卸载信息（FastDebug版的虚拟机才支持）(诊断内存泄露很有用)



程序计数器是一块较小的内存空间，可以把它看作当前线程正在执行的字节码的行号指示器。也就是说，程序计数器里面记录的是当前线程正在执行的那一条字节码指令的地址。 
注：但是，如果当前线程正在执行的是一个本地方法，那么此时程序计数器为空。 
线程私有。每条线程都有一个程序计数器。
是唯一一个不会出现OutOfMemoryError的内存区域。
生命周期随着线程的创建而创建，随着线程的结束而死亡。 

Java虚拟机栈(JVM Stack)和本地方法栈 
Java虚拟机栈是描述Java方法运行过程的内存模型。 
Java虚拟机栈也是线程私有的，每个线程都有各自的Java虚拟机栈，而且随着线程的创建而创建，随着线程的死亡而死亡。
本地方法栈和Java虚拟机栈实现的功能类似，只不过本地方法区是本地方法运行的内存模型。
都会抛出StackOverFlowError和OutOfMemoryError异常。

注：StackOverFlowError和OutOfMemoryError的异同？ 
StackOverFlowError表示当前线程申请的栈超过了事先定好的栈的最大深度，但内存空间可能还有很多。 
而OutOfMemoryError是指当线程申请栈时发现栈已经满了，而且内存也全都用光了。 

堆是用来存放对象的内存空间。 几乎所有的对象都存储在堆中。 
堆的大小既可以固定也可以扩展，但主流的虚拟机堆的大小是可扩展的，因此当线程请求分配内存，但堆已满，且内存已满无法再扩展时，就抛出OutOfMemoryError。 
堆在虚拟机启动时创建，垃圾回收的主要场所。

方法区：
Java虚拟机规范中定义方法区是堆的一个逻辑部分。 方法区中存放已经被虚拟机加载的类信息、常量、静态变量、即时编译器编译后的代码等。 
方法区是堆的一个逻辑部分，因此和堆一样，都是线程共享的。整个虚拟机中只有一个方法区。

程序计数器、Java虚拟机栈、本地方法栈都是一个线程对应一个的，都是线程私有。 
整个Java虚拟机只有一个堆，所有的线程都访问同一个堆。
两个“栈”的功能类似，都是方法运行过程的内存模型。Java虚拟机栈描述的是Java方法运行过程的内存模型，而本地方法栈是描述Java本地方法运行过程的内存模型。
Java虚拟机的内存模型中一共有两个“堆”，一个是原本的堆，一个是方法区。方法区本质上是属于堆的一个逻辑部分。堆中存放对象，方法区中存放类信息、常量、静态变量、即时编译器编译的代码。
堆是Java虚拟机中最大的一块内存区域，也是垃圾收集器主要的工作区域。
程序计数器、Java虚拟机栈、本地方法栈是线程私有的，即每个线程都拥有各自的程序计数器、Java虚拟机栈、本地方法区。并且他们的生命周期和所属的线程一样。 
而堆、方法区是线程共享的，在Java虚拟机中只有一个堆、一个方法栈。并在JVM启动的时候就创建，JVM停止才销毁。
对象的内存分配，往大方向上讲就是在堆上分配，对象主要分配在新生代的Eden Space和From Space，少数情况下会直接分配在老年代。

jvm:标记复制、标记清除、标记整理算法（垃圾回收）
http://blog.csdn.net/u010841296/article/details/50945390


http://blog.csdn.net/zcw4237256/article/details/79042308
如何确定某个对象是“垃圾”？
1、引用计数法
2、在Java中采取了 可达性分析法。该方法的基本思想是通过一系列的“GC Roots”对象作为起点进行搜索，如果在“GC Roots”和一个对象之间没有可达路径，则称该对象是不可达的，不过要注意的是被判定为不可达的对象不一定就会成为可回收对象。被判定为不可达的对象要成为可回收对象必须至少经历两次标记过程，如果在这两次标记过程中仍然没有逃脱成为可回收对象的可能性，则基本上就真的成为可回收对象了。

典型的垃圾收集算法
1.Copying（复制）算法：对内存空间的使用做出了高昂的代价，因为能够使用的内存缩减到原来的一半。
　　很显然，Copying算法的效率跟存活对象的数目多少有很大的关系，如果存活对象很多，那么Copying算法的效率将会大大降低。
2.Mark-Sweep（标记-清除）算法：容易产生内存碎片，碎片太多可能会导致后续过程中需要为大对象分配空间时无法找到足够的空间而提前触发新的一次垃圾收集动作
3.Mark-Compact（标记-整理）算法：在完成标记之后，它不是直接清理可回收对象，而是将存活对象都向一端移动，然后清理掉端边界以外的内存。
4.Generational Collection（分代收集）算法：
分代收集算法是目前大部分JVM的垃圾收集器采用的算法。它的核心思想是根据对象存活的生命周期将内存划分为若干个不同的区域。一般情况下将堆区划分为老年代（Tenured Generation）和新生代（Young Generation），老年代的特点是每次垃圾收集时只有少量对象需要被回收，而新生代的特点是每次垃圾回收时都有大量的对象需要被回收，那么就可以根据不同代的特点采取最适合的收集算法。
新生代都采取Copying算法
老年代的特点是每次回收都只回收少量对象，一般使用的是Mark-Compact算法



目前常用的垃圾回收算法有很多种：
引用计数器法（Reference Counting）
标记清除法（Mark-Sweep）
复制算法（Coping）
标记压缩法（Mark-Compact）
分代算法（Generational Collecting）、分区算法（Region）
其中 cms是常用的算法。

本机内存DirectMemory，属于C Heap，可以通过参数-XX:MaxDirectMemorySize指定。属于堆外内存

说明DirectBuffer的GC规则与堆对象的回收规则是一样的，只有垃圾对象才会被回收，而判定是否为垃圾对象依然是根据引用树中的存活节点来判定。
在垃圾收集时，虽然虚拟机会对DirectMemory进行回收，但是DirectMemory却不像新生代和老年代那样，发现空间不足了就通知收集器进行垃圾回收，它只能等待老年代满了后FullGC，然后“顺便地”帮它清理掉内存中废弃的对象。否则，只能等到抛出内存溢出异常时，在catch块里调用System.gc()。

NIO的Buffer还提供了一个可以直接访问系统物理内存，而不需要进行用户态和内核态之间的拷贝，即Direct Memory的类-DirectByteBuffe，是Direct I/O模式。 
通过byteBuffer.allocateDirect(capacity)在DirectMemory上进行分配。

内存溢出（OOM）现象及举例
一.HeapSize OOM(堆空间内存溢出)
A.eg:List.add(" ")在一个死循环中不断的调用add却没有remove。
B.并发导致。
二.PermGen OOM（永久代内存溢出）
A.常量池（JDK1.6,JDK1.7以后常量池不会放在永久代中了。）
B.class加载
三.DirectBuffer OOM(直接内存内存溢出)
四.StackOverflowError(栈内存溢出错误)
1.StackOverflowError，通常都是程序的问题，JVM对栈帧的大小设置已经很大了。

2.程序运行过程中，方法分派时，会分配frame来存放本地变量，栈,pc寄存器等信息，方法再调用方法会导致Java栈空间无止境的增长（死递归），Java的解决方法是：设置一个私有栈（不在堆内存，而是在NativeMemory），这个栈的空间大小，通过-Xss来设置，数量级在256K-1MB。如果使用空间超过了-Xss限制，就会出现StackOverflowError。

3.eg:死递归
死递归和死循环的区别：死循环类似于while（true）的操作，它的线程栈空间使用不会递增。而死递归需要记录退回的路径，递归过程中调用方法，每个方法运行过程中的本地变量。也就是要记录上下文信息。这些信息会随着内容的增加，占用很大的内存空间。
死递归：
eg:1.组件的复用。
     2.子类调用父类（复用父类的方法），父类调用子类（达到多态的效果），这中间要经过许多方法，可能形成环，进而形成死递归。
     3.三方框架的问题。
五.其他内存溢出
A.unable to creat new native thread
原因：物理内存不够用或者OS限制了单个进程使用的最大内存，大量的线程分配时，就有可能导致占用的Native Memory很多。
eg:死循环中创建线程并启动，线程内部申请变量，本身不退出。而且设置Os对进程内存的限制。

B.request{} byte for {}out of swap
地址空间不够用（不一定是物理地址，还有swap,显卡，网卡）

C.IoException:too many open files
打开太多的文件，也可能是本地的socket打开太多，而没有被关闭，太多没有关闭套接字导致。




典型的垃圾收集器
1.Serial/Serial Old
　　Serial/Serial Old收集器是最基本最古老的收集器，它是一个单线程收集器，并且在它进行垃圾收集时，必须暂停所有用户线程。Serial收集器是针对新生代的收集器，采用的是Copying算法，Serial Old收集器是针对老年代的收集器，采用的是Mark-Compact算法。它的优点是实现简单高效，但是缺点是会给用户带来停顿。

2.ParNew
　　ParNew收集器是Serial收集器的多线程版本，使用多个线程进行垃圾收集。

3.Parallel Scavenge
　　Parallel Scavenge收集器是一个新生代的多线程收集器（并行收集器），它在回收期间不需要暂停其他用户线程，其采用的是Copying算法，该收集器与前两个收集器有所不同，它主要是为了达到一个可控的吞吐量。

4.Parallel Old
　　Parallel Old是Parallel Scavenge收集器的老年代版本（并行收集器），使用多线程和Mark-Compact算法。

5.CMS
　　CMS（Current Mark Sweep）收集器是一种以获取最短回收停顿时间为目标的收集器，它是一种并发收集器，采用的是Mark-Sweep算法。

6.G1
　　G1收集器是当今收集器技术发展最前沿的成果，它是一款面向服务端应用的收集器，它能充分利用多CPU、多核环境。因此它是一款并行与并发收集器，并且它能建立可预测的停顿时间模型。

对象的内存分配，往大方向上讲就是在堆上分配，对象主要分配在新生代的Eden Space和From Space，少数情况下会直接分配在老年代。
如果新生代的Eden Space和From Space的空间不足，则会发起一次GC，如果进行了GC之后，Eden Space和From Space能够容纳该对象就放在Eden Space和From Space。
在GC的过程中，会将Eden Space和From  Space中的存活对象移动到To Space，然后将Eden Space和From Space进行清理。如果在清理的过程中，To Space无法足够来存储某个对象，就会将该对象移动到老年代中。在进行了GC之后，使用的便是Eden space和To Space了，下次GC时会将存活对象复制到From Space，如此反复循环。
当对象在Survivor区躲过一次GC的话，其对象年龄便会加1，默认情况下，如果对象年龄达到15岁，就会移动到老年代中。
一般来说，大对象会被直接分配到老年代，所谓的大对象是指需要大量连续存储空间的对象，最常见的一种大对象就是大数组
当然分配的规则并不是百分之百固定的，这要取决于当前使用的是哪种垃圾收集器组合和JVM的相关参数。


在执行机制上JVM提供了串行GC（Serial GC）、并行回收GC（Parallel Scavenge）和并行GC（ParNew）


JVM、垃圾回收、内存调优、常见参数
http://blog.csdn.net/u010305706/article/details/50978370


四、JVM内存调优
对JVM内存的系统级的调优主要的目的是减少GC的频率和Full GC的次数，过多的GC和Full GC是会占用很多的系统资源（主要是CPU），影响系统的吞吐量。特别要关注Full GC，因为它会对整个堆进行整理，导致Full GC一般由于以下几种情况：

1、旧生代空间不足
    调优时尽量让对象在新生代GC时被回收、让对象在新生代多存活一段时间和不要创建过大的对象及数组避免直接在旧生代创建对象 
2、Pemanet Generation空间不足
    增大Perm Gen空间，避免太多静态对象 
    统计得到的GC后晋升到旧生代的平均大小大于旧生代剩余空间
    控制好新生代和旧生代的比例 
    
    
各部分比例不良设置会导致什么后果
1）新生代设置过小
    一是新生代GC次数非常频繁，增大系统消耗；二是导致大对象直接进入旧生代，占据了旧生代剩余空间，诱发Full GC
2）新生代设置过大
    一是新生代设置过大会导致旧生代过小（堆总量一定），从而诱发Full GC；二是新生代GC耗时大幅度增加
    一般说来新生代占整个堆1/3比较合适
3）Survivor设置过小
    导致对象从eden直接到达旧生代，降低了在新生代的存活时间
4）Survivor设置过大
    导致eden过小，增加了GC频率
    另外，通过-XX:MaxTenuringThreshold=n来控制新生代存活时间，尽量让对象在新生代被回收

JVM提供两种较为简单的GC策略的设置方式
1）吞吐量优先
    JVM以吞吐量为指标，自行选择相应的GC策略及控制新生代与旧生代的大小比例，来达到吞吐量指标。这个值可由-XX:GCTimeRatio=n来设置
2）暂停时间优先
    JVM以暂停时间为指标，自行选择相应的GC策略及控制新生代与旧生代的大小比例，尽量保证每次GC造成的应用停止时间都在指定的数值范围内完成。这个值可由-XX:MaxGCPauseRatio=n来设置


JVM常见配置

堆设置
-Xms:初始堆大小
-Xmx:最大堆大小
-XX:NewSize=n:设置年轻代大小
-XX:NewRatio=n:设置年轻代和年老代的比值。如:为3，表示年轻代与年老代比值为1：3，年轻代占整个年轻代年老代和的1/4
-XX:SurvivorRatio=n:年轻代中Eden区与两个Survivor区的比值。注意Survivor区有两个。如：3，表示Eden：Survivor=3：2，一个Survivor区占整个年轻代的1/5
-XX:MaxPermSize=n:设置持久代大小

收集器设置
-XX:+UseSerialGC:设置串行收集器
-XX:+UseParallelGC:设置并行收集器
-XX:+UseParalledlOldGC:设置并行年老代收集器
-XX:+UseConcMarkSweepGC:设置并发收集器

垃圾回收统计信息
-XX:+PrintGC
-XX:+PrintGCDetails
-XX:+PrintGCTimeStamps
-Xloggc:filename

并行收集器设置
-XX:ParallelGCThreads=n:设置并行收集器收集时使用的CPU数。并行收集线程数。
-XX:MaxGCPauseMillis=n:设置并行收集最大暂停时间
-XX:GCTimeRatio=n:设置垃圾回收时间占程序运行时间的百分比。公式为1/(1+n)

并发收集器设置
-XX:+CMSIncrementalMode:设置为增量模式。适用于单CPU情况。
-XX:ParallelGCThreads=n:设置并发收集器年轻代收集方式为并行收集时，使用的CPU数。并行收集线程数。

Java垃圾回收类型
这里有五种可以在应用里使用的垃圾回收类型。仅需要使用JVM开关就可以在我们的应用里启用垃圾回收策略。让我们一起来逐一了解：

1、Serial GC（-XX:+UseSerialGC）：Serial GC使用简单的标记、清除、压缩方法对年轻代和年老代进行垃圾回收，即Minor GC和Major GC。Serial GC在client模式（客户端模式）很有用，比如在简单的独立应用和CPU配置较低的机器。这个模式对占有内存较少的应用很管用。
2、Parallel GC（-XX:+UseParallelGC）：除了会产生N个线程来进行年轻代的垃圾收集外，Parallel GC和Serial GC几乎一样。这里的N是系统CPU的核数。我们可以使用 -XX:ParallelGCThreads=n 这个JVM选项来控制线程数量。并行垃圾收集器也叫throughput收集器。因为它使用了多CPU加快垃圾回收性能。Parallel GC在进行年老代垃圾收集时使用单线程。
3、Parallel Old GC（-XX:+UseParallelOldGC）：和Parallel GC一样。不同之处，Parallel Old GC在年轻代垃圾收集和年老代垃圾回收时都使用多线程收集。
4、并发标记清除（CMS）收集器（-XX:+UseConcMarkSweepGC)：CMS收集器也被称为短暂停顿并发收集器。它是对年老代进行垃圾收集的。CMS收集器通过多线程并发进行垃圾回收，尽量减少垃圾收集造成的停顿。CMS收集器对年轻代进行垃圾回收使用的算法和Parallel收集器一样。这个垃圾收集器适用于不能忍受长时间停顿要求快速响应的应用。可使用 -XX:ParallelCMSThreads=n JVM选项来限制CMS收集器的线程数量。
5、G1垃圾收集器（-XX:+UseG1GC) G1（Garbage First）：垃圾收集器是在Java 7后才可以使用的特性，它的长远目标时代替CMS收集器。G1收集器是一个并行的、并发的和增量式压缩短暂停顿的垃圾收集器。G1收集器和其他的收集器运行方式不一样，不区分年轻代和年老代空间。它把堆空间划分为多个大小相等的区域。当进行垃圾收集时，它会优先收集存活对象较少的区域，因此叫“Garbage First”。你可以在Oracle Garbage-FIrst收集器文档找到更多详细信息。


Java垃圾回收调优
Java垃圾回收调优应该是提升应用吞吐量的最后一个选择。在你发现应用由于长时间垃圾回收导致了应用性能下降、出现超时的时候，应该考虑Java垃圾收集调优。
如果你在日志里看到 java.lang.OutOfMemoryError: PermGen space错误，那么可以尝试使用 -XX:PermGen 和 -XX:MaxPermGen JVM选项去监控并增加Perm Gen内存空间。你也可以尝试使用-XX:+CMSClassUnloadingEnabled并查看使用CMS垃圾收集器的执行性能。
如果你看到了大量的Full GC操作，那么你应该尝试增大老年代的内存空间。
全面垃圾收集调优要花费大量的努力和时间，这里没有一尘不变的硬性调优规则。你需要去尝试不同的选项并且对这些选项进行对比，从而找出最适合自己应用的方案。



一次CMS GC问题排查过程（理解原理+读懂GC日志）
http://blog.csdn.net/endlu/article/details/51423996


CMS收集周期
CMS并非没有暂停，而是用两次短暂停来替代串行标记整理算法的长暂停，它的收集周期是这样：
初始标记(CMS-initial-mark) -> 并发标记(CMS-concurrent-mark) -> 重新标记(CMS-remark) -> 并发清除(CMS-concurrent-sweep) ->并发重设状态等待下次CMS的触发(CMS-concurrent-reset)。
其中的1，3两个步骤需要暂停所有的应用程序线程的。第一次暂停从root对象开始标记存活的对象，这个阶段称为初始标记；第二次暂停是在并发标记之后， 暂停所有应用程序线程，重新标记并发标记阶段遗漏的对象（在并发标记阶段结束后对象状态的更新导致）。第一次暂停会比较短，第二次暂停通常会比较长，并且 remark这个阶段可以并行标记。



java中内存堆，内存栈，常量池三者的关系
http://blog.csdn.net/u011001723/article/details/46005639
运行时常量池是方法区的一部分。方法区是堆得一部分
1.寄存器：最快的存储区, 由编译器根据需求进行分配,我们在程序中无法控制. 
　　2. 栈：存放基本类型的变量数据和对象的引用，但对象本身不存放在栈中，而是存放在堆（new 出来的对象）或者常量池中（字符串常量对象存放在常量池中。） 
　　3. 堆：存放所有new出来的对象。 
　　4. 静态域：存放静态成员（static定义的） 
　　5. 常量池：存放字符串常量和基本类型常量（public static final）。 
　　6. 非RAM存储：硬盘等永久存储空间 
　　这里我们主要关心栈，堆和常量池，对于栈和常量池中的对象可以共享，对于堆中的对象不可以共享。栈中的数据大小和生命周期是可以确定的，当没有引用指向数据时，这个数据就会消失。堆中的对象的由垃圾回收器负责回收，因此大小和生命周期不需要确定，具有很大的灵活性。 
　　对于字符串：其对象的引用都是存储在栈中的，如果是编译期已经创建好(直接用双引号定义的)的就存储在常量池中，如果是运行期（new出来的）才能确定的就存储在堆中。对于equals相等的字符串，在常量池中永远只有一份，在堆中有多份。

堆中一般存放的是new出来的东西或数组对象，
3是常量，存放在方法区中的运行时常量池。
new String使用new出来的，都是全新的对象，就跟基础类型的包装类一样，否则是存放在常量池中共享的

内存泄漏
http://blog.csdn.net/wtt945482445/article/details/52483944
https://www.cnblogs.com/Sharley/p/5285045.html
https://www.jianshu.com/p/90caf813682d

JAVA中的内存泄露
http://blog.csdn.net/anxpp/article/details/51325838
http://blog.csdn.net/zhousenshan/article/details/52864277
Java中的内存泄露，广义并通俗的说，就是：不再会被使用的对象的内存不能被回收，就是内存泄露。
对象都是有生命周期的，有的长，有的短，如果长生命周期的对象持有短生命周期的引用，就很可能会出现内存泄露。

JAVA内存泄露排查
https://yq.aliyun.com/articles/61148
http://www.importnew.com/17153.html
java内存泄露典型特征:
现象一: 堆/Perm 区不断增长, 没有下降趋势(回收速度赶不上增长速度), 最后不断触发FullGC, 甚至crash
现象二:每次FullGC后, 堆/Perm 区在慢慢的增长, 最后不断触发FullGC, 甚至crash

    * **用JProfiler来trace String.intern方法栈
* 方案2: dump heap, 看看哪些class有异常现象(数量), String被Perm区引用的对象信息等.但这种方式不太直观，可以从String数据看看发现可疑问题，没有方案1直观。(如下图: 如果能在日常调试推荐JProfiler**)
* 方案3: 增加-XX:+TraceClassLoading和-XX:+TraceClassUnloading, 看看哪些class加载了，哪些class卸载了. 如果一些特殊的class一直被加载而没有被卸载说明也是有问题的。(如下图) 
执行jmap -permgen(jstat -gcutil  可以查看内存增长速度和区域)命令看看Perm区中的内容, 初步确定是否存在问题 (如下图)

Java oom的排查：
http://blog.csdn.net/john8169/article/details/55802651
http://blog.csdn.net/u010256841/article/details/41121755
https://www.jianshu.com/p/2fdee831ed03

OOM的原因一般为内存泄露，创建了对象不能释放，也有可能是突然间创建了大对象，有时加载过多的class也是原因。线上遇到OOM需要做两件事情第一个是dump内存，第二个是看下GC日志。
1、top查看内存占用
2、查看GC情况，jstat -gcutil 1234 1000 10
3、dump日志，使用eclipse MAT或者visaul VM查看dump文件分析原因，jmap -dump:format=b,file=heap.hprof 1234。
eclipse MAT中点击Histogram可以看出所有heap中的对象列表，以及每类对象的数量
4、查看gc日志，堆空间日志和gc日志
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=/home/admin/logs
-Xloggc:/home/admin/logs/gc.log
-XX:+PrintGCDetails
-XX:+PrintGCDateStamps

1、java.lang.OutOfMemoryError:Java heap space 堆内存溢出
2、java.lang.OutOfMemoryError:GC overhead limit exceeded GC回收不了内存溢出
3、java.lang.OutOfMemoryError:Permgen space 持久代内存溢出
4、java.lang.OutOfMemoryError:Metaspace 元空间内存溢出
5、java.lang.OutOfMemoryError:Unable to create new native thread就意味着Java应用程序已达到其可以启动线程数量的极限了。
6、java.lang.OutOfMemoryError:Out of swap space?表示交换空间也将耗尽，并且由于缺少物理内存和交换空间，再次尝试分配内存也将失败。
7、java.lang.OutOfMemoryError:Requested array size exceeds VM limit意味着你的应用程序试图分配大于Java虚拟机可以支持的数组。



几种OOM（OutOfMemory）产生的过程：
http://blog.csdn.net/cutesource/article/details/8244250
1）Java堆：所有对象的实例分配都在Java堆上分配内存，堆大小由-Xmx和-Xms来调节
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
2）方法区：方法区是存放虚拟机加载类的相关信息，如类、静态变量和常量，大小由-XX:PermSize和-XX:MaxPermSize来调节，类太多有可能撑爆永久带
Exception in thread "main" java.lang.OutOfMemoryError: PermGen space
3）Java栈和本地方法栈
栈是存放线程调用方法时存储局部变量表，操作，方法出口等与方法执行相关的信息，栈大小由Xss来调节，方法调用层次太多会撑爆这个区域，
当然报这样的错很少见，一般只会出现无限循环的递归中，另外，线程太多也会占满栈区域
Exception in thread "main" java.lang.StackOverflowError
线程太多也会占满栈区域，报出异常：Exception in thread "main" java.lang.OutOfMemoryError:unable to create new native thread

发生oom的可能原因：
http://blog.csdn.net/ls5718/article/details/52411211
jvm的几个内存区域，除了程序计数器
1、java虚拟机栈：栈区域有两种异常类型：
如果线程请求的栈深度大于虚拟机所允许的深度，将抛StackOverflowError异常；
如果虚拟机栈可以动态扩展（大部分虚拟机都可动态扩展），当扩展时无法申请到足够的内存时会抛出OutOfMemoryError异常。
2、本地方法栈：本地方法栈则是为虚拟机用到的Native方法服务。和虚拟机栈一样可能抛出StackOverflowError和OutOfMemoryError异常。
3、堆区：JavaHeap是垃圾收集器管理的主要区域，其可细分为新生代和老年代。如果在堆中没有内存完成实例分配，并且也无法再扩展时，会抛出OutOfMemoryError异常。
4、方法区：与javaHeap一样是各个线程共享的内存区域，用于存放已被虚拟机加载的类信息、常量、静态变量、及时编译器编译后的代码等数据。当方法区无法满足内存分配的需求时，将抛出OutOfMemoryError异常。方法同时包含常听说的运行时常量池，用于存放编译期生成的各种字面量和符号引用。
5、直接内存
直接内存并不是虚拟机运行时数据区的一部分，也不是java虚拟机规范中定义的内存区域，是jvm外部的内存区域，这部分区域也可能导致OutOfMemoryError异常。
1、对象创建开销太大造成的：频繁创建小对象，或者创建一些较大的对象
2、大对象toString()产生的大String很可能是罪魁祸首：某个用于缓存的对象使用了一个List结构，但是添加元素的没有去重，导致List越来越大造成的。这个对象本身占用内存只有110M，但是toString()出来的字符串达到700M的大小，所以引起了频繁的GC

Java中如何获取到线程dump文件
死循环、死锁、阻塞、页面打开慢等问题，打线程dump是最好的解决问题的途径。所谓线程dump也就是线程堆栈，获取到线程堆栈有两步：
（1）获取到进程的pid，可以通过使用jps命令，在Linux环境下还可以使用ps -ef | grep java
（2）打印线程堆栈，可以通过使用jstack pid命令，在Linux环境下还可以使用kill -3 pid
另外提一点，Thread类提供了一个getStackTrace()方法也可以用于获取线程堆栈。这是一个实例方法，因此此方法是和具体线程实例绑定的，
每次获取获取到的是具体某个线程当前运行的堆栈，

 
 Linux环境下如何查找哪个线程使用CPU最长
这是一个比较偏实践的问题，这种问题我觉得挺有意义的。可以这么做：
（1）获取项目的pid，jps或者ps -ef | grep java，这个前面有讲过
（2）top -H -p pid，顺序不能改变
这样就可以打印出当前的项目，每条线程占用CPU时间的百分比。注意这里打出的是LWP，也就是操作系统原生线程的线程号，
使用"top -H -p pid"+"jps pid"可以很容易地找到某条占用CPU高的线程的线程堆栈，从而定位占用CPU高的原因，一般是因为不当的代码操作导致了死循环。
最后提一点，"top -H -p pid"打出来的LWP是十进制的，"jps pid"打出来的本地线程号是十六进制的，转换一下，就能定位到占用CPU高的线程的当前线程堆栈了。


Java使用JMAP dump及分析dump文件：
jmap -dump导出dump文件，使用jhat或者eclipse-mat来分析
或者是jstack连续输出线程堆栈信息，都存在同一个或多个线程，则说明系统中有锁竞争激烈，死锁，或锁饿死的想象。
	waiting的线程是否过多等

jmap -dump + jhat（或eclipse MAT）
jstack连续输出线程堆栈信息
jstat -gcutil查看年轻代的三个区的容量，GC次数、时间等

dump文件的几种方式：
1、使用jmap -dump命令
2、使用jconsole工具
3、增加jvm启动参数，-XX:+HeapDumpOnOutOfMemoryErro
4、使用hprof。启动虚拟机加入-Xrunhprof:heap=site，会生成java.hprof.txt文件。该配置会导致jvm运行非常的慢，不适合生产环境。（HPROF可以用来展示和跟踪cpu的使用情况，内存分配的统计数据等。不仅如此，它还支持对 java进程完整的内存dump，所有线程的monitor stats数据）


jdk的命令行工具；
1、jps 等同于ps，列出正在运行的虚拟机进程
2、jstat 用于监视虚拟机各种运行状态信息（例如：jstat gc 2764 250 20）
3、jinfo 实时地查看和调整jvm的各项参数
4、jmap 用于生成堆转储快照，-dump文件
5、jhat 堆转储快照的分析工具
6、jstack java堆栈信息跟踪工具（一般多jstack几次才能看出来）

jdk的可视化工具：
1、JConsole
2、VisualVM

Linux top
用top查看，发现内存占用(%MEM)挺多，其他指标均正常。
1. jps -mlvV
找出当前java进程号1234
2. jstat -gcutil 1234 1000 10或者jstat -gc 
从这一步查出，full gc次数频繁，由此可见原因是老年代空间不足
  S0     S1     E      O      M     CCS    YGC     YGCT    FGC    FGCT     GCT   
3. dump内存
接下来就是排查问题最重要的一步，dump内存最容易想到的是
jmap -dump:format=b,file=heap.hprof 1234。
注意如果用jmap来dump的话，一来非常慢，二来可能会出异常，在linux JDK1.6某个版本里使用jmap可能会让系统挂掉，可以通过-d64来解决(jmap -J-d64 -dump:format=b,file=dump.bin PID)。而jdk7的某个版本则会抛出异常，这是jdk的bug.
4. 使用eclipse MAT或者visaul VM查看dump文件分析原因
在用eclipse分析dump文件时有可能因为文件过大而报异常，这时要调大eclipse本身占用的堆内存大小，在eclipse.ini文件里面，因为eclipse本身也是一个java程序。
通过自动dump下来的内存文件很快发现有一个对象占用内存非常大，解决后系统恢复正常。
5. 查看gc日志
排查OOM通常要结合tomcat的日志、gc日志来查看。如果没有任何JVM参数设置，gc日志默认打印在stdout.log文件里，里面可能会打其他的日志，而且GC日志也不会输出时间，所以在JVM启动参数里最好加以下命令，规范下GC日志输出到/home/admin/logs/gc.log，并且打印GC时间。
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=/home/admin/logs
-Xloggc:/home/admin/logs/gc.log
-XX:+PrintGCDetails
-XX:+PrintGCDateStamps

1、观察某一时刻的JMAP histo的内容如下：可以查看哪些类型数据占用内存较大
2、Eclipse MAT(Memory Analyzer Tool)是一个强大的内存分析工具，它可以方便的分析内存泄露的问题。实际使用之后，确实也感觉到比VisualVM更加强大一些。
可以查看具体哪个对象占用内存比较大

之后我们通过分析对象，发现是因为某个用于缓存的对象使用了一个List结构，但是添加元素的没有去重，导致List越来越大造成的。这个对象本身占用内存只有110M，但是toString()出来的字符串达到700M的大小，所以引起了频繁的GC——最开始对象小的时候是New GC，后来对象大了，直接进入了年老代，就变成了Full GC。


总结
回到之前的问题，通过这次分析，我们可以这么总结：
1、将服务切换下线后，Memory Free逐渐回升，Full GC减少，Heap Dump的可达对象较少。这种情况不是内存泄露，有可能是对象创建开销太大造成的。
2、在1的前提下，New GC很频繁。这种情况可能是频繁创建小对象，或者创建一些较大的对象(尚不足以直接进入年老代)
3、在1的前提下，Full GC很频繁。这种情况是频繁创建大对象，或者创建了一些超大对象导致的。
4、第3种情况下，大对象toString()产生的大String很可能是罪魁祸首。



堆内内存与堆外内存区别：DirectBuffer OOM是堆外内存

https://blog.csdn.net/yangguosb/article/details/77870791
优点： 提升了IO效率（避免了数据从用户态向内核态的拷贝）；减少了GC次数（节约了大量的堆内内存）。 
缺点：分配和回收堆外内存比分配和回收堆内存耗时；（解决方案：通过对象池避免频繁地创建和销毁堆外内存），回收时间不确定，因为依靠jvm的GC

基于 GC 回收：堆内的DirectByteBuffer对象被GC时，会调用cleaner回收其引用的堆外内存。问题是YGC只会将将新生代里的不可达的DirectByteBuffer对象及其堆外内存回收，如果有大量的DirectByteBuffer对象移到了old区，但是又一直没有做CMS GC或者FGC，而只进行YGC，物理内存会被慢慢耗光，触发OOM； 

http://blog.csdn.net/liuleinevermore/article/details/70141410
http://blog.csdn.net/donsonzhang/article/details/46666353
http://breezylee.iteye.com/blog/2039985
https://www.cnblogs.com/hupp/p/4517369.html
http://blog.csdn.net/jxzxm1_2/article/details/2499751
名词解释：
    堆内内存：on-heap memory
    对外内存：off-heap memory
创建Buffer对象时，可以选择从JVM堆中分配内存，也可以OS本地内存中分配，由于本地缓冲区避免了缓冲区复制，在性能上相对堆缓冲区有一定优势，但同时也存在一些弊端。
两种缓冲区对应的API如下：
JVM堆缓冲区：ByteBuffer.allocate(size)
本地缓冲区：ByteBuffer.allocateDirect(size)
从堆中分配的缓冲区为普通的Java对象，生命周期与普通的Java对象一样，当不再被引用 时，Buffer对象会被回收。而直接缓冲区（DirectBuffer）为本地内存，并不在Java堆中，也不能直接被JVM垃圾回收。由于直接缓冲区在 JVM里被包装进Java对象DirectByteBuffer中，当它的包装类被垃圾回收时，会调用相应的JNI方法释放本地内存，所以本地内存的释放 也依赖于JVM中DirectByteBuffer对象的回收。

使用堆外内存
有时候对内存进行大对象的读写，会引起JVM长时间的停顿，有时候则是希望最大程度地提高JVM的效率，我们需要自己来管理内存（看起来很像是 Java像C++祖宗的妥协吧）。

Java NIO学习笔记三（堆外内存之 DirectByteBuffer 详解）
http://blog.csdn.net/u013096088/article/details/78774627

优点：
1、避免堆内内存和堆外内存之间的数据拷贝，提升数据传递效率
2、减少了JVM的GC影响

堆外内存多用于生命期中等或较长的对象，或者是大对象（避免对GC造成的影响）

堆外内存那些事

使用堆外内存的原因：
对垃圾回收停顿的改善因为full gc意味着彻底回收，彻底回收时，垃圾收集器会对所有分配的堆内内存进行完整的扫描，这意味着一个重要的事实——这样一次垃圾收集对Java应用造成的影响，跟堆的大小是成正比的。过大的堆会影响Java应用的性能。如果使用堆外内存的话，堆外内存是直接受操作系统管理( 而不是虚拟机 )。这样做的结果就是能保持一个较小的堆内内存，以减少垃圾收集对应用的影响。
在某些场景下可以提升程序I/O操纵的性能。少去了将数据从堆内内存拷贝到堆外内存的步骤。

什么情况下使用堆外内存：存活时间长，大内存的
堆外内存适用于生命周期中等或较长的对象。( 如果是生命周期较短的对象，在YGC的时候就被回收了，就不存在大内存且生命周期较长的对象在FGC对应用造成的性能影响 )。
直接的文件拷贝操作，或者I/O操作。直接使用堆外内存就能少去内存从用户内存拷贝到系统内存的操作，因为I/O操作是系统内核内存和设备间的通信，而不是通过程序直接和外设通信的。
同时，还可以使用 池+堆外内存 的组合方式，来对生命周期较短，但涉及到I/O操作的对象进行堆外内存的再使用。( Netty中就使用了该方式 )

堆外内存 VS 内存池：
内存池：主要用于两类对象：①生命周期较短，且结构简单的对象，在内存池中重复利用这些对象能增加CPU缓存的命中率，从而提高性能；②加载含有大量重复对象的大片数据，此时使用内存池能减少垃圾回收的时间。
堆外内存：它和内存池一样，也能缩短垃圾回收时间，但是它适用的对象和内存池完全相反。内存池往往适用于生命期较短的可变对象，而生命期中等或较长的对象，正是堆外内存要解决的。

外内存的特点：
对于大内存有良好的伸缩性
对垃圾回收停顿的改善可以明显感觉到
在进程间可以共享，减少虚拟机间的复制

堆外内存的一些问题：
堆外内存回收问题，以及堆外内存的泄漏问题。这个在上面的源码解析已经提到了。
堆外内存的数据结构问题：堆外内存最大的问题就是你的数据结构变得不那么直观，如果数据结构比较复杂，就要对它进行串行化（serialization），而串行化本身也会影响性能。另一个问题是由于你可以使用更大的内存，你可能开始担心虚拟内存（即硬盘）的速度对你的影响了。

通过unsafe.allocateMemory分配堆外内存，并返回堆外内存的基地址
// 构建Cleaner对象用于跟踪DirectByteBuffer对象的垃圾回收，以实现当DirectByteBuffer被垃圾回收时，堆外内存也会被释放
cleaner = Cleaner.create(this, new Deallocator(base, size, cap));

如果系统中内存( 即，堆外内存 )不够的话：
jlra.tryHandlePendingReference()会触发一次非堵塞的Reference#tryHandlePending(false)。该方法会将已经被JVM垃圾回收的DirectBuffer对象的堆外内存释放。 
如果在进行一次堆外内存资源回收后，还不够进行本次堆外内存分配的话，则System.gc()会触发一个full gc，当然前提是你没有显示的设置-XX:+DisableExplicitGC来禁用显式GC。并且你需要知道，调用System.gc()并不能够保证full gc马上就能被执行。 
所以在后面打代码中，会进行最多9次尝试，看是否有足够的可用堆外内存来分配堆外内存。并且每次尝试之前，都对延迟等待时间，已给JVM足够的时间去完成full gc操作。如果9次尝试后依旧没有足够的可用堆外内存来分配本次堆外内存，则抛出OutOfMemoryError(“Direct buffer memory”)异常。

比如我们要完成一个从文件中读数据到堆内内存的操作，即FileChannelImpl.read(HeapByteBuffer)。这里实际上File I/O会将数据读到堆外内存中，然后堆外内存再讲数据拷贝到堆内内存，这样我们就读到了文件中的内存。
写操作则反之，我们会将堆内内存的数据线写到对堆外内存中，然后操作系统会将堆外内存的数据写入到文件中。 

Q：为什么需要用户进程(位于用户态中)要通过系统调用(Java中即使JNI)来调用内核态中的资源，或者说调用操作系统的服务了？ 
A：intel cpu提供Ring0-Ring3四种级别的运行模式，Ring0级别最高，Ring3最低。Linux使用了Ring3级别运行用户态，Ring0作为内核态。Ring3状态不能访问Ring0的地址空间，包括代码和数据。因此用户态是没有权限去操作内核态的资源的，它只能通过系统调用外完成用户态到内核态的切换，然后在完成相关操作后再有内核态切换回用户态。
在linux中内核态的权限是最高的

DirectByteBuffer该类本身还是位于Java内存模型的堆中。堆内内存是JVM可以直接管控、操纵。而DirectByteBuffer中的unsafe.allocateMemory(size)是个一个native方法，这个方法分配的是堆外内存，通过C的malloc来进行分配的。分配的内存是系统本地的内存，并不在Java的内存中，也不属于JVM管控范围，所以在DirectByteBuffer一定会存在某种方式来操纵堆外内存。 
在DirectByteBuffer的父类Buffer中有个address属性：address表示分配的堆外内存的地址。
address只会被直接缓存给使用到。之所以将address属性升级放在Buffer中，是为了在JNI调用GetDirectBufferAddress时提升它调用的速率。 

Q：那为什么操作系统不直接访问Java堆内的内存区域了？ 
A：这是因为JNI方法访问的内存区域是一个已经确定了的内存区域地质，那么该内存地址指向的是Java堆内内存的话，那么如果在操作系统正在访问这个内存地址的时候，Java在这个时候进行了GC操作，而GC操作会涉及到数据的移动操作[GC经常会进行先标志在压缩的操作。即，将可回收的空间做标志，然后清空标志位置的内存，然后会进行一个压缩，压缩就会涉及到对象的移动，移动的目的是为了腾出一块更加完整、连续的内存空间，以容纳更大的新对象]，数据的移动会使JNI调用的数据错乱。所以JNI调用的内存是不能进行GC操作的。

直接使用堆外内存，如DirectByteBuffer：这种方式是直接在堆外分配一个内存(即，native memory)来存储数据，程序通过JNI直接将数据读/写到堆外内存中。因为数据直接写入到了堆外内存中，所以这种方式就不会再在JVM管控的堆内再分配内存来存储数据了，也就不存在堆内内存和堆外内存数据拷贝的操作了。这样在进行I/O操作时，只需要将这个堆外内存地址传给JNI的I/O的函数就好了。

---------------------------------------------------------------------------------------------------------------------

参考
https://www.cnblogs.com/xiaoxian1369/p/5498817.html



类加载机制和双亲委派机制，类加载过程，java创建一个对象的流程  

类加载器和双亲委派机制，何时破坏双亲委派机制
类的加载过程：加载、验证、准备、初始化、卸载等，使用双亲委派主要是因为安全，缓存、高效
JVM的ClassLoader分类：Bootstrap ClassLoader（Java的核心类库）、Extension ClassLoader（扩展核心类以外的，(%JAVA_HOME%/jre/lib/ext)下的jar包）、APP ClassLoader（加载CLASSPATH环境变量所指定的jar包与类路径）
各种类加载器间关系：以组合关系复用父类加载器的父子关系，注意，这里的父子关系并不是以继承关系实现的。
类加载器的双亲委派加载机制（重点）：当一个类收到了类加载请求，他首先不会尝试自己去加载这个类，而是把这个请求委派给父类去完成，每一个层次类加载器都是如此，因此所有的加载请求都应该传送到启动类加载其中，只有当父类加载器反馈自己无法完成这个请求的时候（在它的加载路径下没有找到所需加载的Class），子类加载器才会尝试自己去加载。
双亲委派机制的破坏：spi和osgi，自定义ClassLoader，重写了loadClass方法（默认的loadClass方法是实现了双亲委派机制的逻辑）
几种扩展应用用户定制自己的ClassLoader可以实现以下的一些应用  
安全性。类进入JVM之前先经过ClassLoader，所以可以在这边检查是否有正确的数字签名等  
解码器。java字节码很容易被反编译，通过定制ClassLoader使得字节码先加密防止别人下载后反编译，这里的ClassLoader相当于一个动态的解码器  
归档。可能为了节省网络资源，对自己的代码做一些特殊的归档，然后用定制的ClassLoader来解档  
自展开程序。把java应用程序编译成单个可执行类文件，这个文件包含压缩的和加密的类文件数据，同时有一个固定的ClassLoader，当程序运行时它在内存中完全自行解开，无需先安装  
动态生成。可以生成应用其他还未生成类的类，实时创建整个类并可在任何时刻引入JVM  

ClassLoader可以实现的功能：
1、自定义加载类，实现切面功能、方法调用链、代码保护加解密等，如切面log日志，切面其他功能
2、系统开发模块化，比如阿里的jarslink、支付宝的sofaArk
3、热部署功能



ClassLoader类加载过程：

类从被加载到JVM中开始，到卸载为止，整个生命周期包括：加载、验证、准备、解析、初始化、使用和卸载七个阶段。
其中类加载过程包括加载、验证、准备、解析和初始化五个阶段。

类加载器的任务就是根据一个类的全限定名来读取此类的二进制字节流到JVM中，然后转换为一个与目标类对应的java.lang.Class对象实例。
BootstrapClassLoader、ExtClassLoader和AppClassLoader
defineClass方法将字节码的byte数组转换为一个类的class对象实例，如果希望在类被记载到JVM时就被链接，那么可以调用resolveClass方法。


自定义类加载器需要继承抽象类ClassLoader，实现findClass方法，该方法会在loadClass调用的时候被调用，findClass默认会抛出异常。
findClass方法表示根据类名查找类对象
loadClass方法表示根据类名进行双亲委托模型进行类加载并返回类对象
defineClass方法表示跟根据类的字节码转换为类对象

当一个类加载器接收到一个类加载的任务时，不会立即展开加载，而是将加载任务委托给它的父类加载器去执行，每一层的类都采用相同的方式，直至委托给最顶层的启动类加载器为止。如果父类加载器无法加载委托给它的类，便将类的加载任务退回给下一级类加载器去执行加载。

双亲委托模型的工作过程是：如果一个类加载器收到了类加载的请求，它首先不会自己去尝试加载这个类，而是把这个请求委托给父类加载器去完成，每一个层次的类加载器都是如此，因此所有的加载请求最终都应该传送到顶层的启动类加载器中，只有当父类加载器反馈自己无法完成这个加载请求（它的搜索范围中没有找到所需要加载的类）时，子加载器才会尝试自己去加载。
使用双亲委托机制的好处是：能够有效确保一个类的全局唯一性，当程序中出现多个限定名相同的类时，类加载器在执行加载时，始终只会加载其中的某一个类。

使用双亲委托模型来组织类加载器之间的关系，有一个显而易见的好处就是Java类随着它的类加载器一起具备了一种带有优先级的层次关系。例如类java.lang.Object，它存放在rt.jar之中，无论哪一个类加载器要加载这个类，最终都是委托给处于模型最顶端的启动类加载器进行加载，因此Object类在程序的各种加载器环境中都是同一个类。相反，如果没有使用双亲委托模型，由各个类加载器自行去加载的话，如果用户自己编写了一个称为java.lang.Object的类，并放在程序的ClassPath中，那系统中将会出现多个不同的Object类，Java类型体系中最基础的行为也就无法保证，应用程序也将会变得一片混乱。如果自己去编写一个与rt.jar类库中已有类重名的Java类，将会发现可以正常编译，但永远无法被加载运行。


双亲委托模型对于保证Java程序的稳定运作很重要，但它的实现却非常简单，实现双亲委托的代码都集中在java.lang.ClassLoader的loadClass()方法中，逻辑清晰易懂：先检查是否已经被加载过，若没有加载则调用父类加载器的loadClass()方法，若父加载器为空则默认使用启动类加载器作为父加载器。如果父类加载器加载失败，抛出ClassNotFoundException异常后，再调用自己的findClass方法进行加载。




参考
https://www.cnblogs.com/CZDblog/p/5589379.html
https://blog.csdn.net/justloveyou_/article/details/72466416


java创建一个对象的流程  

检测类是否被加载没有加载的先加载→为新生对象分配内存→将分配到的内存空间都初始化为零值→对对象进行必要的设置→执行<init>方法把对象进行初始化

初始化对象过程：
总的来说，类实例化的一般过程是：父类的类构造器<clinit>() -> 子类的类构造器<clinit>() -> 父类的成员变量和实例代码块 -> 父类的构造函数 -> 子类的成员变量和实例代码块 -> 子类的构造函数。




---------------------------------------------------------------------------------------------------------------------


---------------------------------------------------------------------------------------------------------------------


---------------------------------------------------------------------------------------------------------------------


---------------------------------------------------------------------------------------------------------------------









