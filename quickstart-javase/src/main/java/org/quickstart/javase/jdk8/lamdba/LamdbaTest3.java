package org.quickstart.javase.jdk8.lamdba;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2020/4/24 14:31
 */
public class LamdbaTest3 {

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
