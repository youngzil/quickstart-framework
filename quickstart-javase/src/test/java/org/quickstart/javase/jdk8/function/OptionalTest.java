/**
 * 项目名称：quickstart-javase 文件名：OptionalTest.java 版本信息： 日期：2018年3月28日 Copyright yangzl Corporation 2018 版权所有 *
 */
package org.quickstart.javase.jdk8.function;

import org.junit.Test;
import org.quickstart.javase.jdk.hashcode.User;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * OptionalTest
 *
 * https://blog.csdn.net/aitangyong/article/details/54564100
 *
 * @author：youngzil@163.com
 * @2018年3月28日 下午8:20:38
 * @since 1.0
 */
public class OptionalTest {

    // Option创建
    @Test
    public void testOptionalCreate() {

        //        Optional类提供类三个方法用于实例化一个Optional对象，它们分别为empty()、of()、ofNullable()，这三个方法都是静态方法，可以直接调用。

        //        empty()方法用于创建一个没有值的Optional对象：
        //        empty()方法创建的对象没有值，如果对emptyOpt变量调用isPresent()方法会返回false，调用get()方法抛出NullPointerException异常。
        Optional<String> emptyOpt = Optional.empty();

        //        of()方法使用一个非空的值创建Optional对象：
        String str = "Hello World";
        Optional<String> notNullOpt = Optional.of(str);
        notNullOpt.ifPresent(System.out::println);

        //        ofNullable()方法接收一个可以为null的值：
        //        如果str的值为null，得到的nullableOpt是一个没有值的Optional对象。
        Optional<String> nullableOpt = Optional.ofNullable(str);

    }

    @Test
    public void testOptionalObtain() {

        //        使用Optional中提供的map()方法可以以更简单的方式实现：
        Optional<User> userOpt = Optional.ofNullable(new User());
        Optional<String> roleIdOpt = userOpt.map(User::getName);

        //        使用orElse()方法获取值
        //        Optional类还包含其他方法用于获取值，这些方法分别为：
        //        1、 orElse()：如果有值就返回，否则返回一个给定的值作为默认值；
        //        2、orElseGet()：与orElse()方法作用类似，区别在于生成默认值的方式不同。该方法接受一个Supplier<? extends T>函数式接口参数，用于生成默认值；
        //        3、orElseThrow()：与前面介绍的get()方法类似，当值为null时调用这两个方法都会抛出NullPointerException异常，区别在于该方法可以指定抛出的异常类型。
        String str = "Hello World";
        Optional<String> strOpt = Optional.of(str);
        String orElseResult = strOpt.orElse("Hello Shanghai");
        String orElseGet = strOpt.orElseGet(() -> "Hello Shanghai");
        String orElseThrow =
            strOpt.orElseThrow(() -> new IllegalArgumentException("Argument 'str' cannot be null or blank."));

        //        此外，Optional类还提供了一个ifPresent()方法，该方法接收一个Consumer<? super T>函数式接口，一般用于将信息打印到控制台：
        Optional<String> strOpt2 = Optional.of("Hello World");
        strOpt2.ifPresent(System.out::println);

        //        使用filter()方法过滤
        //        filter()方法可用于判断Optional对象是否满足给定条件，一般用于条件过滤：
        //        在上面的代码中，如果filter()方法中的Lambda表达式成立，filter()方法会返回当前Optional对象值，否则，返回一个值为空的Optional对象。

        Optional<String> optional = Optional.of("lw900925@163.com");
        optional = optional.filter(str2 -> str2.contains("164"));

        //        1、尽量避免在程序中直接调用Optional对象的get()和isPresent()方法；
        //        2、避免使用Optional类型声明实体类的属性；
        //        第一条建议中直接调用get()方法是很危险的做法，如果Optional的值为空，那么毫无疑问会抛出NullPointerException异常，而为了调用get()方法而使用isPresent()方法作为空值检查，这种做法与传统的用if语句块做空值检查没有任何区别。
        //        第二条建议避免使用Optional作为实体类的属性，它在设计的时候就没有考虑过用来作为类的属性，如果你查看Optional的源代码，你会发现它没有实现java.io.Serializable接口，这在某些情况下是很重要的（比如你的项目中使用了某些序列化框架），使用了Optional作为实体类的属性，意味着他们不能被序列化。

        //不要使用isPresent()判断之后再使用get()取值，这种做法与传统的用if语句块做空值检查没有任何区别。
        //要使用orElse、orElseGet、orElseThrow

        //        总结一下，新的Optional类让我们可以以函数式编程的方式处理null值，抛弃了Java 8之前需要嵌套大量if-else代码块，使代码可读性有了很大的提高。

    }

