package org.quickstart.javase.jdk.util.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yangzl
 * @description TODO
 * @createTime 2020/6/12 10:43
 */
public class RegexDemo {

    @Test
    public void testNoRegex() {
        String str = "1234567890";        // 此字符串由数字组成
        boolean flag = true;            // 定义一个标记变量
        // 要先将字符串拆分成字符数组，之后依次判断
        char c[] = str.toCharArray();    // 将字符串变为字符数组
        for (int i = 0; i < c.length; i++) {    // 循环依次判断
            if (c[i] < '0' || c[i] > '9') {        // 如果满足条件，则表示不是数字
                flag = false;            // 做个标记
                break;                    // 程序不再向下继续执行
            }
        }
        if (flag) {
            System.out.println("是由数字组成！");
        } else {
            System.out.println("不是由数字组成！");
        }
    }

    @Test
    public void testRegex() {
        String str = "1234567890";        // 此字符串由数字组成
        if (Pattern.compile("[0-9]+").matcher(str).matches()) {    // 使用正则
            System.out.println("是由数字组成！");
        } else {
            System.out.println("不是由数字组成！");
        }
    }

    @Test
    public void main() {
        String str = "";
        String pattern = "topic*";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        System.out.println(m.matches());
    }

    @Test
    public void testMatches(String args[]) {
        String str = "1993-07-27";        // 指定好一个日期格式的字符串
        String pat = "\\d{4}-\\d{2}-\\d{2}";    // 指定好正则表达式
        Pattern p = Pattern.compile(pat);    // 实例化Pattern类
        Matcher m = p.matcher(str);    // 实例化Matcher类
        if (m.matches()) {        // 进行验证的匹配，使用正则
            System.out.println("日期格式合法！");
        } else {
            System.out.println("日期格式不合法！");
        }
    }

    @Test
    public void testSplit() {
        //        使用正则进行字符串的拆分功能。
        // 要求将里面的字符取出，也就是说按照数字拆分
        String str = "A1B22C333D4444E55555F";    // 指定好一个字符串
        String pat = "\\d+";    // 指定好正则表达式
        Pattern p = Pattern.compile(pat);    // 实例化Pattern类
        String s[] = p.split(str);    // 执行拆分操作
        for (int x = 0; x < s.length; x++) {
            System.out.print(s[x] + "\t");
        }
    }

    public static void main(String args[]) {

        //        将字符串中的全部数字替换成“_”

        // 要求将里面的字符取出，也就是说按照数字拆分
        String str = "A1B22C333D4444E55555F";    // 指定好一个字符串
        String pat = "\\d+";    // 指定好正则表达式
        Pattern p = Pattern.compile(pat);    // 实例化Pattern类
        Matcher m = p.matcher(str);    // 实例化Matcher类的对象
        String newString = m.replaceAll("_");
        System.out.println(newString);
    }

}
