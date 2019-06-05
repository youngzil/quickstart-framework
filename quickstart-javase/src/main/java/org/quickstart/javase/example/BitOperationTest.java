/**
 * 项目名称：quickstart-javase 
 * 文件名：BitOperationTest.java
 * 版本信息：
 * 日期：2018年10月25日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.example;

import java.util.Arrays;

/**
 * BitOperationTest
 * 
 * @author：youngzil@163.com
 * @2018年10月25日 下午7:30:11
 * @since 1.0
 */
public class BitOperationTest {

    // 1.获得int型最大值
    int getMaxInt() {
        return (1 << 31) - 1;// 2147483647， 由于优先级关系，括号不可省略
        // 另一种写法
        // return ~(1 << 31);//2147483647

        // 另一种写法，有些编译器不适用
        // return (1 << -1) - 1;//2147483647
    }

    // 2.获得int型最小值
    int getMinInt() {
        return 1 << 31;// -2147483648

        // 另一种写法有些编译器不适用
        // return 1 << -1;//-2147483648
    }

    // 3.获得long类型的最大值
    // 获得long最小值，和其他类型的最大值，最小值同理.
    long getMaxLong() {
        return ((long) 1 << 127) - 1;// 9223372036854775807
    }

    // 4.乘以2运算
    int mulTwo(int n) {// 计算n*2
        return n << 1;
    }

    // 5.除以2运算
    int divTwo(int n) {// 负奇数的运算不可用
        return n >> 1;// 除以2
    }

    // 6.乘以2的m次方
    int mulTwoPower(int n, int m) {// 计算n*(2^m)
        return n << m;
    }

    // 7.除以2的m次方
    int divTwoPower(int n, int m) {// 计算n/(2^m)
        return n >> m;
    }

    // 8.判断一个数的奇偶性
    boolean isOddNumber(int n) {

        // 只要根据最未位是0还是1来决定，为0就是偶数，为1就是奇数。
        // 下面程序将输出0到100之间的所有偶数：
        for (int i = 0; i < 100; i++) {
            if ((i & 1) == 0) { // 偶数,代替if (i % 2 == 0)来判断a是不是偶数
                System.out.println(i);
            }
        }

        return (n & 1) == 1;
    }

    // 9.不用临时变量交换两个数（面试常考）
    void swap(int a, int b) {
        // 通用版（一些语言中得分开写）

        // 第一步 a^=b 即a=(a^b)；
        // 第二步 b^=a 即b=b^(a^b)，由于^运算满足交换律，b^(a^b)=b^b^a。由于一个数和自己异或的结果为0并且任何数与0异或都会不变的，所以此时b被赋上了a的值；
        // 第三步 a^=b 就是a=a^b，由于前面二步可知a=(a^b)，b=a，所以a=a^b即a=(a^b)^a。故a会被赋上b的值；

        a ^= b;
        b ^= a;
        a ^= b;
    }

    // 10.取绝对值（某些机器上，效率比n>0  ?  n:-n 高）
    int abs(int n) {
        return (n ^ (n >> 31)) - (n >> 31);
        /* n>>31 取得n的符号，若n为正数，n>>31等于0，若n为负数，n>>31等于-1
         对于任何数，与0异或都会保持不变，与-1即0xFFFFFFFF异或就相当于取反。
        若n为正数 n^0=0,数不变，若n为负数有n^-1 需要计算n和-1的补码，然后进行异或运算，
        结果n变号并且为n的绝对值减1，再减去-1就是绝对值 
         因此，n与(n >> 31)异或后再减(n >> 31)（因为(n >> 31)为0或-1，所以减(n >> 31)即是要么加0要么加1）也可以得到绝对值。
        */
        
        // 另外一种实现
        // 对于负数可以通过对其取反后加1来得到正数。
        // 因此先移位来取符号位，int i = a >> 31;要注意如果a为正数，i等于0，为负数，i等于-1。
        // 然后对i进行判断——如果i等于0，直接返回。否之，返回~a+1。
//        int i = n >> 31;
//        return i == 0 ? n : (~n + 1);
    }

