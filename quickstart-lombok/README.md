https://projectlombok.org/
http://zwitserloot.com/html/
https://github.com/rzwitserloot/lombok


参考学习文章
https://www.jianshu.com/p/365ea41b3573
https://blog.csdn.net/tuxedolinux/article/details/79340011



@Setter
@Getter
可以放在POJO类的任意位置，并且会自从重写equal、hashCode、toString（按照属性定义顺序）等

使用了lombok的注解(@Setter,@Getter,@ToString,@@RequiredArgsConstructor,@EqualsAndHashCode或@Data)之后，就不需要编写或生成get/set等方法，很大程度上减少了代码量，而且减少了代码维护的负担。故强烈建议项目中使用lombok，去掉bean中get、set、toString、equals和hashCode等方法的代码。

@Data注解的作用相当于 @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode的合集。

另外@Log 省去了在LombokTest中添加 getLogger的如下代码： 
private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(LogExample.class.getName());



