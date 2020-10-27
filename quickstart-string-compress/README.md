QuickLZ

[QuickLZ的官网](http://www.quicklz.com/)

QuickLZ 是一个号称世界压缩速度最快的压缩库，并且也是个开源的压缩库，其遵守 GPL 1, 2 或 3协议。

QuickLZ是世界上最快的压缩库，每个内核达到308 Mbyte / s。如果已获得商业许可，则可以使用它，也可以根据GPL 1、2或3使用，其中必须公开发布任何东西。


LZ4
http://www.lz4.org
项目：http://code.google.com/p/lz4/
https://lz4.github.io/lz4/
https://github.com/lz4/lz4
https://github.com/lz4/lz4-java
https://commons.apache.org/proper/commons-compress/javadocs/api-release/org/apache/commons/compress/compressors/lz4/package-summary.html

LZ4 (Extremely Fast Compression algorithm)

LZ4是无损压缩算法，每个核心提供的压缩速度> 500 MB / s（> 0.15字节/周期）。 它具有极快的解码器，每个内核的速度为多个GB / s（〜1 Byte / cycle）。 可以使用一种称为LZ4_HC的高压缩导数，将可定制的CPU时间用于压缩率。 LZ4库作为使用BSD许可证的开源软件提供。


参考示例
https://www.programcreek.com/java-api-examples/index.php?api=net.jpountz.lz4.LZ4Compressor



Snappy
snappy项目：https://code.google.com/p/snappy/
https://github.com/google/snappy
https://github.com/xerial/snappy-java
Snappy
Snappy是在谷歌内部生产环境中被许多项目使用的压缩库，包括BigTable，MapReduce和RPC等。谷歌表示算法库针对性能做了调整，而不是针对压缩比或与其他类似工具的兼容性。在Intel酷睿i7处理器上，其单核处理数据流的能力达到250M/s-500M/s。Snappy同时针对64位x86处理器进行了优化，在英特尔酷睿i7处理器单一核心实现了至少250MB/s的压缩性能和500MB/ s的解压缩性能。Snappy对于纯文本的压缩率为1.5-1.7，对于HTML是2-4，当然了对于JPEG、PNG和其他已经压缩过的数据压缩率为1.0。谷歌强劲吹捧Snappy的鲁棒性，称其是“即使面对损坏或恶意输入也不会崩溃的设计”，并且在谷歌的生产环境中经过了PB级数据压缩的考验而稳定的。


LZF：
http://www.veryhuo.com/a/manual/php/function.lzf-compress.html
http://www.quicklz.com/



zlib
http://www.zlib.net/
https://github.com/madler/zlib
https://sourceforge.net/projects/zlib/

jzlib：java版本的Zlib
http://www.jcraft.com/jzlib/
https://github.com/ymnk/jzlib



FastLZ：JFastLZ，java的实现
http://fastlz.org/
http://code.google.com/p/fastlz
http://code.google.com/p/jfastlz 
FastLZ是一个高效的轻量级压缩解压库，其官方测试数据如下表：



LZO/miniLZO
官方网站：http://www.oberhumer.com/opensource/lzo/
https://github.com/Karmasphere/lzo-java
LZO是一个开源的无损压缩C语言库，其优点是压缩和解压缩比较迅速占用内存小等特点（网络传输希望的是压缩和解压缩速度比较快，压缩率不用很高），其提供了比较全的LZO库和一个精简版的miniLZO库




字符串解压缩类库(zip、GZIP、QuickLz、snappy、lzf、jzlib)介绍
1、ZIP、 GZIP  计算机文件压缩算法，JDK中java.util.zip.*中实现。主要包括ZipInputStream/ ZipOutputStream、GZipInputStream/ ZipOutputStream。

2、QuickLZ是一个号称世界压缩速度最快的压缩库，并且也是个开源的压缩库，其遵守 GPL 1, 2 或 3协议。

3、Snappy是一个 C++的用来压缩和解压缩的开发包，其目标不是最大限度压缩，而且不兼容其他压缩格式。旨在提供高速压缩速度和合理的压缩率。在64位模式的 Core i7 处理器上，可达每秒250~500兆的压缩速度。在 Google 内部被广泛的使用，从 BigTable到 MapReduce以及内部的RPC 系统。

4、LZF采用类似lz77和lzss的混合编码，针对字符串压缩算法。 

5、JZLIB是纯java的开源解压、压缩包，与JDK中ZLIB类似。




参考
https://blog.csdn.net/mcpang/article/details/41141261
http://blog.sina.com.cn/s/blog_814e83d801019itv.html
http://www.importnew.com/14410.html
https://blog.csdn.net/scorpiohjx2/article/details/18423529
https://blog.csdn.net/zhangskd/article/details/17009111


