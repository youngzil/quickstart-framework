/**
 * 项目名称：quickstart-javase 文件名：OptionalTest.java 版本信息： 日期：2018年3月28日 Copyright yangzl Corporation 2018 版权所有 *
 */
package org.quickstart.javase.jdk8.function;

import org.junit.Test;

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