    @Test
    public void testUsage() {

        // Optional类已经成为Java 8类库的一部分，在Guava中早就有了，可能Oracle是直接拿来使用了
        // Optional用来解决空指针异常，使代码更加严谨，防止因为空指针NullPointerException对代码造成影响
        String msg = "hello";
        Optional<String> optional = Optional.of(msg);
        // 判断是否有值，不为空
        boolean present = optional.isPresent();
        // 如果有值，则返回值，如果等于空则抛异常
        String value = optional.get();
        // 如果为空，返回else指定的值
        String hi = optional.orElse("hi");
        // 如果值不为空，就执行Lambda表达式
        optional.ifPresent(opt -> System.out.println(opt));

    }

  /*Optional类
（二）常用方法
    1、isPresent()
    2、ifPresent()
    3、orElse()
    4、orElseGet()
    5、get()
    6、orElseThrow()
    7、map()
    8、flatMap()
    9、filter()*/

    @Test
    public void testOptionalMethod() {

        // 1、isPresent()
        System.out
            .println(Arrays.asList("1", "2", "3").stream().filter(x -> x.equals("3")).findAny().isPresent());    // true

        // 2、ifPresent()
        Arrays.asList("1", "2", "3").stream().filter(x -> x.equals("3")).findAny()
            .ifPresent(System.out::println);    // 3

        // 3、orElse()
        System.out.println(
            Arrays.asList("1", "2", "3").stream().filter(x -> x.equals("4")).findAny().orElse("nothing"));    // nothing

        // 4、orElseGet()
        System.out.println(Arrays.asList("1", "2", "3").stream().filter(x -> x.equals("4")).findAny()
            .orElseGet(() -> String.format("orElseGet:%d", 4)));    // orElseGet:4

        // 5、get()
        System.out.println(Arrays.asList("1", "2", "3").stream().filter(x -> x.equals("4")).findAny()
            .get());    // NoSuchElementException

        // 6、orElseThrow()
        System.out.println(Arrays.asList("1", "2", "3").stream().filter(x -> x.equals("4")).findAny()
            .orElseThrow(IllegalArgumentException::new));    // java.lang.IllegalArgumentException

        // 7、map()
        System.out.println(
            Arrays.asList("1", "2", "3").stream().filter(x -> x.equals("4")).findAny().map(x -> "OptMap" + x)
                .orElse("nothing"));    // nothing

        // 8、flatMap()
        System.out.println(Arrays.asList("1", "2", "3").stream().filter(x -> x.equals("3")).findAny()
            .flatMap(x -> Optional.of(Integer.parseInt(x))).orElse(0));    // 3

        // 9、filter()
        System.out.println(
            Arrays.asList("1", "2", "3").stream().filter(x -> x.equals("3")).findAny().filter(s -> s.equals("3"))
                .orElse("nothing"));    // 3

    }

