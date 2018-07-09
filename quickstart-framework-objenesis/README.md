http://objenesis.org/
https://github.com/easymock/objenesis


Objenesis是一个专用于在创建对象时绕过构造函数的库。在任何JVM上都有。
Objenesis是一个轻量级的Java库，作用是绕过构造器创建一个实例。

Java已经支持通过Class.newInstance()动态实例化Java类，但是这需要Java类有个适当的构造器。很多时候一个Java类无法通过这种途径创建，例如：
构造器需要参数
构造器有副作用
构造器会抛出异常

Objenesis可以绕过上述限制。它一般用于：
序列化、远程处理和持久化：无需调用代码即可将Java类实例化并存储特定状态。
代理、AOP库和Mock对象：可以创建特定Java类的子类而无需考虑super()构造器。
容器框架：可以用非标准方式动态实例化Java类。例如Spring引入Objenesis后，Bean不再必须提供无参构造器了。



参考
https://blog.csdn.net/top_code/article/details/52964854
https://blog.csdn.net/codershamo/article/details/52015206
https://blog.csdn.net/yao219/article/details/48735799

