http://velocity.apache.org/
https://github.com/apache/velocity-engine



Velocity教程
https://www.jianshu.com/p/5913903324ff
https://www.jianshu.com/p/378827f1dfc8
http://ifeve.com/velocity-velocity/




Velocity是一个基于Java的模板引擎，通过特定的语法，Velocity可以获取在java语言中定义的对象，从而实现界面和java代码的真正分离，这意味着可以使用velocity替代jsp的开发模式了(实际上笔者所在的公司已经这么做了)。这使得前端开发人员可以和 Java 程序开发人员同步开发一个遵循 MVC 架构的 web 站点，在实际应用中，velocity还可以应用于很多其他的场景.

1. Velocity的介绍
Velocity是一个基于Java的模板引擎，其提供了一个Context容器，在java代码里面我们可以往容器中存值，然后在vm文件中使用特定的语法获取，这是velocity基本的用法，其与jsp、freemarker并称为三大视图展现技术，相对于jsp而言，velocity对前后端的分离更加彻底：在vm文件中不允许出现java代码，而jsp文件中却可以.

作为一个模块引擎，除了作为前后端分离的MVC展现层，Velocity还有一些其他用途，比如源代码生成、自动email和转换xml等，具体的用法可以参考这篇文章.

作者：请叫我林小李
链接：https://www.jianshu.com/p/5913903324ff
来源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
