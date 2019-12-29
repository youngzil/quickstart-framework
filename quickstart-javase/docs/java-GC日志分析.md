```
Gc日志分析工具：GCHisto、GCViewer

(1)GCHisto
Gchisto的maven版本。
svn官方地址：https://svn.java.net/svn/gchisto~svn

http://java.net/projects/gchisto
https://github.com/jewes/gchisto
﻿https://github.com/vimerzhao/gchisto
﻿https://github.com/wengyingjian/gchisto

(2)GCLogViewer
http://code.google.com/p/gclogviewer/

(3)HPjmeter
﻿http://www.javaperformancetuning.com/tools/hpjmeter/index.shtml
获取地址 http://www.hp.com/go/java
参考文档 http://www.javaperformancetuning.com/tools/hpjtune/index.shtml

(4)GCViewer
http://www.tagtraum.com/gcviewer.html
https://github.com/chewiebug/GCViewer


文章
https://blog.csdn.net/gzh0222/article/details/8223277


﻿http://sysadminsjourney.com/2008/09/15/profile-gc-with-gchisto/
https://www.openhub.net/p/gchisto


jmap+jhat jmap dump出来用MAT分析或jhat分析
jstat –gcutil [pid] [intervel] [count]
jstack 打印线程堆栈信息
jinfo


grep Full gc.log粗略观察FullGC发生频率
jstat –gcutil [pid] [intervel] [count]
jmap -histo pid可以观测对象的个数和占用空间
jmap -heap pid可以观测jvm配置参数，堆内存各区使用情况
jprofiler,jmap dump出来用MAT分析






