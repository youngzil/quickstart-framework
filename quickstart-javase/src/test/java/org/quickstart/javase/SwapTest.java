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

        testInteger();

        testMathabs();

        testDouble();
    }

    private static void testInteger() {
        Integer a = 50;
        Integer b = 50;
        Integer c = 150;
        Integer d = 150;
        System.out.println(a == b);
        System.out.println(c == d);
    }

    private static void testMathabs() {
        Short s = (short) Math.abs(Short.MIN_VALUE);
        System.out.println(s);

        // Long、Short、Byte
        System.out.println(Math.abs(Long.MIN_VALUE));
        System.out.println(Math.abs(Byte.MIN_VALUE));
        
        System.out.println("-----------------");
        Integer i = Integer.MIN_VALUE;
        Integer iabs = (Integer) Math.abs(i);
        System.out.println(i);
        System.out.println(i < 0 && i.intValue() == iabs.intValue());
        

        Integer dd = Integer.MAX_VALUE;
        System.out.println(dd > (dd + 1));
        
        Integer minvalue = Integer.MIN_VALUE;
        System.out.println(minvalue != 0 && minvalue == -minvalue);

    }

    private static void testDouble() {
        double i = Double.POSITIVE_INFINITY;
        System.out.println(i == (i + 1));
        System.out.println(i == (i - 1));

        double j = Double.NaN;
        System.out.println(j == j);

    }

}