    // 11.取两个数的最大值（某些机器上，效率比a>b ? a:b高）
    // 通用版
    int max(int a, int b) {
        return b & ((a - b) >> 31) | a & (~(a - b) >> 31);
        /*如果a>=b,(a-b)>>31为0，否则为-1*/
    }

    // 12.取两个数的最小值（某些机器上，效率比a>b ? b:a高）
    // 通用版
    int min(int a, int b) {
        return a & ((a - b) >> 31) | b & (~(a - b) >> 31);
        /*如果a>=b,(a-b)>>31为0，否则为-1*/
    }

    // 13.判断符号是否相同
    boolean isSameSign(int x, int y) { // 有0的情况例外
        return (x ^ y) >= 0; // true 表示 x和y有相同的符号， false表示x，y有相反的符号。
    }

    // 14.计算2的n次方
    int getFactorialofTwo(int n) {// n > 0
        return 2 << (n - 1);// 2的n次方
    }

    // 15.判断一个数是不是2的幂
    boolean isFactorialofTwo(int n) {
        return n > 0 ? (n & (n - 1)) == 0 : false;
        /*如果是2的幂，n一定是100... n-1就是1111....
           所以做与运算结果为0*/
    }

    // 16.对2的n次方取余
    int quyu(int m, int n) {// n为2的次方
        return m & (n - 1);
        /*如果是2的幂，n一定是100... n-1就是1111....
         所以做与运算结果保留m在n范围的非0的位*/
    }

    // 17.求两个整数的平均值
    int getAverage(int x, int y) {
        return (x + y) >> 1;
        // 另一种写法
        // return ((x ^ y) >> 1) + (x & y);
        /*(x^y) >> 1得到x，y其中一个为1的位并除以2，
          x&y得到x，y都为1的部分，加一起就是平均数了*/

    }

    // 下面是三个最基本对二进制位的操作
    // 18.从低位到高位,取n的第m位
    int getBit(int n, int m) {
        return (n >> (m - 1)) & 1;
    }

    // 19.从低位到高位.将n的第m位置1
    int setBitToOne(int n, int m) {
        return n | (1 << (m - 1));
        /*将1左移m-1位找到第m位，得到000...1...000
          n在和这个数做或运算*/
    }

    // 20.从低位到高位,将n的第m位置0
    int setBitToZero(int n, int m) {
        return n & ~(1 << (m - 1));
        /* 将1左移m-1位找到第m位，取反后变成111...0...1111
           n再和这个数做与运算*/
    }
    
    
    void  printPrimeNumber() {
     // 打印100以内素数：
     // （1）对每个素数，它的倍数必定不是素数；
     // （2）有很多重复访问如flag[10]会在访问flag[2]和flag[5]时各访问一次；
     int max = 100;
     boolean[] flags = new boolean[max];
     int [] primes = new int[max / 3 + 1];
     int pi = 0;

     for (int m = 2; m < max ; m ++) {
         if (!flags[m]) {
             primes[pi++] = m;
             for(int n = m; n < max; n += m) {
                 flags[n] = true;
             }
         }
     }

     System.out.println(Arrays.toString(primes));
    }

    public static void main(String[] args) {
        // 另附一些对程序效率上没有实质提高的位运算技巧，一些也是位运算的常识（面试也许会遇到）
        int n = 2;
        // 计算n+1
        n = -~n;

        // 计算n-1
        n = ~-n;

        // 变换符号就是正数变成负数，负数变成正数。
        // 取相反数
        n = ~n + 1;
        // 另一种写法
        n = (n ^ -1) + 1;

        int x = 1;
        int a = 2;
        int b = 3;

        // if(x == a) x = b; if(x == b) x = a;
        x = a ^ b ^ x;

        // sign函数，参数为n，当n>0时候返回1，n<0时返回-1，n=0时返回0
        // !!n - (((unsigned)n >> 31) << 1);
        
    }

}
