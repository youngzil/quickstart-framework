主要是对ClassLoader相关的知识进行一个总结，讨论和解决以下问题：
1、Class和ClassLoader是什么关系，Class.forName 和ClassLoader.loadClass的联系与区别？
2、new的过程是什么，在jvm中如何执行，new和classLoader有什么关系，被new的对象的classLoader是什么，为什么new如此重要？
3、什么是类型隔离，为什么要类型隔离，如何隔离，和ClassLoader有什么关系？
4、为何要花时间实现自己的ClassLoader



为何要花时间实现自己的ClassLoader
虽然人生的乐趣很大一部分来自于将时间花在有意思但是无意义的事情上，但是这件事绝对是有意思并且有意义的，有以下几个情景是值得我们花费时间实现自己的classLoader的:
1、我们需要的类不一定存放在已经设置好的classPath下(有系统类加载器AppClassLoader加载的路径)，对于自定义路径中的class类文件的加载，我们需要自己的ClassLoader
2、有时我们不一定是从类文件中读取类，可能是从网络的输入流中读取类，这就需要做一些加密和解密操作，这就需要自己实现加载类的逻辑，当然其他的特殊处理也同样适用。
3、可以定义类的实现机制，实现类的热部署,如OSGi中的bundle模块就是通过实现自己的ClassLoader实现的。
4、需要修改类的定义【统一修改或者特性修改】：加解密、权限、模块化、日志跟踪的实现、注入




参考
https://www.jianshu.com/p/d7dc067c6e5b
https://www.jianshu.com/p/d7dc067c6e5b
https://blog.csdn.net/u011490072/article/details/81560295



---------------------------------------------------------------------------------------------------------------------  
JAVA对象的创建过程



参考
https://blog.csdn.net/weixin_30737363/article/details/99529462
https://blog.csdn.net/zz13995900221/article/details/79662139






