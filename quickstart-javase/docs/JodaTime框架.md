[使用Joda-Time优雅的处理日期时间](https://www.jianshu.com/p/efdeda608780)

由于Joda-Time很优秀，在Java 8出现前的很长时间内成为Java中日期时间处理的事实标准，用来弥补JDK的不足。  
[Joda-Time官网](https://www.joda.org/joda-time/quickstart.html)  
[Joda-Time Github](https://github.com/JodaOrg/joda-time)  


值得注意的是，Java 8中的java.time包中提供的API和Joda-Time并不完全相同。比如，在Joda-Time中常用的Interval（用来表示一对DateTime），在JSR 310中并不支持。

因此，另一个名叫Threeten的第三方库用来弥补Java 8的不足。Threeten翻译成中文就是310的意思，表示这个库与JSR 310有关。它的作者同样是Joda-Time的作者Stephen Colebourne。


Threeten主要提供两种发行包：ThreeTen-Backport和ThreeTen-Extra。前者的目的在于对Java 6和Java 7的项目提供Java 8的date-time类的支持；后者的目的在于为Java 8的date-time类提供额外的增强功能（比如：Interval等）。

[ThreeTen-Backport](https://www.threeten.org/threetenbp/)  
[ThreeTen-Extra](https://www.threeten.org/threeten-extra/)  


[ThreeTen Extra 时间日期处理利器](https://blog.csdn.net/ywb201314/article/details/110927506)  

ThreeTen 的设计里面 Instant 表示时间点，Interval 表示时间段，使用Interval即可对区间进行判断。

[]()  
[]()
[]()
[]()
[]()