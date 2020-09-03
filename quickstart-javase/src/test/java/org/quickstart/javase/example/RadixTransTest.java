/**
 * 项目名称：quickstart-javase 
 * 文件名：RadixTransTest.java
 * 版本信息：
 * 日期：2018年10月25日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.example;

import org.junit.Test;

/**
 * RadixTransTest
 *
 * @author：youngzil@163.com
 * @2018年10月25日 上午11:33:48
 * @since 1.0
 */
public class RadixTransTest {
    // 求十进制数num的radix进制形式
    // 使用java提供的方法
    // 但仅局限于比较常用的二进制、八进制、十六进制
    public static String trans1(int num, int radix) {
        if (radix == 2) {
            return Integer.toBinaryString(num);
        } else if (radix == 8) {
            return Integer.toOctalString(num);
        } else if (radix == 16) {
            return Integer.toHexString(num);
        }
        return null;
    }

    // 使用数组的形式进行转换
    public static void trans2(int num, int radix) {
        System.out.print(num + "转成" + radix + "进制数为：");

        // 创建数组，32位
        char[] arr = new char[32];

        // 创建参考字符数组
        char[] ch = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        // 指针，从数组最后开始
        int pos = 32;

        // 开始循环计算num和radix的商和余数
        while (num > 0) {
            arr[--pos] = ch[num % radix];
            num /= radix;

            /*
             * 这里是针对二进制、八进制和十六进制进行的移位运算
            arr[--pos] = ch[num&(radix-1)];
            if(radix == 2)
                num >>= 1;
            else if(radix == 8)
                num >>= 3;
            else if(radix == 16)
                num >>= 4;
            */
        }

        // 输出有效的进制数
        for (int i = pos; i < 32; i++) {
            System.out.print(arr[i]);
        }

        System.out.println();
    }

    // 使用StringBuilder进行转换
    public static String trans3(int num, int radix) {
        // 使用StringBuilder的reverse方法
        StringBuilder sb = new StringBuilder();

        while (num > 0) {
            // 把除以基数的余数存到缓冲区中
            sb.append(num % radix);
            num /= radix;
        }

        return sb.reverse().toString();
    }

    // 使用迭代方式转成二进制
    public static void toBin(int num) {
        if (num > 0) {
            toBin(num / 2);

            System.out.print(num % 2);
        }

    }

    public static void main(String[] args) {
        System.out.println("9的二进制数为：" + trans1(9, 2));
        trans2(19, 6);
        System.out.println("19的7进制数为：" + trans3(19, 7));
        System.out.print("10的二进制形式：");
        toBin(10);

        // 1. 各种进制字符串形式的相互转化
        System.out.println("把2,8,16的数字的字符串形式，转化为10进制：");
        System.out.println(Integer.parseInt("10", 10));
        System.out.println(Integer.parseInt("10", 2));
        System.out.println(Integer.parseInt("10", 8));
        System.out.println(Integer.parseInt("10", 16));
        System.out.println();

        System.out.println("把10进制，转化为2,8,16进制：");
        System.out.println(Integer.toString(10));
        System.out.println(Integer.toBinaryString(10));
        System.out.println(Integer.toOctalString(10));
        System.out.println(Integer.toHexString(10));
        System.out.println();

        // 2. 在输入输出的过程中，直接转化各种进制（注意：不能直接转化2进制，2进制需要用字符串处理）
        System.out.println("把8,16进制的数字，直接打印为10进制：");
        System.out.format("%d", 10).println();
        System.out.format("%d", 010).println();
        System.out.format("%d", 0x10).println();
        System.out.println();

        System.out.println("把10进制，直接打印为8,16进制，而且可以控制输出形式：");
        System.out.format("%d", 10).println();
        System.out.format("%o, %#o, %#4o, %#04o", 10, 10, 10, 10).println();
        System.out.format("%x, %#x, %#4x, %#04x", 10, 10, 10, 10).println();
        System.out.println();

        // 3. 格式化输出的字符串
        System.out.println("把10进制，输出为8,16进制到字符串，而且可以控制输出形式：");
        String s;
        System.out.println(s = String.format("%d", 10));
        System.out.println(s = String.format("%o, %#o, %#4o, %#04o", 10, 10, 10, 10));
        System.out.println(s = String.format("%x, %#x, %#4x, %#04x", 10, 10, 10, 10));
        System.out.println();

        /* Java中数值变量的声明： 
        二进制变量的声明以0b为前缀； 
        八进制变量的声明以0为前缀； 
        十六进制变量的声明以0x为前缀。*/

        // 二进制、八进制、十六进制数值在运用时候自动转为对应的十进制的值
        int a = 0b11; // 声明二进制变量
        int b = 011; // 声明八进制变量
        int c = 11; // 声明十进制变量
        int d = 0x11; // 声明十六进制变量
        System.out.println("a：" + a); // 3
        System.out.println("b：" + b); // 9
        System.out.println("c：" + c); // 11
        System.out.println("d：" + d); // 17

        /* Java中十进制转其它进制： 
        十进制转二进制：Integer.toBinaryString(i); 
        十进制转八进制：Integer.toOctalString(i); 
        十进制转十六进制：Integer.toHexString(i);*/
        int i = 15;
        System.out.println("十进制15对应的二进制为：" + Integer.toBinaryString(i)); // 1111
        System.out.println("十进制15对应的八进制为：" + Integer.toOctalString(i)); // 17
        System.out.println(i); // 15
        System.out.println("十进制15对应的十六进制为：" + Integer.toHexString(i)); // f

        // System.out.println(Integer.toString(3FFFF, int radix) );

    }

