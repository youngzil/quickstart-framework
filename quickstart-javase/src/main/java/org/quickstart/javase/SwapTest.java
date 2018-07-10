package org.quickstart.javase;

/**
 * Created by yangzl on 2017/8/6.
 */
public class SwapTest {

    public static void main(String[] args) {

        int a = 37;
        int b = 14;

        if (a != b) {
            a ^= b;
            b ^= a;
            a ^= b;
        }

        System.out.println("a=" + a);
        System.out.println("b=" + b);
    }

}
