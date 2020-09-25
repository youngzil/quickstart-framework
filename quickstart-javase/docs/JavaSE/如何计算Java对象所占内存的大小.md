如何计算Java对象所占内存的大小


Java对象内存布局
Java对象的内存布局包括：对象头(Header)，实例数据(Instance Data)和补齐填充(Padding)。
即，Java对象从整体上可以分为三个部分，对象头、实例数据和对齐填充


对象头：Instance Header，Java对象最复杂的一部分，采用C++定义了头的协议格式，存储了Java对象hash、GC年龄、锁标记、class指针、数组长度等信息，稍后做出详细解说。

实例数据：Instance Data，这部分数据才是真正具有业务意义的数据，实际上就是当前对象中的实例字段。在VM中，对象的字段是由基本数据类型和引用类型组成的。其所占用空间的大小如下所示：

对齐填充：Padding，VM要求对象大小须是8的整体数，该部分是为了让整体对象在内存中的地址空间大小达到8的整数倍而额外占用的字节数。


对象头：
关于对象头的详细介绍可以参看我的博文：http://blog.csdn.net/codersha...，这里只关注其内存占用大小。在64位机器上，默认不开启指针压缩（-XX:-UseCompressedOops）的情况下，对象头占用12bytes，开启指针压缩（-XX:+UseCompressedOops）则占用16bytes。
对象引用（reference）类型在64位机器上，关闭指针压缩时占用8bytes， 开启时占用4bytes。
JVM 对象头一般占用两个机器码，在 32-bit JVM 上占用 64bit， 在 64-bit JVM 上占用 128bit 即 8+8=16 bytes（开启指针压缩后占用 4+8=12 bytes）
64位机器上，数组对象的对象头占用 24 bytes，启用压缩之后占用 16 bytes。之所以比普通对象占用内存多是因为需要额外的空间存储数组的长度。
更具体的对象头介绍请参考：http://blog.csdn.net/wenniuwuren/article/details/50939410


对齐填充：
HotSpot 的对齐方式为 8 字节对齐，不足的需要 Padding 填充对齐， 公式：（对象头 + 实例数据 + padding）% 8 == 0 （0<= padding <8）
Java对象占用空间是8字节对齐的，即所有Java对象占用bytes数必须是8的倍数。例如，一个包含两个属性的对象：int和byte，并不是占用17bytes(12+4+1)，而是占用24bytes（对17bytes进行8字节对齐）
比如TestObject对象有四个属性，分别为int, double, Byte, char[]类型。在打开指针压缩(-XX:+UseCompressedOops)的情况下，在64位机器上，TestObject占用的内存大小应为：
12(Header) + 4(int) + 8(double) + 4(reference) = 28 (bytes)，加上8字节对齐，最终的大小应为32 bytes。



参考
https://www.jianshu.com/p/9d729c9c94c4
https://segmentfault.com/a/1190000006933272
https://blog.csdn.net/wenniuwuren/article/details/50958892
https://www.cnblogs.com/Kidezyq/p/8030098.html
