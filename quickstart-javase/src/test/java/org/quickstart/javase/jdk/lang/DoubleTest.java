package org.quickstart.javase.jdk.lang;

import org.junit.Test;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2020/4/26 01:58
 */
public class DoubleTest {

    @Test
    public void testDouble() {
        float a = 0.125f;
        double b = 0.125d;
        System.out.println((a - b) == 0.0);
    }

    @Test
    public void testDouble2() {
        double c = 0.8;
        double d = 0.7;
        double e = 0.6;
        System.out.println((c - d) == (d - e));
    }

    @Test
    public void testDouble3() {
        //        输出Infinity，
        //        原因：①1/0.1=10 ②1/0.01=100 ③1/0.001=1000，所以分子确定时，分母越小商越大，分母趋向于0时，商无穷大。对于java语言，手册中也有提到加减运算的第一步就是零值检测，如果涉及其中一个数是0，可以直接得出结果。
        System.out.println(1.0 / 0);
    }

    @Test
    public void testDouble4() {
        //  输出NaN
        //  原因：个人觉得还是java虚拟机自己搞的逻辑，目的就是用一个公式来模拟出这种Not a Number的值来，就像NaN转成Long统一翻译为0xff800001一样。底层应该是在零值检测时处理的。为什么这样说，因为(0.0f == 0)是true，那0.0/0是不是就等于0/0，那为什么会有两种结果。只有自己定义用来标示能解释通的吧。
        System.out.println(0.0 / 0.0);

    }


//    参考
//    https://blog.csdn.net/weixin_39923425/article/details/102400791

}
