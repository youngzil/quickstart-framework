- [Java GC日志设置和分析](#Java-GC日志设置和分析)
- [GC日志分析工具](#GC日志分析工具)




## Java GC日志设置和分析


查看 [gc.log](gc.log)


参考  
[JVM GC 日志详解](https://juejin.im/post/6844903791909666823)  
[通过案例瞬间理解JVM中PSYoungGen、ParOldGen、MetaSpace](https://blog.csdn.net/someby/article/details/83713476)  
[Java GC 分析](http://www.ityouknow.com/jvm/2017/09/18/GC-Analysis.html)  





## GC日志分析工具

GC日志分析工具：GCHisto、GCViewer

(1)GCHisto
Gchisto的maven版本。
svn官方地址：https://svn.java.net/svn/gchisto~svn

http://java.net/projects/gchisto
https://github.com/jewes/gchisto
https://github.com/vimerzhao/gchisto
https://github.com/wengyingjian/gchisto

(2)GCLogViewer
http://code.google.com/p/gclogviewer/

(3)HPjmeter
http://www.javaperformancetuning.com/tools/hpjmeter/index.shtml
获取地址 http://www.hp.com/go/java
参考文档 http://www.javaperformancetuning.com/tools/hpjtune/index.shtml

(4)GCViewer
http://www.tagtraum.com/gcviewer.html
https://github.com/chewiebug/GCViewer



[Profile Your Java GC Performance With GChisto](http://sysadminsjourney.com/2008/09/15/profile-gc-with-gchisto/)  
[gchisto下载](https://www.openhub.net/p/gchisto)




jmap+jhat jmap dump出来用MAT分析或jhat分析
jstat –gcutil [pid] [intervel] [count]
jstack 打印线程堆栈信息
jinfo


grep Full gc.log粗略观察FullGC发生频率
jstat –gcutil [pid] [intervel] [count]
jmap -histo pid可以观测对象的个数和占用空间
jmap -heap pid可以观测jvm配置参数，堆内存各区使用情况
jprofiler,jmap dump出来用MAT分析






使用jstack和TDA进行java线程dump分析

TDA - Thread Dump Analyzer

[https://github.com/irockel/tda](https://github.com/irockel/tda)  
[https://github.com/mkbrv/tda](https://github.com/mkbrv/tda)  
[使用jstack和TDA进行java线程dump分析](https://blog.csdn.net/everlasting_188/article/details/51943095)  



参考  
[gc日志分析工具](https://cloud.tencent.com/developer/article/1200360)  
[可视化GC日志分析工具](https://juejin.im/post/6844903873279344653)  



