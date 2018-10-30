/**
 * 项目名称：quickstart-javase 
 * 文件名：RadixTransTest.java
 * 版本信息：
 * 日期：2018年10月25日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.example;

/**
 * RadixTransTest
 * 
 * @author：yangzl@asiainfo.com
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

    }
}
