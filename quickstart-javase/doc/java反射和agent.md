```
RTTI和反射机制区别
反射的使用：获取类、构造方法，成员变量，成员方法、注解、泛型

---------------------------------------------------------------------------------------------------------------------
参考
https://blog.csdn.net/sinat_38259539/article/details/71799078
https://blog.csdn.net/xu__cg/article/details/52862402
https://blog.csdn.net/xu__cg/article/details/52882023
https://blog.csdn.net/xu__cg/article/details/52877573

RTTI和反射机制

RTTI(Runtime Type Information，运行时类信息)。

Java让我们在运行时识别对象和类的信息，主要有2种方式：一种是传统的RTTI，它假定我们在编译时已经知道了所有的类型信息；另一种是反射机制，它允许我们在运行时发现和使用类的信息。

并不是所有的Class都能在编译的时候明确，某些情况下，需要运行才能知道具体的类型信息，例如JDK动态代理，这就是RTTI(Runtime Type Information，运行时类信息)。
在java中，有两种RTTI的方式，一种是传统的，即假设在编译时已经知道了所有的类型；还有一种，是利用反射机制，在运行时再尝试确定类型信息。


RTTI和反射之间的真正区别只在于：
RTTI：编译器在编译时打开和检查.class文件
反射：运行时打开和检查.class文件

严格的说，反射也是一种形式的RTTI，不过，一般的文档资料中把RTTI和反射分开，因为一般的，大家认为RTTI指的是传统的RTTI，通过继承和多态来实现，在运行时通过调用超类的方法来实现具体的功能（超类会自动实例化为子类，或使用instance of）。

传统的RTTI有3种实现方式：
1、向上转型或向下转型（upcasting and downcasting），在java中，向下转型（父类转成子类）需要强制类型转换
2、Class对象（用了Class对象，不代表就是反射，如果只是用Class对象cast成指定的类，那就还是传统的RTTI）instanceof或isInstance()
3、传统的RTTI与反射最主要的区别，在于RTTI在编译期需要.class文件，而反射不需要。传统的RTTI使用转型或Instance形式实现，但都需要指定要转型的类型

反射
那到底什么是反射（Reflection）呢？反射有时候也被称为内省（Introspection），事实上，反射，就是一种内省的方式，Java不允许在运行时改变程序结构或类型变量的结构，但它允许在运行时去探知、加载、调用在编译期完全未知的class，可以在运行时加载该class，生成实例对象（instance object），调用method，或对field赋值。这种类似于“看透”了class的特性被称为反射（Reflection），我们可以将反射直接理解为：可以看到自己在水中的倒影，这种操作与直接操作源代码效果相同，但灵活性高得多。

反射
java最屌的功能，没有之一，就是反射，java的反射允许你在运行期间对一个完全未知的类进行任何操作（通过类名），包括所有私有成员，甚至是匿名内部类，当然使用反射的前提是，必须得到这个类class文件。


类加载器首先会检查这个类的Class对象是否已被加载过，如果尚未加载，默认的类加载器就会根据类名查找对应的.class文件。
　　想在运行时使用类型信息，必须获取对象(比如类Base对象)的Class对象的引用，使用功能Class.forName(“Base”)可以实现该目的，或者使用base.class。

注意，有一点很有趣，
使用功能”.class”来创建Class对象的引用时，不会自动初始化该Class对象，
使用forName()会自动初始化该Class对象。


为了使用类而做的准备工作一般有以下3个步骤：
加载：由类加载器完成，找到对应的字节码，创建一个Class对象
链接：验证类中的字节码，为静态域分配空间
初始化：如果该类有超类，则对其初始化，执行静态初始化器和静态初始化块


Class类与java.lang.reflect类库一起对反射进行了支持，该类库包含Field、Method和Constructor类，这些类的对象由JVM在启动时创建，用以表示未知类里对应的成员。这样的话就可以使用Contructor创建新的对象，用get()和set()方法获取和修改类中与Field对象关联的字段，用invoke()方法调用与Method对象关联的方法。另外，还可以调用getFields()、getMethods()和getConstructors()等许多便利的方法，以返回表示字段、方法、以及构造器对象的数组，这样，对象信息可以在运行时被完全确定下来，而在编译时不需要知道关于类的任何事情。

反射机制并没有什么神奇之处，当通过反射与一个未知类型的对象打交道时，JVM只是简单地检查这个对象，看它属于哪个特定的类。因此，那个类的.class对于JVM来说必须是可获取的，要么在本地机器上，要么从网络获取。

所以对于RTTI和反射之间的真正区别只在于：
RTTI，编译器在编译时打开和检查.class文件
反射，运行时打开和检查.class文件

反射是框架设计的灵魂：通过把.class文件加载到内存，使用Class对象表示，反向获取类的信息，包括类信息，变量，方法等
（使用的前提条件：必须先得到代表的字节码的Class，Class类用于表示.class文件（字节码））

Class 类的实例表示正在运行的 Java 应用程序中的类和接口。也就是jvm中有N多的实例每个类都有该Class对象。（包括基本数据类型）
Class 没有公共构造方法。Class 对象是在加载类时由 Java 虚拟机以及通过调用类加载器中的defineClass 方法自动构造的。也就是这不需要我们自己去处理创建，JVM已经帮我们创建好了。



获取Class对象：三种：Class.forName、类名.class、类实例对象.getClass()
获取java类对象3种方式：只是加载，没有初始化，类从被加载到虚拟机内存中开始，到卸载出内存为止，它的整个生命周期包括：加载、验证、准备、解析、初始化、使用和卸载七个阶段。
F f = new F();
// 第一种表达方式
Class c1 = F.class;// 这种表达方式同时也告诉了我们任何一个类都有一个隐含的静态成员变量class
// 第二种表达方式
Class c2 = f.getClass();// 这种表达方式在已知了该类的对象的情况下通过getClass方法获取
// 第三种表达方式
Class c3 = Class.forName("service.impl.F");// 类的全称


获取Class对象的三种方式
1.1 Object ——> getClass();
1.2 任何数据类型（包括基本数据类型）都有一个“静态”的class属性，不会自动初始化该Class对象，
1.3 通过Class类的静态方法：forName（String  className）(常用)，会自动初始化该Class对象。


注意：在运行期间，一个类，只有一个Class对象产生。
三种方式常用第三种，第一种对象都有了还要反射干什么。第二种需要导入类的包，依赖太强，不导包就抛编译错误。一般都第三种，一个字符串可以传入也可写在配置文件中等多种方法。



反射API用来生成JVM中的类、接口或则对象的信息。 
- Class类：反射的核心类，可以获取类的属性，方法等信息。 
- Field类：Java.lang.reflec包中的类，表示类的成员变量，可以用来获取和设置类之中的属性值。 
- Method类： Java.lang.reflec包中的类，表示类的方法，它可以用来获取类中的方法信息或者执行方法。 
- Constructor类： Java.lang.reflec包中的类，表示类的构造方法。

public Constructor[] getConstructors()：所有”公有的”构造方法 
public Constructor[] getDeclaredConstructors()：获取所有的构造方法(包括私有、受保护、默认、公有) 
con.setAccessible(true);//暴力访问(忽略掉访问修饰符)  
obj = con.newInstance(’男’); 初始化对象

Field[] getFields():获取所有的”公有字段” 
Field[] getDeclaredFields():获取所有字段，包括：私有、受保护、默认、公有； 
f.setAccessible(true);//暴力反射，解除私有限定  
f.set(obj, ”18888889999”);  给obj对象设置属性值

public Method[] getMethods():获取所有”公有方法”；（包含了父类的方法也包含Object类） 
public Method[] getDeclaredMethods():获取所有的成员方法，包括私有的(不包括继承的) 
m.setAccessible(true);//解除私有限定  
Object result = m.invoke(obj, 20);//调用object的m函数，需要两个参数，一个是要调用的对象（获取有反射），一个是实参  


getAnnotation：返回程序元素上存在的指定类型的注解，如果该类型的注解不存在则返回null
getAnnotations：返回程序元素上存在的所有注解
isAnnotationPresent：判断该程序元素上是否包含指定类型的注解，存在返回true，否则返回false
getDeclaredAnnotations：返回直接存在于此元素上的所有注解


1.反射机制概念 
在Java中的反射机制是指在运行状态中，对于任意一个类都能够知道这个类所有的属性和方法；并且对于任意一个对象，都能够调用它的任意一个方法；这种动态获取信息以及动态调用对象方法的功能成为Java语言的反射机制。

2.反射的应用场合 
在Java程序中许多对象在运行是都会出现两种类型：编译时类型和运行时类型。 
编译时的类型由声明对象时实用的类型来决定，运行时的类型由实际赋值给对象的类型决定 


Java采用泛型擦除机制来引入泛型。Java中的泛型仅仅是给编译器Javac使用的，确保数据的安全性和免去强制类型转换的麻烦。但是编译一旦完成，所有和泛型有关的类型全部被擦除。 
　　为了通过反射操作这些类型以迎合实际开发的需要，Java新增了ParameterizedType,GenericArrayType,TypeVariable和WildcardType几种类型来代表不能被归一到Class类中的类型但是又和原始类型齐名的类型。 

ParameterizedType:表示一种参数化的类型，比如Collection< String >
GenericArrayType:表示一种元素类型是参数化类型或者类型变量的数组类型
TypeVariable:是各种类型变量的公共父接口
WildcardType:代表一种通配符类型表达式，比如？、？ extends Number、？ super Integer。（wildcard是一个单词：就是”通配符“）


---------------------------------------------------------------------------------------------------------------------

java.lang.reflect.Type类：
/Users/yangzl/git/quickstart-framework/quickstart-javase/src/main/java/org/quickstart/javase/jdk/reflection/ReflectionUtil.java

ReflectionUtil就是为了解决这类问题的辅助工具类，为java.lang.reflect标准库的工具类。它提供了便捷的访问泛型对象类型(java.reflect.Type)的反射方法。

ReflectionUtil中包含以下几种功能：
通过Type获取对象class;
通过Type创建对象;
获取泛型对象的泛型化参数;
检查对象是否存在默认构造函数;
获取指定类型中的特定field类型;
获取指定类型中的特定method返回类型;
根据字符串标示获取枚举常量;


参考
https://www.cnblogs.com/whitewolf/p/4355541.html

---------------------------------------------------------------------------------------------------------------------



