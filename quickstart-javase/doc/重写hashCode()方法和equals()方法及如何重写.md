```
重写hashCode()方法和equals()方法及如何重写
http://blog.csdn.net/jing_bufferfly/article/details/50868266


我想写的问题有三个：
1、首先我们为什么需要重写hashCode()方法和equals()方法
2、在什么情况下需要重写hashCode()方法和equals()方法
3、如何重写这两个方法

*********************************************************************
第一个问题：为什么需要重写hashCode()方法和equals()方法
    Java中的超类Object类中定义的equals()方法是用来比较两个引用所指向的对象的内存地址是否一致
Object类中equals()方法的源码
public boolean equals(Object obj) {
       return (this == obj);
}
********************************************************************

Object类中的hashCode()方法，用native关键字修饰，说明这个方法是个原生函数，也就说这个方法的实现不是用java语言实现的，是使用c/c++实现的，并且被编译成了DLL，由java去调用，jdk源码中不包含。对于不同的平台它们是不同的，java在不同的操作系统中调用不同的native方法实现对操作系统的访问，因为java语言不能直接访问操作系统底层，因为它没有指针。
这种方法调用的过程：
1、在java中申明native方法，然后编译
2、用javah产生一个  .h  文件
3、写一个 .cpp文件实现native导出方法，其中需要包含第二步产生的.h文件(其中又包含了jdk带的jni.h文件)；
4、将.cpp文件编译成动态链接库文件
5、在java中用System.loadLibrary()文件加载第四步产生的动态链接库文件，然后这个navite方法就可被访问了
Java的API文档对hashCode()方法做了详细的说明，这也是我们重写hashCode()方法时的原则【Object类】

重点要注意的是：
a.  在java应用程序运行时，无论何时多次调用同一个对象时的hsahCode()方法，这个对象的hashCode()方法的返回值必须是相同的一个int值
b.  如果两个对象equals()返回值为true,则他们的hashCode()也必须返回相同的int值
c.  如果两个对象equals()返回值为false,则他们的hashCode()返回值也必须不同

public native int hashCode();

现在到了说正题了，为什么要重写
我们在定义类时，我们经常会希望两个不同对象的某些属性值相同时就认为他们相同，所以我们要重写equals()方法，按照原则，我们重写了equals()方法，也要重写hashCode()方法，要保证上面所述的b,c原则;所以java中的很多类都重写了这两个方法,例如String类，包装类

4、第二个问题：在什么情况下需要重写hashCode()方法和equals()方法
当我们自定义的一个类，想要把它的实例保存在集合中时，我们就需要重写这两个方法；集合(Collection)有两个类，一个是List,一个是Set
List:集合中的元素是有序的，可以重复的
Set:无序，不可重复的
以HashSet来说明：
HashSet存放元素时，根据元素的hashCode值快速找到要存储的位置，如果这个位置有元素，两个对象通过equals()比较，如果返回值为true,则不放入；如果返回值为false,则这个时候会以链表的形式在同一个位置上存放两个元素，这会使得HashSet的性能降低，因为不能快速定位了。还有一种情况就是两个对象的hashCode()返回值不同，但是equals()返回true,这个时候HashSet会把这两个对象都存进去，这就和Set集合不重复的规则相悖了;所以，我们重写了equals()方法时，要按照b,c规则重写hashCode()方法！


【关于DLL文件】http://wapbaike.baidu.com/view/230750.htm?adapt=1&fr=aladdin
【为什么重写】  http://m.blog.csdn.net/article/details?id=8197508（这篇讲的



在集合框架中的HashSet，HashTable和HashMap都使用哈希表的形式存储数据，而hashCode计算出来的哈希码便是它们的身份证。哈希码的存在便可以： 

快速定位对象，提高哈希表集合的性能。
只有当哈希表中对象的索引即hashCode和对象的属性即equals同时相等时，才能够判断两个对象相等。
从上面可以看出，哈希码主要是为哈希表服务的，其实如果不需要使用哈希表，也可以不重写hashCode。但是SUN公司应该是出于对程序扩展性的考虑（万一以后需要将对象放入哈希表集合中），才会规定重写equals的同时需要重写hashCode，以避免后续开发不必要的麻烦。

重写equals的注意事项
Java语言规范要求equals需要具有如下的特性： 
自反性：对于任何非空引用 x，x.equals() 应该返回 true。
对称性：对于任何引用 x 和 y，当且仅当 y.equals(x) 返回 true，x.equals(y) 也应该返回 true。
传递性：对于任何引用 x、y 和 z，如果 x.equals(y)返回 true，y.equals(z) 也应返回同样的结果。
一致性：如果 x 和 y 引用的对象没有发生变化，反复调用 x.equals(y) 应该返回同样的结果。
对于任意非空引用 x，x.equals(null) 应该返回 false。








