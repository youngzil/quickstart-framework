package org.quickstart.javase.jdk.util.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yangzl
 * @description TODO
 * @createTime 2020/6/12 10:53
 */
public class StringRegexDemo {
    @Test
    public void testMatch() {

        // Pattern pattern = Pattern.compile("topic.*"); //以topic开头
        // Pattern pattern = Pattern.compile("(topic)(.*?)"); //以topic开头
        // Pattern pattern = Pattern.compile("(.*?)(topic)"); //以topic结尾
        // Pattern pattern = Pattern.compile("(.*?)(topic)(.*?)"); //含有topic的字符串

        // ^为限制开头
        // Pattern pattern = Pattern.compile("^topic.*"); //查找以topic开头,任意结尾的字符串

        // $为限制结尾
        Pattern pattern = Pattern.compile(".*topic$"); //任意字符串开始，以topic为结尾字符

        Matcher matcher = pattern.matcher("topic01");
        System.out.println(matcher.matches());

        matcher = pattern.matcher("topic02");
        System.out.println(matcher.matches());

        matcher = pattern.matcher("222topic02");
        System.out.println(matcher.matches());

        matcher = pattern.matcher("02topic");
        System.out.println(matcher.matches());

        matcher = pattern.matcher("222topic");
        System.out.println(matcher.matches());

    }

    @Test
    public void testReplace() {

        Pattern pattern = Pattern.compile("正则表达式");

        Matcher matcher = pattern.matcher("正则表达式 Hello World,正则表达式 Hello World");
        // 文字替换（首次出现字符）
        //替换第一个符合正则的数据
        System.out.println(matcher.replaceFirst("Java"));

        matcher = pattern.matcher("正则表达式 Hello World,正则表达式 Hello World");
        //文字替换（全部）
        System.out.println(matcher.replaceAll("Java"));

        matcher = pattern.matcher("正则表达式 Hello World,正则表达式 Hello World ");
        // 文字替换（置换字符）
        StringBuffer sbr = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sbr, "Java");
        }
        matcher.appendTail(sbr);
        System.out.println(sbr.toString());
    }

    @Test
    public void testEmail() {

        // ◆验证是否为邮箱地址

        String str = "ceponline@yahoo.com.cn";

        Pattern pattern = Pattern.compile("[//w//.//-]+@([//w//-]+//.)+[//w//-]+", Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(str);

        System.out.println(matcher.matches());

    }

    @Test
    public void testReplaceHtml() {

        // ◆去除html标记

        Pattern pattern = Pattern.compile("<.+?>", Pattern.DOTALL);

        Matcher matcher = pattern.matcher("<a href=\"www.jianshu.com\" >主页</a>");

        String string = matcher.replaceAll("");

        System.out.println(string);

        // ◆查找html中对应条件字符串

        pattern = Pattern.compile("href=/\" (. + ?)/\"");

        matcher = pattern.matcher("<a href=\"www.jianshu.com\">主页</a>");

        if (matcher.find()) {
            System.out.println(matcher.group(1));
        }

    }

    @Test
    public void testSubUrl() {

        // ◆截取http://地址

        //截取url

        Pattern pattern = Pattern.compile("(http://|https://){1}[//w//.//-/:]+");

        Matcher matcher = pattern.matcher("http://www.baidu.com");

        StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {

            buffer.append(matcher.group());

            buffer.append("/r/n");

            System.out.println(buffer);

        }

    }

    @Test
    public void testReplaceStr() {

        // ◆替换指定{}中文字

        String str = "Java目前的发展史是由{0}年-{1}年";

        String[][] object = {new String[] {"{0}", "1995"}, new String[] {"{1}", "2007"}};

        System.out.println(replace(str, object));

    }

    public static String replace(final String sourceString, Object[] object) {

        String temp = sourceString;

        for (int i = 0; i < object.length; i++) {

            String[] result = (String[])object[i];

            Pattern pattern = Pattern.compile(result[0]);

            Matcher matcher = pattern.matcher(temp);

            temp = matcher.replaceAll(result[1]);

        }

        return temp;

    }

    @Test
    public void test() {
        String str1 = "A1B22C333D4444E55555F".replaceAll("\\d+", "_");
        boolean temp = "1983-07-27".matches("\\d{4}-\\d{2}-\\d{2}");
        String s[] = "A1B22C333D4444E55555F".split("\\d+");
        System.out.println("字符串替换操作：" + str1);
        System.out.println("字符串验证：" + temp);
        System.out.print("字符串的拆分：");
        for (int x = 0; x < s.length; x++) {
            System.out.print(s[x] + "\t");
        }
    }

    @Test
    public void test2() {
        String info = "LX:98|HHXY:90|NAN:100";            // 定义一个字符串
        // 拆分的形式：
		/*
			LX	-->	98
			HHXY	-->	90
			NAN	-->	100
		*/
        String s[] = info.split("\\|"); // 因为 | 是表示规范中的或，所以需要进行转义
        System.out.println("字符串的拆分：");
        for (int x = 0; x < s.length; x++) {
            String s2[] = s[x].split(":");
            System.out.println(s2[0] + "\t" + s2[1]);
        }
    }

}
