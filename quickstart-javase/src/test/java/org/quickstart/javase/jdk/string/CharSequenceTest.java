/**
 * 项目名称：quickstart-javase 
 * 文件名：CharSequenceTest.java
 * 版本信息：
 * 日期：2017年7月31日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.string;

/**
 * CharSequenceTest StringBuilder 和 StringBuffer都是可变的字符序列。它们都继承于AbstractStringBuilder，实现了CharSequence接口。 但是，StringBuilder是非线程安全的，而StringBuffer是线程安全的。
 * 
 * @author：youngzil@163.com
 * @2017年7月31日 下午9:22:02
 * @version 2.0
 */
public class CharSequenceTest {
    public static void main(String[] args) {

        testCharSequence();
    }

    /**
     * CharSequence 测试程序
     */
    private static void testCharSequence() {
        System.out.println("-------------------------------- testCharSequence -----------------------------");

        // 1. CharSequence的子类String
        String str = "abcdefghijklmnopqrstuvwxyz";
        System.out.println("1. String");
        System.out.printf("   %-30s=%d\n", "str.length()", str.length());
        System.out.printf("   %-30s=%c\n", "str.charAt(5)", str.charAt(5));
        String substr = (String) str.subSequence(0, 5);
        System.out.printf("   %-30s=%s\n", "str.subSequence(0,5)", substr.toString());

        // 2. CharSequence的子类StringBuilder
        StringBuilder strbuilder = new StringBuilder("abcdefghijklmnopqrstuvwxyz");
        System.out.println("2. StringBuilder");
        System.out.printf("   %-30s=%d\n", "strbuilder.length()", strbuilder.length());
        System.out.printf("   %-30s=%c\n", "strbuilder.charAt(5)", strbuilder.charAt(5));
        // 注意：StringBuilder的subSequence()返回的是，实际上是一个String对象！
        String substrbuilder = (String) strbuilder.subSequence(0, 5);
        System.out.printf("   %-30s=%s\n", "strbuilder.subSequence(0,5)", substrbuilder.toString());

        // 3. CharSequence的子类StringBuffer
        StringBuffer strbuffer = new StringBuffer("abcdefghijklmnopqrstuvwxyz");
        System.out.println("3. StringBuffer");
        System.out.printf("   %-30s=%d\n", "strbuffer.length()", strbuffer.length());
        System.out.printf("   %-30s=%c\n", "strbuffer.charAt(5)", strbuffer.charAt(5));
        // 注意：StringBuffer的subSequence()返回的是，实际上是一个String对象！
        String substrbuffer = (String) strbuffer.subSequence(0, 5);
        System.out.printf("   %-30s=%s\n", "strbuffer.subSequence(0,5)", substrbuffer.toString());

        System.out.println();
    }
}