    @Test
    public void testOption() {
        // 繁琐的代码
        Outer outer = new Outer();
        if (outer != null && outer.nested != null && outer.nested.inner != null) {
            System.out.println(outer.nested.inner.foo);
        }

        // 在 Java8 中，我们有更优雅的解决方式，那就是使用 Optional是说，我们可以在一行代码中，进行流水式的 map 操作。而 map 方法内部会自动进行空校验：

        Optional.of(new Outer())//
            .map(Outer::getNested)//
            .map(Nested::getInner)//
            .map(Inner::getFoo)//
            .ifPresent(System.out::println);

        // 如果不为空，最终输出 foo 的值

        // 通过 suppiler 函数自定义方法

        Outer obj = new Outer();
        // 直接调用 resolve 方法，内部做空指针的处理
        resolve(() -> obj.getNested().getInner().getFoo())//
            .ifPresent(System.out::println);
        // 如果不为空，最终输出 foo 的值

    }

    public static <T> Optional<T> resolve(Supplier<T> resolver) {
        try {
            T result = resolver.get();
            return Optional.ofNullable(result);

        } catch (NullPointerException e) {
            // 可能会抛出空指针异常，直接返回一个空的 Optional 对象
            return Optional.empty();
        }
    }

    // 最外层对象
    class Outer {
        Nested nested;

        Nested getNested() {
            return nested;
        }
    }

    // 第二层对象
    class Nested {
        Inner inner;

        Inner getInner() {
            return inner;
        }
    }

    // 最底层对象
    class Inner {
        String foo;

        String getFoo() {
            return foo;
        }
    }

    /**
     * JDK8 Optional Created by chengbx on 2018/5/28. Optional<T> 类是一个容器类，代表一个值存在或者不存在，原来用null表示一个值不存在， 现在Optional可以更好的表达这个概念。并且可以避免空指针异常。 Optional．of（T t）：创建一个 Optional实例 Optional． empty（）：创建一个空的
     * Optional实例 Optional． ofNullable（T t）：若t不为nul1，创建 Optional实例，则创建空实例 ispresent（）：判判断是否包含值 orElse（T t）：如如果调用对象包含值，返返回该值，否则返回t orElseGet（ Supplier s）：如果调用对象包含值，返返回该值，否则返回s获取取的值 map（ Function
     * f）：如如果有值对其处理，并返回处理后的 Optional，否则返回 Optional、 empty flatMap（ Function mapper）：与map类似，要求返回值必须是 Optional
     *
     */

