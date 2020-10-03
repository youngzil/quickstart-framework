package org.quickstart.javase.jdk8.lamdba;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2020/4/24 14:31
 */
public class LamdbaTest3 {

    @Test
    public void  testBuild(){
        // 使用双冒号::来构造静态函数引用
        Function<String, Integer> fun = Integer::parseInt;
        Integer value = fun.apply("123");
        System.out.println(value);

        // 使用双冒号::来构造非静态函数引用
        String content = "Hello JDK8";
        Function<Integer, String> func = content::substring;
        String result = func.apply(1);
        System.out.println(result);

        // 构造函数引用
        BiFunction<String, Integer, User> biFunction = User::new;
        User user = biFunction.apply("mengday", 28);
        System.out.println(user.toString());

        // 函数引用也是一种函数式接口，所以也可以将函数引用作为方法的参数
        sayHello(String::toUpperCase, "hello");
    }

    // 方法有两个参数，一个是
    private static void sayHello(Function<String, String> func, String parameter){
        String result = func.apply(parameter);
        System.out.println(result);
    }

    public static void main(String[] args) {
        repeatMessage("Hello", 20);
    }
    public static void repeatMessage(String text, int count) {
        Runnable r = () -> {
            for(int i = 0; i < count; i++) {
                System.out.println(text);
                Thread.yield();
            }
        };
        new Thread(r).start();
    }

}
