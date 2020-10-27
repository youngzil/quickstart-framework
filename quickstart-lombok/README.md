[Lombok官网](https://projectlombok.org/)  
[Lombok介绍](http://zwitserloot.com)  
[Lombok Github](https://github.com/rzwitserloot/lombok)  


参考学习文章  
[Lombok简介](https://www.jianshu.com/p/365ea41b3573)  
[Lombok 之 ToString](https://blog.csdn.net/tuxedolinux/article/details/79340011)



@Setter
@Getter
可以放在POJO类的任意位置，并且会自从重写equal、hashCode、toString（按照属性定义顺序）等

使用了lombok的注解(@Setter,@Getter,@ToString,@@RequiredArgsConstructor,@EqualsAndHashCode或@Data)之后，就不需要编写或生成get/set等方法，很大程度上减少了代码量，而且减少了代码维护的负担。故强烈建议项目中使用lombok，去掉bean中get、set、toString、equals和hashCode等方法的代码。

@Getter 注解支持一个 lazy 属性，该属性默认为 false。当设置为 true 时，会启用延迟初始化，即当首次调用 getter 方法时才进行初始化。


@Data注解的作用相当于 @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode的合集。

另外@Log 省去了在LombokTest中添加 getLogger的如下代码： 
private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(LogExample.class.getName());



@NoArgsConstructor 注解
使用 @NoArgsConstructor 注解可以为指定类，生成默认的构造函数

@AllArgsConstructor 注解
使用 @AllArgsConstructor 注解可以为指定类，生成包含所有成员的构造函数

@RequiredArgsConstructorDemo 注解
使用 @RequiredArgsConstructor 注解可以为指定类必需初始化的成员变量，如 final 成员变量，生成对应的构造函数

@EqualsAndHashCode 注解
使用 @EqualsAndHashCode 注解可以为指定类生成 equals 和 hashCode 方法


@ToString 注解
使用 @ToString 注解可以为指定类生成 toString 方法


@Data 注解
@Data 注解与同时使用以下的注解的效果是一样的：
@ToString
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode


@Log 注解
若你将 @Log 的变体放在类上（适用于你所使用的日志记录系统的任何一种）；之后，你将拥有一个静态的 final log 字段，然后你就可以使用该字段来输出日志。


@Synchronized 注解
@Synchronized 是同步方法修饰符的更安全的变体。与 synchronized 一样，该注解只能应用在静态和实例方法上。它的操作类似于 synchronized 关键字，但是它锁定在不同的对象上。synchronized 关键字应用在实例方法时，锁定的是 this 对象，而应用在静态方法上锁定的是类对象。对于 @Synchronized 注解声明的方法来说，它锁定的是 $LOCK 或 $lock。


@Builder 注解
使用 @Builder 注解可以为指定类实现建造者模式，该注解可以放在类、构造函数或方法上。

 @SneakyThrows 注解
@SneakyThrows 注解用于自动抛出已检查的异常，而无需在方法中使用 throw 语句显式抛出。

@NonNull 注解
你可以在方法或构造函数的参数上使用 @NonNull 注解，它将会为你自动生成非空校验语句。

@Clean 注解
@Clean 注解用于自动管理资源，用在局部变量之前，在当前变量范围内即将执行完毕退出之前会自动清理资源，自动生成 try-finally 这样的代码来关闭流。

 @With 注解
在类的字段上应用 @With 注解之后，将会自动生成一个 withFieldName(newValue) 的方法，该方法会基于 newValue 调用相应构造函数，创建一个当前类对应的实例。

val
val 用在局部变量前面，相当于将变量声明为 final，此外 Lombok 在编译时还会自动进行类型推断。
    val example = new ArrayList<String>();
相当于
    final ArrayList<String> example = new ArrayList<String>();


