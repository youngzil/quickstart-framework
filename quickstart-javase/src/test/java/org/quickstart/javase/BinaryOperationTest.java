package org.quickstart.javase;

/**
 * BinaryOperationTest
 *
 * @version 2.0
 * @author：youngzil@163.com
 * @2017年8月6日 下午5:33:39
 */
public class BinaryOperationTest {

    public static void main(String[] args) {

        System.out.println("打印出来：" + getBinaryNum(1));

        // ~操作符（取反），对应二进制位取反，0变成1，1变成0
        // &操作符（与），对应二进制位进行与操作，都为1时变成1，其他变为0
        // |操作符（或）对应二进制位进行或操作，都为0时变成0，其他变为1
        // ^操作符（异或），对应二进制位相同时，该位变成0，否则变成1
        // <<操作（左移），二进制位向左移动，右边填充0
        // >>操作（右移），二进制位向右移动，左边填充0

        // 不使用中间变量交换两个数
        int num1 = 2;
        int num2 = 5;
        num1 = num1 ^ num2;
        num2 = num2 ^ num1;
        num1 = num1 ^ num2;
        System.out.println("num1:" + num1 + "\n" + "num2:" + num2);

        // 求2的N次方
        // 求2的32次方：
        System.out.println(Math.pow(2, 32));
        System.out.println(1L << 32);

        // 判断奇数偶数
        int even = 6;
        int odd = 9;
        if (even % 2 == 0) {
            System.out.println("偶数");
        } else {
            System.out.println("奇数");
        }
        System.out.println((((int) even & 1) == 1) ? "奇数" : "偶数");

        System.out.println((((int) odd & 1) == 1) ? "奇数" : "偶数");

        // 求绝对值
        int num = -3;
        System.out.println(Math.abs(num));
        int i = num >> 31;
        System.out.println(i == 0 ? num : (~num + 1));
    }

    /**
     * @Description: @param：
     * @author：youngzil@163.com
     * @Date 2017/8/6 17:49
     * @since 2.0
     */
    private static String getBinaryNum(int n) {

        String num = Integer.toBinaryString(n);
        if (num.length() == 32) {
            return num;
        } else {
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 32 - num.length(); i++) {
                sb.append("0");
            }
            return sb.toString() + num;
        }
    }
}