    @Test
    public void test1() {

        // System.out.println()会先自动转为10进制后再输出的；toBinaryString()表示转换为二进制进行字符串进行输出。

        //二进制
        int i = 0B101;
        System.out.println(i); //5
        System.out.println(Integer.toBinaryString(i));
        //八进制
        i = 0101;
        System.out.println(i); //65
        System.out.println(Integer.toBinaryString(i));
        //十进制
        i = 101;
        System.out.println(i); //101
        System.out.println(Integer.toBinaryString(i));
        //十六进制
        i = 0x101;
        System.out.println(i); //257
        System.out.println(Integer.toBinaryString(i));
    }

    @Test
    public void test2() {
        int i = 192;
        System.out.println("---------------------------------");
        System.out.println("十进制转二进制：" + Integer.toBinaryString(i)); //11000000
        System.out.println("十进制转八进制：" + Integer.toOctalString(i)); //300
        System.out.println("十进制转十六进制：" + Integer.toHexString(i)); //c0
        System.out.println("---------------------------------");
        // 统一利用的为Integer的valueOf()方法,parseInt方法也是ok的
        System.out.println("二进制转十进制：" + Integer.valueOf("11000000", 2).toString()); //192
        System.out.println("八进制转十进制：" + Integer.valueOf("300", 8).toString()); //192
        System.out.println("十六进制转十进制：" + Integer.valueOf("c0", 16).toString()); //192
        System.out.println("---------------------------------");
    }

    @Test
    public void test3() {
        long l = 100L;
        //如果不是最大值 前面都是0  输出的时候就不会有那么长了（所以下面使用最大/最小值示例）
        System.out.println(Long.toBinaryString(l)); //1100100
        System.out.println(Long.toBinaryString(l).length()); //7

        System.out.println("---------------------------------------");

        l = Long.MAX_VALUE; // 2的63次方 - 1
        //正数长度为63为（首位为符号位，0代表正数，省略了所以长度是63）
        //111111111111111111111111111111111111111111111111111111111111111
        System.out.println(Long.toBinaryString(l));
        System.out.println(Long.toBinaryString(l).length()); //63

        System.out.println("---------------------------------------");

        l = Long.MIN_VALUE; // -2的63次方
        //负数长度为64位（首位为符号位，1代表负数）
        //1000000000000000000000000000000000000000000000000000000000000000
        System.out.println(Long.toBinaryString(l));
        System.out.println(Long.toBinaryString(l).length()); //64
    }

    @Test
    public void testAnd() {
        int i = 0B100; // 十进制为4
        int j = 0B101; // 十进制为5

        // 二进制结果：100
        // 十进制结果：4
        System.out.println("二进制结果：" + Integer.toBinaryString(i & j));
        System.out.println("十进制结果：" + (i & j));
    }

    @Test
    public void testOr() {
        int i = 0B100; // 十进制为4
        int j = 0B101; // 十进制为5

        // 二进制结果：101
        // 十进制结果：5
        System.out.println("二进制结果：" + Integer.toBinaryString(i | j));
        System.out.println("十进制结果：" + (i | j));
    }

    @Test
    public void testNot() {
        int i = 0B100; // 十进制为4

        // 二进制结果：11111111111111111111111111111011
        // 十进制结果：-5
        System.out.println("二进制结果：" + Integer.toBinaryString(~i));
        System.out.println("十进制结果：" + (~i));
    }

    @Test
    public void testBitwiseOr() {
        int i = 0B100; // 十进制为4
        int j = 0B101; // 十进制为5

        // 二进制结果：1
        // 十进制结果：1
        System.out.println("二进制结果：" + Integer.toBinaryString(i ^ j));
        System.out.println("十进制结果：" + (i ^ j));
    }

