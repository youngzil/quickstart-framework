/**
 * 项目名称：quickstart-javase 
 * 文件名：RadixTransTest.java
 * 版本信息：
 * 日期：2018年10月25日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.example;

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
        if (radix == 2)
            return Integer.toBinaryString(num);
        else if (radix == 8)
            return Integer.toOctalString(num);
        else if (radix == 16)
            return Integer.toHexString(num);
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
        for (int i = pos; i < 32; i++)
            System.out.print(arr[i]);

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
}
