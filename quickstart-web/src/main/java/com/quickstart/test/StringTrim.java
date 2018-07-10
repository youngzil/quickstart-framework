package com.quickstart.test;

public class StringTrim {

    public static void main(String[] args) {

        /*JAVA中去掉空格
        1. String.trim()
        trim()是去掉首尾空格
        2.str.replace(" ", ""); 去掉所有空格，包括首尾、中间
        复制代码 代码如下:String str = " hell o ";
        String str2 = str.replaceAll(" ", "");
        System.out.println(str2);
        3.或者replaceAll(" +",""); 去掉所有空格
        4.str = .replaceAll("\\s*", "");
        可以替换大部分空白字符， 不限于空格
        \s 可以匹配空格、制表符、换页符等空白字符的其中任意一个 您可能感兴趣的文章:java去除字符串中的空格、回车、换行符、制表符的小例子*/

        String s = "string";
        String space = "string ";// 空格
        String tab = "string	";// 制表符

        System.out.println(s.equals(space.trim()));
        System.out.println(s.equals(tab.trim()));

        System.out.println(s.equals(space.replace(" ", "")));
        System.out.println(s.equals(tab.replace(" ", "")));

        System.out.println(s.equals(space.replaceAll(" +", "")));
        System.out.println(s.equals(tab.replaceAll(" +", "")));

        System.out.println(s.equals(space.replaceAll("\\s*", "")));
        System.out.println(s.equals(tab.replaceAll("\\s*", "")));

    }

}