    @Test
    public void testShiftLeft() {
        int i = 0B100; // 十进制为4

        int j = -0B100; // 十进制为-4
        System.out.println("i=" + Integer.toBinaryString(i));
        System.out.println("j=" + Integer.toBinaryString(j));

        // 二进制结果：100000
        // 十进制结果：32 = 4 * (2的3次方)
        System.out.println("二进制结果：" + Integer.toBinaryString(i << 2));
        System.out.println("二进制结果j：" + Integer.toBinaryString(j << 2));
        System.out.println("十进制结果：" + (i << 3));
    }

    @Test
    public void testPositiveNumberShiftRight() {
        int i = 0B100; // 十进制为4

        // 二进制结果：10
        // 十进制结果：2
        System.out.println("二进制结果：" + Integer.toBinaryString(i >> 1));
        System.out.println("十进制结果：" + (i >> 1));
    }

    @Test
    public void testNegativeNumberShiftRight() {
        int i = -0B100; // 十进制为-4

        // 二进制结果：11111111111111111111111111111110
        // 十进制结果：-2
        System.out.println("二进制结果：" + Integer.toBinaryString(i >> 1));
        System.out.println("十进制结果：" + (i >> 1));
    }

    @Test
    public void testUnsignedRight() {
        int i = -0B100; // 十进制为-4

        // 二进制结果：11111111111111111111111111111110（>>的结果）
        // 二进制结果：1111111111111111111111111111110（>>>的结果）
        // 十进制结果：2147483646
        System.out.println("二进制结果：" + Integer.toBinaryString(i >>> 1));
        System.out.println("十进制结果：" + (i >>> 1));
    }

    @Test
    public void test() {
        int i = 0B110; // 十进制为6
        i &= 0B11; // 效果同：i = i & 3

        // 二进制结果：10
        // 十进制结果：2
        System.out.println("二进制结果：" + Integer.toBinaryString(i));
        System.out.println("十进制结果：" + (i));
    }

    @Test
    public void test4() {
        int i = 100;
        int j = -2;

        System.out.println(((i >> 31) ^ (j >> 31)) == 0);

        j = 10;
        System.out.println(((i >> 31) ^ (j >> 31)) == 0);

        // 判断两个数字符号是否相同
        // int类型共32bit，右移31位那么就只剩下1个符号位了（因为是带符号右移动，所以正数剩0负数剩1），再对两个符号位做^异或操作结果为0就表明二者一致。

    }

    @Test
    public void test5() {
        // 判断一个数的奇偶性
        // 在十进制数中可以通过和2取余来做，对于位运算有一个更为高效的方式：
        // 为何&1能判断基偶性？因为在二进制下偶数的末位肯定是0，奇数的最低位肯定是1。
        // 而二进制的1它的前31位均为0，所以在和其它数字的前31位与运算后肯定所有位数都是0（无论是1&0还是0&0结果都是0），那么唯一区别就是看最低位和1进行与运算的结果喽：结果为1表示奇数，反则结果为0就表示偶数。
        System.out.println(isEvenNum(1)); //false
        System.out.println(isEvenNum(2)); //true
        System.out.println(isEvenNum(3)); //false
        System.out.println(isEvenNum(4)); //true
        System.out.println(isEvenNum(5)); //false
    }

    /**
     * 是否为偶数
     */
    private static boolean isEvenNum(int n) {
        return (n & 1) == 0;
    }

    @Test
    public void test6() {
       // 交换两个数的值（不借助第三方变量）
//        使用这种方式最大的好处是：容易理解。最大的坏处是：a+b,可能会超出int型的最大范围，造成精度丢失导致错误，造成非常隐蔽的bug。所以若你这样运用在生产环境的话，是有比较大的安全隐患的。

        int a = 3, b = 5;
        System.out.println(a + "-------" + b);
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println(a + "-------" + b);
    }

    @Test
    public void test7() {
        // 交换两个数的值（不借助第三方变量）

        // 这里使用最大值演示，以证明这样方式是不会溢出的
        int a = Integer.MAX_VALUE, b = Integer.MAX_VALUE - 10;

        System.out.println(a ^ a);
        System.out.println(b ^ b);

//        借助位运算的可逆性来完成操作：
//        由于全文都没有对a/b做加法运算，因此不能出现溢出现象，所以是安全的。这种做法的核心原理依据是：位运算的可逆性，使用异或来达成目的。
        System.out.println(a + "-------" + b);
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a + "-------" + b);
    }

}
