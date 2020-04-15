线程堆栈
1、使用 jstack
2、使用 jcmd
3、使用 kill -3

堆：
1、java -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/var/log/abc/
2、jmap
3、使用 jcmd






获取 JVM 进程的线程堆栈 dump 和堆 dump


JVM 的线程堆栈 dump 也称 core dump，内容为文本，主要包含当时 JVM 的线程堆栈，堆 dump 也称 heap dump，内容为二进制格式，主要包含当时 JVM 堆内存中的内容。
由于各个操作系统、各个 JVM 实现不同，即使同一 JVM 实现，各个版本也有差异，本文描述的方法都基于 64 位 Linux 操作系统环境，Java 8 Oracle HotSpot JVM 实现。

堆栈和堆的内容在定位问题的时候，都是非常重要的信息。线程堆栈 dump 可以了解当时 JVM 中所有线程的运行情况，比如线程的状态和当前正在运行的代码行。堆 dump 可以了解当时堆的使用情况，各个类实例的数量及各个实例所占用的空间大小。



线程堆栈


使用 jstack
jstack 是 JDK 自带的工具，用于 dump 指定进程 ID(PID)的 JVM 的线程堆栈信息。

# 打印堆栈信息到标准输出
jstack PID

# 打印堆栈信息到标准输出，会打印关于锁的信息
jstack -l PID

强制打印堆栈信息到标准输出，如果使用 jstack PID 没有响应的情况下(此时 JVM 进程可能挂起)，加 -F 参数
jstack -F PID


使用 jcmd
jcmd 是 JDK 自带的工具，用于向 JVM 进程发送命令，根据命令的不同，可以代替或部分代替 jstack、jmap 等。可以发送命令 Thread.print 来打印出 JVM 的线程堆栈信息。

# 下面的命令同等于 jstack PID
jcmd PID Thread.print

# 同等于 jstack -l PID
jcmd PID Thread.print -l


使用 kill -3
kill 可以向特定的进程发送信号(SIGNAL)，缺省情况是发送终止(TERM) 的信号 ，即 kill PID 与 kill -15 PID 或 kill -TERM PID 是等价的。JVM 进程会监听 QUIT 信号(其值为 3)，当收到这个信号时，会打印出当时的线程堆栈和堆内存使用概要，相比 jstack，此时多了堆内存的使用概要情况。但 jstack 可以指定 -l 参数，打印锁的信息。

kill -3 PID
# 或
kill -QUIT PID



堆
-XX:+HeapDumpOnOutOfMemoryError
添加 JVM 参数 -XX:+HeapDumpOnOutOfMemoryError 后，当发生 OOM(OutOfMemory)时，自动堆 dump。缺省情况下，JVM 会创建一个名称为 java_pidPID.hprof 的堆 dump 文件在 JVM 的工作目录下。但可以使用参数 -XX:HeapDumpPath=PATH 来指定 dump 文件的保存位置。

# JVM 发生 OOM 时，会自动在 /var/log/abc 目录下产生堆 dump 文件 java_pidPID.hprof
java -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/var/log/abc/
jmap
jmap 也是 JDK 自带的工具，主要用于获取堆相关的信息。

堆 dump
# 将 JVM 的堆 dump 到指定文件，如果堆中对象较多，需要的时间会较长，子参数 format 只支持 b，即二进制格式
jmap -dump:format=b,file=FILE_WITH_PATH

# 如果 JVM 进程未响应命令，可以加上参数 -F 尝试
jmap -F -dump:format=b,file=FILE_WITH_PATH

# 可以只 dump 堆中的存活对象，加上 live 子参数，但使用 -F 时不支持 live
jmap -dump:live,format=b,file=FILE_WITH_PATH
获取堆概要信息
# -heap 参数用于查看指定 JVM 进程的堆的信息，包括堆的各个参数的值，堆中新生代、年老代的内存大小、使用率等
jmap -heap PID

# 同样，如果 JVM 进程未响应命令，可以加上参数 -F 尝试
jmap -F -heap PID
一个实例输出如下：

Attaching to process ID 68322, please wait...
Debugger attached successfully.
Server compiler detected.
JVM version is 25.112-b16

using thread-local object allocation.
Parallel GC with 4 thread(s)

Heap Configuration:
   MinHeapFreeRatio         = 0
   MaxHeapFreeRatio         = 100
   MaxHeapSize              = 268435456 (256.0MB)
   NewSize                  = 8388608 (8.0MB)
   MaxNewSize               = 89128960 (85.0MB)
   OldSize                  = 16777216 (16.0MB)
   NewRatio                 = 2
   SurvivorRatio            = 8
   MetaspaceSize            = 21807104 (20.796875MB)
   CompressedClassSpaceSize = 1073741824 (1024.0MB)
   MaxMetaspaceSize         = 17592186044415 MB
   G1HeapRegionSize         = 0 (0.0MB)

Heap Usage:
PS Young Generation
Eden Space:
   capacity = 41943040 (40.0MB)
   used     = 1701504 (1.6226806640625MB)
   free     = 40241536 (38.3773193359375MB)
   4.05670166015625% used
From Space:
   capacity = 4194304 (4.0MB)
   used     = 0 (0.0MB)
   free     = 4194304 (4.0MB)
   0.0% used
To Space:
   capacity = 5242880 (5.0MB)
   used     = 0 (0.0MB)
   free     = 5242880 (5.0MB)
   0.0% used
PS Old Generation
   capacity = 30408704 (29.0MB)
   used     = 12129856 (11.56793212890625MB)
   free     = 18278848 (17.43206787109375MB)
   39.889421134159484% used

16658 interned Strings occupying 1428472 bytes.
获取堆中的类实例统计
# 打印 JVM 堆中的类实例统计信息，以占用内存的大小排序，同样，如果 JVM 未响应命令，也可以使用 -F 参数
jmap -histo PID

# 也可以只统计堆中的存活对象，加上 live 子参数，但使用 -F 时不支持 live
jmap -histo:live PID
使用 jcmd
# 等同 jmap -dump:live,format=b,file=FILE_WITH_PATH
jcmd PID GC.heap_dump FILE_WITH_PATH

# 等同 jmap -dump:format=b,file=FILE_WITH_PATH
jcmd PID GC.heap_dump -all FILE_WITH_PATH

# 等同 jmap -histo:live PID
jcmd PID GC.class_histogram

# 等同 jmap -histo PID
jcmd PID GC.class_histogram -all




