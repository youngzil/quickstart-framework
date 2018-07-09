http://json-lib.sourceforge.net/index.html

http://json-lib.sourceforge.net/

包路径：net.sf.json.


参考文章
http://blog.csdn.net/tanga842428/article/details/54864485


java对象<<>>json格式的字符串
java对象<<>>xml格式的文档

Json-lib可以将Java对象转成json格式的字符串，也可以将Java对象转换成xml格式的文档，同样可以将json字符串转换成Java对象或是将xml字符串转换成Java对象。


xml和json之间的转换
http://www.xml.com/pub/a/2006/05/31/converting-between-xml-and-json.html

XOM虽然也是一种面向对象的XML API，类似于DOM 的风格，但是它有一些与众不同的特性比如严格保持内存中对象的不变性，从而使XOM实例总是能序列化为正确的XML。此外，与其他Java XML API相比，XOM 追求更简单和更正规。
该项目主页:http://www.xom.nu/

ezmorph：
众所周知，在java中对象之间的赋值是地址引用关系，如：
A a = new A();
A b = a;
则修改b的属性，a的属性也会跟着修改。
       在很多场合下，我们希望克隆出一个新的对象出来，新对象的修改不会影响原有对象，这时我们一般常用的有如下两种方式：
使对象实现Cloneable接口，这个只适用于我们自己的java对象
使用jakatar commons的BeanUtils实现bean copy
     昨晚看到一个新的组件:EZMorph，可以实现同样的功能，这里简单介绍一下。
      EZMorph据说起源于json，后来独立出来了，有兴趣的可以到官网（http://ezmorph.sourceforge.net/）上浏览一下。
EZMorph的主要原理
    若要将A的属性赋给B，则经过如下步骤：
new一个B的实例
遍历A的属性
若A有某个属性P1，B也有一个属性P1，则将A的P1的属性值赋给B的P1



Json-lib（项目地址：http://json-lib.sourceforge.net/index.html）。json-lib最开始的也是应用最广泛的json解析工具，json-lib 不好的地方确实是依赖于很多第三方包，包括commons-beanutils.jar，commons-collections-3.2.jar，commons-lang-2.6.jar，commons-logging-1.1.1.jar，ezmorph-1.0.6.jar，对于复杂类型的转换，json-lib对于json转换成bean还有缺陷，比如一个类里面会出现另一个类的list或者map集合，json-lib从json到bean的转换就会出现问题。json-lib在功能和性能上面都不能满足现在互联网化的需求。

