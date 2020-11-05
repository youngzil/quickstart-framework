Python for the Java Platform

[Jython官网](http://www.jython.org/)  
[Jython Github](https://github.com/jython/jython)  


Python是用C编写的高级的、面向对象的、开放源代码的编程语言。所以又叫CPython.

Jython是一种完整的语言，而不是一个Java翻译器或仅仅是一个Python编译器，它是一个Python语言在Java中的完全实现。Jython也有很多从CPython中继承的模块库。最有趣的事情是Jython不像CPython或其他任何高级语言，它提供了对其实现语言的一切存取。所以Jython不仅给你提供了Python的库，同时也提供了所有的Java类。这使其有一个巨大的资源库。


Jython配置和使用
- 1、下载地址：http://www.jython.org/downloads.html
- 2、安装：java -jar *-install.jar
- 3、配置环境变量
设置JYTHON_HOME:
    JYTHON_HOME=/Users/yangzl/mysoft/jython2.7.0
添加PATH:
    PATH=$PATH:$JYTHON_HOME/bin

安装完成后，在控制台输入jython，可以进入jython    
   
参考文章
https://www.cnblogs.com/nuccch/p/8435693.html
https://blog.csdn.net/believe2017slwx/article/details/80205049
http://blog.csdn.net/Jerry_1126/article/details/26161183



