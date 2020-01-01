


考虑到 java 自带的 ClassLoader 无法灵活的添加某路径下的类，考虑以下三种方式实现：
1、AppClassLoader 继承自 URLClassLoader，以反射的方式将 addURL 方法设置为 public，以添加自定义路径为 classpath
2、自定义类加载器实现 URLClassLoader，将 addURL 由 protected 变更为 public，以方便添加自定义路径为 classpath
3、自定义类加载器实现 ClassLoader，将本地类文件读入byte[]，调用 defineClass 方法生成类

方案一和方案二本质上是一样的，此处只实现方案一
方案三扩展性更强，不仅仅可以实现加载类，也可实现加载资源文件。但是使用各个类时，必须手动引入，而方案一和方案二可以把路径上的所有类都自动引入


代码参考
org.quickstart.javase.jdk.classloader.ClassLoaderMainTest


参考
https://blog.csdn.net/super_wj0820/article/details/96876212



---------------------------------------------------------------------------------------------------------------------


---------------------------------------------------------------------------------------------------------------------

