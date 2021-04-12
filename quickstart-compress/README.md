compression library





---------------------------------------------------------------------------------------------------------------------


Zstandard - Fast real-time compression algorithm





[zstd文档](https://facebook.github.io/zstd/)  
[zstd Github](https://github.com/facebook/zstd)  
[zstd各个语言实现](https://facebook.github.io/zstd/#other-languages)  
[zstd Java](https://github.com/luben/zstd-jni)  
[A port of Snappy, LZO, LZ4, and Zstandard to Java](https://github.com/airlift/aircompressor)



---------------------------------------------------------------------------------------------------------------------
Snappy

[Snappy官网](http://google.github.io/snappy/)  
[Snappy Github](https://github.com/google/snappy)

Snappy, a fast compressor/decompressor.

快速压缩器/解压缩器

Snappy是一个压缩/解压缩库。它不旨在最大程度地压缩，也不旨在与任何其他压缩库兼容。相反，它的目标是非常高的速度和合理的压缩。例如，与zlib最快的模式相比，Snappy对于大多数输入而言要快一个数量级，但是生成的压缩文件要大20％至100％。（有关更多信息，请参见下面的“性能”。）

Snappy具有以下属性：
- 快速：压缩速度为250 MB /秒及更高，无汇编代码。请参阅下面的“性能”。
- 稳定：在过去的几年中，Snappy已在Google的生产环境中压缩和解压缩了PB级的数据。Snappy位流格式是稳定的，不会在版本之间更改。
- 稳健：Snappy解压缩器的设计不会在遭到损坏或恶意输入时崩溃。
- 免费和开源软件：Snappy已获得BSD类型许可证的许可。有关更多信息，请参见随附的COPYING文件。

Snappy在某些Google演示文稿等中以前被称为“ Zippy”。



snappy项目：https://code.google.com/p/snappy/
https://github.com/google/snappy
https://github.com/xerial/snappy-java

Snappy是在谷歌内部生产环境中被许多项目使用的压缩库，包括BigTable，MapReduce和RPC等。谷歌表示算法库针对性能做了调整，而不是针对压缩比或与其他类似工具的兼容性。在Intel酷睿i7处理器上，其单核处理数据流的能力达到250M/s-500M/s。Snappy同时针对64位x86处理器进行了优化，在英特尔酷睿i7处理器单一核心实现了至少250MB/s的压缩性能和500MB/ s的解压缩性能。Snappy对于纯文本的压缩率为1.5-1.7，对于HTML是2-4，当然了对于JPEG、PNG和其他已经压缩过的数据压缩率为1.0。谷歌强劲吹捧Snappy的鲁棒性，称其是“即使面对损坏或恶意输入也不会崩溃的设计”，并且在谷歌的生产环境中经过了PB级数据压缩的考验而稳定的。




Snappy compressor/decompressor for Java

[snappy-java官网](https://xerial.org/snappy-java/)  
[snappy-java](https://github.com/xerial/snappy-java)  

[A port of Snappy, LZO, LZ4, and Zstandard to Java](https://github.com/airlift/aircompressor)


[Snappy Java API简介](https://blog.csdn.net/cjf_wei/article/details/80635983)  
[snappy-java两种压缩方式的区别](https://my.oschina.net/u/4290244/blog/3348726)



---------------------------------------------------------------------------------------------------------------------

bzip2 is a freely available, patent free (see below), high-quality data compressor. It typically compresses files to within 10% to 15% of the best available techniques (the PPM family of statistical compressors), whilst being around twice as fast at compression and six times faster at decompression.

bzip2是可免费获得的，无专利的（见下文）高质量的数据压缩器。 它通常将文件压缩到最佳技术（PPM统计压缩器系列）的10％到15％之内，而压缩速度约为解压缩速度的两倍，解压缩速度约为压缩速度的六倍。

Apache foundation provides a compress library which contains bzip2 library, here is the example.

commons-compress

[Apache Commons Compress官网](https://commons.apache.org/proper/commons-compress/)  
[Apache Commons Compress Github](https://github.com/apache/commons-compress)


Apache Commons Compress顾名思义，就是负责处理压缩的底层库，它能处理的压缩格式非常多，包括ar, cpio, Unix dump, tar, zip, gzip, XZ, Pack200, bzip2, 7z, arj, lzma, snappy, DEFLATE, lz4, Brotli, Zstandard, DEFLATE64和Z格式。

这里做一个简单说明，我们常说的压缩，实际上是归档（archiver）+压缩（compressor），这里列出的很多格式，我们可能有些并不熟悉，因为它们是一些压缩算法自己的格式，我们比较熟悉的格式应该就是tar, zip, gzip(gz), 7z。

可以看到，Commons Compress可以处理的格式非常多，涵盖了大多数我们可能用到的格式，这也是推荐Commons Compress的一个重要原因：用它基本就可以满足你们项目中所有压缩格式的需要了。


指定压缩文件格式并解压  
Commons Compress支持很多压缩格式，在指定压缩格式时，可以直接使用ArchiveStreamFactory中包含的格式：
- ArchiveStreamFactory.AR: ar格式
- ArchiveStreamFactory.ARJ: arj格式
- ArchiveStreamFactory.CPIO: cpio格式
- ArchiveStreamFactory.DUMP: dump格式
- ArchiveStreamFactory.JAR: jar格式
- ArchiveStreamFactory.TAR: tar格式
- ArchiveStreamFactory.ZIP: zip格式
- ArchiveStreamFactory.SEVEN_Z: 7z格式
- ArchiveStreamFactory.AR: ar格式




[最好的JAVA生态压缩库-Apache Commons Compress介绍](https://zhuanlan.zhihu.com/p/139700568)


---------------------------------------------------------------------------------------------------------------------

[A port of Snappy, LZO, LZ4, and Zstandard to Java](https://github.com/airlift/aircompressor)  




Brotli compression format

[brotli官网](https://www.brotli.org/)  
[brotli Github](https://github.com/google/brotli)  




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

[A port of Snappy, LZO, LZ4, and Zstandard to Java](https://github.com/airlift/aircompressor)





LZF：
http://www.veryhuo.com/a/manual/php/function.lzf-compress.html
http://www.quicklz.com/



一个LZF实现库

High-performance, streaming/chunking Java LZF codec, compatible with standard C LZF package

[LZF Compressor](https://github.com/ning/compress)




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


