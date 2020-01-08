Logback配置日志颜色（彩色）.md


日志彩色渲染
logback 实现 （ASNI）彩色日志（还原Spring boot 彩色日志）


ANSI是一种字符代码，为使计算机支持更多语言，通常使用 0x00~0x7f 范围的1 个字节来表示 1 个英文字符。超出此范围的使用0x80~0xFFFF来编码，即扩展的ASCII编码。

Logback是由log4j创始人设计的又一个开源日志组件。
logback当前分成三个模块：logback-core,logback- classic和logback-access。
logback-core是其它两个模块的基础模块。
logback-classic是log4j的一个 改良版本。此外logback-classic完整实现SLF4J API使你可以很方便地更换成其它日志系统如log4j或JDK14 Logging。
logback-access访问模块与Servlet容器集成提供通过Http来访问日志的功能。



<!-- magenta:洋红 -->
<!-- boldMagenta:粗红-->
<!-- cyan:青色 -->
<!-- white:白色 -->
<!-- magenta:洋红 -->
 

https://blog.csdn.net/u012693119/article/details/79716306
https://blog.csdn.net/qq_34313827/article/details/80394325
https://blog.csdn.net/tt____tt/article/details/82154813
https://blog.csdn.net/shichen2010/article/details/83031418
https://blog.csdn.net/qq_40147863/article/details/88880053
https://blog.csdn.net/ITzhangdaopin/article/details/84635815
https://icode.blog.csdn.net/article/details/88874162


