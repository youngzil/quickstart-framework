package org.quickstart.javase.jdk.util.regex;

import org.junit.Test;

/**
 * @description TODO
 *
 * @author yangzl
 * @createTime 2020/6/12 10:53
 */
public class StringRegexDemo {

    @Test
    public void test(String args[]){
        String str1 = "A1B22C333D4444E55555F".replaceAll("\\d+","_") ;
        boolean temp = "1983-07-27".matches("\\d{4}-\\d{2}-\\d{2}") ;
        String s[] = "A1B22C333D4444E55555F".split("\\d+") ;
        System.out.println("字符串替换操作：" + str1) ;
        System.out.println("字符串验证：" + temp) ;
        System.out.print("字符串的拆分：") ;
        for(int x=0;x<s.length;x++){
            System.out.print(s[x] + "\t") ;
        }
    }

    @Test
    public  void test2(String args[]){
        String info = "LX:98|HHXY:90|NAN:100" ;			// 定义一个字符串
        // 拆分的形式：
		/*
			LX	-->	98
			HHXY	-->	90
			NAN	-->	100
		*/
        String s[] = info.split("\\|") ; // 因为 | 是表示规范中的或，所以需要进行转义
        System.out.println("字符串的拆分：") ;
        for(int x=0;x<s.length;x++){
            String s2[] = s[x].split(":") ;
            System.out.println(s2[0] + "\t" + s2[1]) ;
        }
    }

}