    public static void main(String[] args) {
        Optional<String> fullName = Optional.ofNullable(null);
        System.out.println("Full Name is set? " + fullName.isPresent());
        System.out.println("Full Name: " + fullName.orElseGet(() -> "[none]"));
        System.out.println(fullName.map(s -> "Hey " + s + "!").orElse("Hey Stranger!"));

        Optional<String> firstName = Optional.of("Tom");
        System.out.println("First Name is set? " + firstName.isPresent());
        System.out.println("First Name: " + firstName.orElseGet(() -> "[none]"));
        System.out.println(firstName.map(s -> "Hey " + s + "!").orElse("Hey Stranger!"));
        System.out.println();

        // 参数不能是null
        Optional<Integer> optional1 = Optional.of(1);

        // 参数可以是null
        Optional<Integer> optional2 = Optional.ofNullable(null);

        // 参数可以是非null
        Optional<Integer> optional3 = Optional.ofNullable(2);

        // Optional.empty()：所有null包装成的Optional对象：
        Optional<Integer> optional4 = Optional.ofNullable(null);
        Optional<Integer> optional5 = Optional.ofNullable(null);
        System.out.println(optional4 == optional5);// true
        System.out.println(optional4 == Optional.<Integer>empty());// true

        Object o1 = Optional.<Integer>empty();
        Object o2 = Optional.<String>empty();
        System.out.println(o1 == o2);// true

        // isPresent()：判断值是否存在
        Optional<Integer> optional6 = Optional.ofNullable(1);
        Optional<Integer> optional7 = Optional.ofNullable(null);

        // isPresent判断值是否存在
        System.out.println(optional6.isPresent() == true);
        System.out.println(optional7.isPresent() == false);

        // ifPresent(Consumer consumer)：如果option对象保存的值不是null，则调用consumer对象，否则不调用
        Optional<Integer> optional8 = Optional.ofNullable(1);
        Optional<Integer> optional9 = Optional.ofNullable(null);

        // 如果不是null,调用Consumer
        optional8.ifPresent(new Consumer<Integer>() {
            @Override
            public void accept(Integer t) {
                System.out.println("value is " + t);
            }
        });

        // null,不调用Consumer
        optional9.ifPresent(new Consumer<Integer>() {
            @Override
            public void accept(Integer t) {
                System.out.println("value is " + t);
            }
        });

        // orElse(value)：如果optional对象保存的值不是null，则返回原来的值，否则返回value
        Optional<Integer> optional10 = Optional.ofNullable(1);
        Optional<Integer> optional11 = Optional.ofNullable(null);

        // orElse
        System.out.println(optional10.orElse(1000) == 1);// true
        System.out.println(optional11.orElse(1000) == 1000);// true

        // orElseGet(Supplier supplier)：功能与orElse一样，只不过orElseGet参数是一个对象
        Optional<Integer> optional12 = Optional.ofNullable(1);
        Optional<Integer> optional13 = Optional.ofNullable(null);

        System.out.println(optional12.orElseGet(() -> {
            return 1000;
        }) == 1);// true

        System.out.println(optional13.orElseGet(() -> {
            return 1000;
        }) == 1000);// true

        // orElseThrow()：值不存在则抛出异常，存在则什么不做，有点类似Guava的Precoditions
        Optional<Integer> optional14 = Optional.ofNullable(1);
        Optional<Integer> optional15 = Optional.ofNullable(null);

    /* optional14.orElseThrow(() -> {
         throw new IllegalStateException();
     });
    
     try {
         // 抛出异常
         optional15.orElseThrow(() -> {
             throw new IllegalStateException();
         });
     } catch (IllegalStateException e) {
         e.printStackTrace();
     }*/

        // filter(Predicate)：判断Optional对象中保存的值是否满足Predicate，并返回新的Optional。
        Optional<Integer> optional16 = Optional.ofNullable(1);
        Optional<Integer> optional17 = Optional.ofNullable(null);

        Optional<Integer> filter1 = optional16.filter((a) -> a == null);
        Optional<Integer> filter2 = optional16.filter((a) -> a == 1);
        Optional<Integer> filter3 = optional17.filter((a) -> a == null);

        System.out.println(filter1.isPresent());// false
        System.out.println(filter2.isPresent());// true
        System.out.println(filter2.get().intValue() == 1);// true
        System.out.println(filter3.isPresent());// false

        // map(Function)：对Optional中保存的值进行函数运算，并返回新的Optional(可以是任何类型)
        Optional<Integer> optional18 = Optional.ofNullable(1);
        Optional<Integer> optional19 = Optional.ofNullable(null);

        Optional<String> str1Optional = optional18.map((a) -> "key" + a);
        Optional<String> str2Optional = optional19.map((a) -> "key" + a);

        System.out.println(str1Optional.get());// key1
        System.out.println(str2Optional.isPresent());// false

        // flatMap()：功能与map()相似，差别请看如下代码。flatMap方法与map方法类似，区别在于mapping函数的返回值不同。map方法的mapping函数返回值可以是任何类型T，而flatMap方法的mapping函数必须是Optional。
        Optional<Integer> optional20 = Optional.ofNullable(1);

        Optional<Optional<String>> str1Optional3 = optional20.map((a) -> {
            return Optional.<String>of("key" + a);
        });

        Optional<String> str2Optional4 = optional20.flatMap((a) -> {
            return Optional.<String>of("key" + a);
        });

        System.out.println(str1Optional3.get().get());// key1
        System.out.println(str2Optional4.get());// key1

    }

}
